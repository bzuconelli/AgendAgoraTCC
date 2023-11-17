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




document.getElementById('agendarservico').addEventListener('submit', function (event) {
    event.preventDefault();
    getContratante().then(contratante => {
        let tipoServico=1
        let latitudecontratente = contratante.lat;
        let longitudecontratente = contratante.lng;
        let distancia = document.getElementById('filtroDistancia').value;
        let data = document.getElementById('data').value;
        let formasdepagamento = document.querySelector('#filtroPagamento');
        let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;
        getPretadores(data,formadepagamento,tipoServico,distancia,latitudecontratente,longitudecontratente)
        
        const myModal = new bootstrap.Modal(document.getElementById('modalp'), {})
        myModal.show();
        initMap(latitudecontratente, longitudecontratente);
    })


});

var map;


function initMap(latitudecontratente, longitudecontratente) {
    var mapOptions = {
        center: { lat: latitudecontratente, lng: longitudecontratente },
        zoom: 9,
    };

    map = new google.maps.Map(document.getElementById('map'), mapOptions);

    getPretadores().then(prestadores => prestadores.forEach(prestador => {
        const marker = new google.maps.Marker({
            position: { lat: prestador.latitude, lng: prestador.longitude },
            title: prestador.nome,
        });
        let infoWindow = new google.maps.InfoWindow({
            content: '<div id="teste">' + prestador.id + '<h2>' + prestador.nome + '</h2>' + ' <button type="button"  data-bs-dismiss="modal" aria-label="Close" onclick="selecionar(this)">Selecionar Prestador</button></div>'

        });
        marker.addListener('click', () => {
            infoWindow.open(map, marker);
        })
        marker.setMap(map);
    }));
}
function agendarservico() {

    let servicoaserrealizado = document.getElementById('Saf').value;
    let distancia = document.getElementById('filtroDistancia').value;

    let data = document.getElementById('data').value;
    let formasdepagamento = document.querySelector('#filtroPagamento');
    let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;
}

function selecionar(element) {
    let proximoelemento = element.parentNode;
    var idprestador = proximoelemento.childNodes[0];
    console.log(idprestador);
    document.getElementById('agendar').style.display = "block"


}