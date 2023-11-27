async function getOrdendeservico(apenasdodia) {
    let url = "http://localhost:8080/ordendeservico/servicos";

    if (apenasdodia) {
        url += "?servicos=true";
    }

    let response = await fetch(url, {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem('token')
        },
    });
    if (response.status === 404) { return null; }
    let ordendesservico = await response.json();
    return ordendesservico;
}
function createServiceElements() {
    let servicesListContainer = document.getElementById("servicesList");

    getOrdendeservico(apenasdodia).then(ordendesservico => ordendesservico.forEach(ordendeservico => {
        let serviceElement = document.createElement("div");
        serviceElement.className = "service";
        let h4 = document.createElement("h4");
        h4.textContent = ordendeservico.descricao;
        let dataservico = document.createElement("p");
        dataservico.textContent = "Data: " + ordendeservico.data;
        let nomecliente = document.createElement("p");
        nomecliente.textContent = "Cliente: " + ordendeservico.nomec + " " + ordendeservico.sobrenomec;
        let endereco = document.createElement("p");
        endereco.textContent = "Endereço: Rua " + ordendeservico.rua + " " + "bairro: " + ordendeservico.bairo + " " + "numero: " + ordendeservico.numero + " " + "cidadae: " + ordendeservico.cidade
        serviceElement.appendChild(h4);
        serviceElement.appendChild(dataservico);
        serviceElement.appendChild(nomecliente);
        serviceElement.appendChild(endereco);
        servicesListContainer.appendChild(serviceElement);
    }));
}
apenasdodia= false;

createServiceElements();

