async function getContratante() {
    let response = await fetch("http://localhost:8080/contratante/", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },
    });

    let contratante = await response.json();

    return contratante;
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
                    content: '<div id="teste">' + prestador.id + '<h2>' + prestador.nome + '</h2>' + '<h2>' + prestador.sobrenome + '</h2>' + '<h2>' + prestador.nota + '</h2>' + ' <button type="button"  data-bs-dismiss="modal" aria-label="Close" onclick="selecionar(this)">Selecionar Prestador</button></div>'
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
    getContratante().then(contratante => {
        let tipoServico = 1
        idcontratante = contratante.id;
        let latitudecontratente = contratante.lat;
        let longitudecontratente = contratante.lng;
        let distancia = document.getElementById('filtroDistancia').value;
        let dataInput = document.getElementById('data');
        let dataSelecionada = new Date(dataInput.value);
        let dataAtual = new Date();

        if (dataSelecionada < dataAtual) {
            const myModal = new bootstrap.Modal(document.getElementById('datapassada'), {});
            myModal.show();
            
            return;
        }
        let formasdepagamento = document.querySelector('#filtroPagamento');
        let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;
        document.getElementById("spinner").style.display = 'inline-block';
        document.getElementById('Filtrar').disabled = true;

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
    let tipoServico = 1;
    let servicoaserrealizado = document.getElementById('Saf').value;
    let data = document.getElementById('data').value;
    let formasdepagamento = document.querySelector('#filtroPagamento');
    let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;

    document.getElementById("spinner1").style.display = 'inline-block';
    document.getElementById('Filtrar').disabled = true;
    postOrdendeservico(idprerst, idcontratante, data, formadepagamento, servicoaserrealizado, tipoServico).then(ordendeservico => {
        if (ordendeservico == null) {
            const myModal = new bootstrap.Modal(document.getElementById('vagasocupadas'), {});
            myModal.show();
            document.getElementById('Filtrar').disabled = false;
            document.getElementById("spinner").style.display = 'none';
            document.getElementById('agendar').disabled = false;
            document.getElementById("spinner1").style.display = 'none';


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