if (sessionStorage.getItem("token") === null) {
    window.location.href="../login.html"

}
async function getPrestador() {
    let response = await fetch("http://localhost:8080/prestador/prestador", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem('token')
        },
    });

    let prestador = await response.json();

    return prestador;
}
var map;
function initMap(latitudecontratente, longitudecontratente, prestadores) {
    if (latitudecontratente != "" || longitudecontratente != "") {
        var mapOptions = {
            center: { lat: parseFloat(latitudecontratente), lng: parseFloat(longitudecontratente) },
            zoom: 13,
        };
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        if (prestadores && prestadores.length > 0) {
            prestadores.forEach(prestador => {
                const marker = new google.maps.Marker({
                    position: { lat: parseFloat(prestador.lat), lng: parseFloat(prestador.lng) },
                    title: prestador.nome,
                    map: map
                });

                let infoWindow = new google.maps.InfoWindow({
                    content: '<div id="teste" style="max-width: 200px; text-align: center; padding: 10px; border-radius: 8px; background-color: #fff; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">' + 
                    '<h3 style="margin-bottom: 5px; font-size: 8px;"> ' + prestador.id + '</h3>' +
                    '<h2 style="margin-bottom: 10px;"> ' + prestador.nome + ' ' + prestador.sobrenome + '</h2>' +
                    '<p style="font-size: 15px;">Nota: ' + prestador.nota + '</p>' +
                    '<button type="button" style="background-color: #007BFF; color: #fff; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer;" data-bs-dismiss="modal" aria-label="Close" onclick="selecionar(this)">Selecionar Prestador</button>' +
                    '</div>'
                });
                marker.setMap(map);

                marker.addListener('click', () => {
                    infoWindow.open(map, marker);
                });
            });
        }
    }
}
var idcontratante;
document.getElementById('agendarservico').addEventListener('submit', function (event) {
    event.preventDefault();
    getPrestador().then(prestador => {
        let tiposServicos = document.querySelector('#tiposervico');
        let tipoServico = tiposServicos.options[tiposServicos.selectedIndex].value;
        idcontratante = prestador.id;
        let latitudecontratente = prestador.lat;
        let longitudecontratente = prestador.lng;
        let distancia = document.getElementById('filtroDistancia').value;
        let data = document.getElementById('data').value;
        let dataAtual = new Date();
        let ano = dataAtual.getFullYear();
        let mes = (dataAtual.getMonth() + 1).toString().padStart(2, '0'); 
        let dia = dataAtual.getDate().toString().padStart(2, '0'); 
        let dataFormatada = `${ano}-${mes}-${dia}`;
        let formasdepagamento = document.querySelector('#filtroPagamento');
        let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;

        if (data <= dataFormatada) {
            const myModal = new bootstrap.Modal(document.getElementById('datapassada'), {});
            myModal.show();

            return;
        }
        getPretadores(data, formadepagamento, tipoServico, distancia, latitudecontratente, longitudecontratente).then(prestadores => {
            if (prestadores == null) {
                const myModal = new bootstrap.Modal(document.getElementById('modalnadaencontrado'), {});
                myModal.show();
                document.getElementById('Filtrar').disabled = false;
                document.getElementById("spinner").style.display = 'none';
            } else {
                const myModal = new bootstrap.Modal(document.getElementById('modalp'), {});
                myModal.show();
                initMap(latitudecontratente, longitudecontratente, prestadores);


            }
        });

    });
});

var idprestador;
function agendarservico() {
    let idprerst = idprestador
    let tiposServicos = document.querySelector('#tiposervico');
    let tipoServico = tiposServicos.options[tiposServicos.selectedIndex].value;
    let servicoaserrealizado = document.getElementById('Saf').value;
    let data = document.getElementById('data').value;
    let formasdepagamento = document.querySelector('#filtroPagamento');
    let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;

    document.getElementById("spinner1").style.display = 'inline-block';
    document.getElementById('agendar').disabled = true;
    postOrdendeservico1(idprerst, idcontratante, data, formadepagamento, servicoaserrealizado, tipoServico).then(ordendeservico => {
        if (ordendeservico == null) {
            const myModal = new bootstrap.Modal(document.getElementById('vagasocupadas'), {});
            myModal.show();
            document.getElementById('Filtrar').disabled = false;
            document.getElementById("spinner").style.display = 'none';
            document.getElementById('agendar').disabled = false;
            document.getElementById('agendar').style.display = 'none';
            document.getElementById("spinner1").style.display = 'none';
            document.getElementById('labelnome').style.display = "none"
            document.getElementById('nomep').style.display = "none"
        } else {
            const myModal = new bootstrap.Modal(document.getElementById('osgerada'), {});
            myModal.show();
            document.getElementById("agendarservico").reset();
            document.getElementById('labelnome').style.display = "none"
            document.getElementById('nomep').style.display = "none"
            document.getElementById('agendar').style.display = "none"
            document.getElementById('Filtrar').disabled = false;
            document.getElementById("spinner").style.display = 'none';
            document.getElementById('agendar').disabled = false;
            document.getElementById("spinner1").style.display = 'none';
        }
    })
}

function selecionar(element) {
    let proximoelemento = element.parentNode;
    let id = proximoelemento.childNodes[0];
    var nome = proximoelemento.childNodes[1].innerHTML;
    console.log(id)
    document.getElementById('labelnome').style.display = "block"
    document.getElementById('nomep').style.display = "block"
    document.getElementById('nomep').value = nome
    document.getElementById('agendar').style.display = "block"
    idprestador = id.textContent.trim()
    console.log(idprestador)
}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href = "../login.html"
    })
}
let path = window.location.pathname;
function highlightActiveLink() {
    document.querySelectorAll('.nav-link').forEach(function (link) {
        link.classList.remove('ativo');
    });
    if (path.includes("agedarservicocomoprestador.html")) {
        document.getElementById('agendarLink').classList.add('ativo');
    } else if (path.includes("prestadorservicos.html")) {
        document.getElementById('meusServicosLink').classList.add('ativo');
    } else if (path.includes("prestadorservicosdodia.html")) {
        document.getElementById('servicosFazerLink').classList.add('ativo');
    } else if (path.includes("telamenuinicialpres.html")) {
        document.getElementById('calendarioVagasLink').classList.add('ativo');
    }
    else if (path.includes("prestadorconfig.html")) {
        document.getElementById('configLink').classList.add('ativo');
    }
}
window.onload = highlightActiveLink;