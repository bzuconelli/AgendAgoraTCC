if (sessionStorage.getItem("token") === null) {
    window.location.href="../login.html"
}
async function getOrdendeservico(osemaberto) {
    let url = "http://localhost:8080/ordendeservico/";

    if (osemaberto) {
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
function apenasemaberto() {
    let checkboxAberto = document.getElementById('apenasemaberto');
    let apenasemaberto = checkboxAberto.checked;
    getOrdendeservico(apenasemaberto).then(ordendesservico => ordendesservico.forEach(ordendeservico => {
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
        colunaPrestador.innerHTML = ordendeservico.nomeo + "  " + ordendeservico.sobrenomeo;
        colunaServico.innerHTML = ordendeservico.descricao;
        colunaStatus.innerHTML = ordendeservico.status;
        colunaData.innerHTML = ordendeservico.data;
        colunaFormadepagamento.innerHTML = ordendeservico.formapagamento;
        if (ordendeservico.status == 'concluido' && ordendeservico.nota == 0) {
            colunaacao.innerHTML = "  <button class='btn btn-info'onclick='avaliar(this)'>Avaliar </button>"
        } else if (ordendeservico.status == 'aberto') {
            colunaacao.innerHTML = "  <button class='btn btn-danger'onclick='cancelar(this)'>Cancelar</button>"
        } else{
            colunaacao.innerHTML = "  <button class='btn btn-info' disabled >Avaliado</button>"
        }
    }));
}
function cancelar(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let id = trelement.childNodes[0].innerHTML;
    let tabela = trelement.parentNode;
    const modalConfirma = new bootstrap.Modal(document.getElementById('modalconfirma'), {});
    modalConfirma.show();
    document.getElementById('btnConfirmarCancelamento').addEventListener('click', function () {
        modalConfirma.hide();
        deleteOrdendeservico(id).then(() => {
            tabela.deleteRow(trelement.rowIndex);
        });
    })
}
function avaliar(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let idorden = trelement.childNodes[0].innerHTML;
    let nomeprestador = trelement.childNodes[1].innerHTML;
    window.sessionStorage.setItem('nomeprestador', nomeprestador);
    window.sessionStorage.setItem('idorden', idorden);
    window.location.href = "../avaliacoes.html";
}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href="../login.html"
    })
}
apenasemaberto();

