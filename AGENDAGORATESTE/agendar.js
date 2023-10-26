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
function agendarservico() {
    let servicoaserrealizado = document.getElementById('Saf').value;
    let distancia = document.getElementById('filtroDistancia').value;

    let data = document.getElementById('data').value;
    let formasdepagamento = document.querySelector('#filtroPagamento');
    let formadepagamento = formasdepagamento.options[formasdepagamento.selectedIndex].value;
}




var map;


function initMap() {
    var mapOptions = {
        center: { lat: -26.826162, lng: -49.300635 },
        zoom: 13,
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


function selecionar(element) {
    let proximoelemento = element.parentNode;
    var idprestador = proximoelemento.childNodes[0];
    console.log(idprestador);
    document.getElementById('agendar').style.display= "block"


}