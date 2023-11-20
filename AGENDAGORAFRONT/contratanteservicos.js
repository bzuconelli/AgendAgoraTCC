async function getOrdendeservico() {

    let response = await fetch("http://localhost:8080/ordendeservico/", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },
    });
    if (response.status === 404) { return null; }

    let ordendesservico = await response.json();

    return ordendesservico;
}
getOrdendeservico().then(ordendesservico => ordendesservico.forEach(ordendeservico => {

    let tabela = document.getElementById("tabeladados");
    let linha = tabela.insertRow();
    let colunacodigo = linha.insertCell();
    let colunaPrestador = linha.insertCell();
    let colunaServico = linha.insertCell();
    let colunaStatus = linha.insertCell();
    let colunaData = linha.insertCell();
    let colunaFormadepagamento = linha.insertCell();
    let colunaacao = linha.insertCell();
    colunacodigo.innerHTML = ordendeservico.idos;
    colunaPrestador.innerHTML = ordendeservico.nomeo + "  " +ordendeservico.sobrenomeo;
    colunaServico.innerHTML = ordendeservico.descricao;
    colunaStatus.innerHTML = ordendeservico.status;
    colunaData.innerHTML = ordendeservico.data;
    colunaFormadepagamento.innerHTML = ordendeservico.formapagamento;
    colunaacao.innerHTML = "  <button class='btn btn-danger'onclick='cancelar(this)'>Cancelar</button>"
}));



function cancelar(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let id = trelement.childNodes[0].innerHTML;
    let tabela = trelement.parentNode;
    deleteOrdendeservico(id).then(() => {
        tabela.deleteRow(trelement.rowIndex);
    });


}