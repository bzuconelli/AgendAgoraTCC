if (sessionStorage.getItem("token") === null) {
    window.location.href = "../login.html"
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
    let tabela = document.getElementById("tabeladados");
    while (tabela.rows.length > 1) {
        tabela.deleteRow(1);
    }

    getOrdendeservico(apenasemaberto).then(ordendesservico => ordendesservico.forEach(ordendeservico => {
        let tabela = document.getElementById("tabeladados");

        let linha = tabela.insertRow();
        let colunacodigo = linha.insertCell();
        let colunaPrestador = linha.insertCell();
        let colunaTelefone = linha.insertCell();
        let colunaServico = linha.insertCell();
        let colunaStatus = linha.insertCell();
        let colunaData = linha.insertCell();
        let colunaFormadepagamento = linha.insertCell();
        let colunaacao = linha.insertCell();
        let partesDaData = ordendeservico.data.split('-');
        let dataFormatada = new Date(partesDaData[0], partesDaData[1] - 1, partesDaData[2]).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });
        colunacodigo.innerHTML = ordendeservico.idos;
        colunaPrestador.innerHTML = ordendeservico.nomeo + "  " + ordendeservico.sobrenomeo;
        colunaTelefone.innerHTML =ordendeservico.telefone
        colunaServico.innerHTML = ordendeservico.descricao;
        colunaStatus.innerHTML = ordendeservico.status;
        colunaData.innerHTML = dataFormatada;
        colunaFormadepagamento.innerHTML = ordendeservico.formapagamento;
        if (ordendeservico.status == 'concluido' && ordendeservico.nota == 0) {
            colunaacao.innerHTML = "  <button class='btn btn-info'onclick='avaliar(this)'>Avaliar </button>"+" "
            + " <img src='../3721678-whatsapp_108065.png' style='width: 40px; height: 40px;' onclick='whats(this)'></button>"
        } else if (ordendeservico.status == 'aberto') {
            colunaacao.innerHTML = "  <button class='btn btn-danger'onclick='cancelar(this)'>Cancelar</button>"+" "
            + " <img src='../3721678-whatsapp_108065.png' style='width: 40px; height: 40px;' onclick='whats(this)'></button>"
        } else {
            colunaacao.innerHTML = "  <button class='btn btn-info' disabled >Avaliado</button>"+" "
            + " <img src='../3721678-whatsapp_108065.png' style='width: 40px; height: 40px;' onclick='whats(this)'></button>"
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
        window.location.href = "../login.html"
    })
}
function whats(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let fone = trelement.childNodes[2].innerHTML;
    let numeroFormatado = fone.replace(/\D/g, '');
    window.open("https://wa.me/55"+numeroFormatado)

}
apenasemaberto();
let path = window.location.pathname;
function highlightActiveLink() {
    document.querySelectorAll('.nav-link').forEach(function (link) {
        link.classList.remove('ativo');
    });
    if (path.includes("agendarservico.html")) {
        document.getElementById('agendarLink').classList.add('ativo');
    } else if (path.includes("contratanteservicos.html")) {
        document.getElementById('meusServicosLink').classList.add('ativo');
    } else if (path.includes("contratanteconfig.html")) {
        document.getElementById('configLink').classList.add('ativo');
    }
}
window.onload = highlightActiveLink;