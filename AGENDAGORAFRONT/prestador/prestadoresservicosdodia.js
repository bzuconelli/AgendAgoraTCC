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
        let tabela = document.getElementById("tabeladados");
        let linha = tabela.insertRow();
        let colunacodigo = linha.insertCell();
        let colunacliente = linha.insertCell();
        let colunaServico = linha.insertCell();
        let colunafone = linha.insertCell();
        let colunaendereco = linha.insertCell();
        let colunaData = linha.insertCell();
        let colunaacao = linha.insertCell();
        colunacodigo.innerHTML = ordendeservico.idos;
        colunacliente.innerHTML = ordendeservico.nomec + "  " + ordendeservico.sobrenomec;
        colunaServico.innerHTML = ordendeservico.descricao;
        colunafone.innerHTML = ordendeservico.telefone;
        colunaendereco.innerHTML ="Rua "+ ordendeservico.rua+" "+ordendeservico.numero+" "+ordendeservico.bairo+" "+ordendeservico.cidade+" ";
        colunaData.innerHTML = ordendeservico.data;
        colunaacao.innerHTML = "  <button class='btn btn-danger'onclick='cancelar(this)'>Cancelar</button>"
       
    }));
}
apenasdodia= false;
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href="../login.html"
    })
}
createServiceElements();

