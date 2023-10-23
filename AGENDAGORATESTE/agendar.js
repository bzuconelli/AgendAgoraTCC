document.getElementById('agendarservico').addEventListener('submit', function (event) {
    event.preventDefault();
    let servicoaserrealizado = document.getElementById('Saf').value;
    let distancia = document.getElementById('filtroDistancia').value;

    let data = document.getElementById('data').value;
    let formasdepagamento = document.querySelector('#filtroPagamento');
    let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;
    const myModal = new bootstrap.Modal(document.getElementById('modalp'), {})
    myModal.show();
    initMap();


});

var Prestador = "Jose Silva";
var nota = 8;
var id = 1;
var map;

function initMap() {
    var mapOptions = {
        center: { lat: -26.826162, lng: -49.300635 },
        zoom: 13,
    };

    map = new google.maps.Map(document.getElementById('map'), mapOptions);

    const marker = new google.maps.Marker({
        position: { lat: -26.826162, lng: -49.300635 },
        map: map,
        title: 'Jose',


    })
    let infoWindow = new google.maps.InfoWindow({
        content: '<div id="teste">' + id + '<h2>' + Prestador + '</h2>' + '<h2> Avalição:' + nota + '</h2>' + '<button onclick="selecionar(this)">Selecionar Prestador</button> </div>'

    });
    marker.addListener('click', () => {
        infoWindow.open(map, marker);
    })

}


function selecionar(elemet) {

    console.log(elemet)




}