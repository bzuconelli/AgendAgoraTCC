if (sessionStorage.getItem("token") === null) {
    window.location.href="../login.html"

}
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
function apenasdodia() {
    let checkbox = document.getElementById('apenasdodia');
    let apenasdodia = checkbox.checked;
    let tabela = document.getElementById("tabeladados");
    while (tabela.rows.length > 1) {
        tabela.deleteRow(1);
    }
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
        let partesDaData = ordendeservico.data.split('-');
        let dataFormatada = new Date(partesDaData[0], partesDaData[1] - 1, partesDaData[2]).toLocaleDateString('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' });
        colunacodigo.innerHTML = ordendeservico.idos;
        colunacliente.innerHTML = ordendeservico.nomec + "  " + ordendeservico.sobrenomec;
        colunaServico.innerHTML = ordendeservico.descricao;
        colunafone.innerHTML = ordendeservico.telefone;
        colunaendereco.innerHTML = "Rua " + ordendeservico.rua + " " + ordendeservico.numero + " " + ordendeservico.bairo + " " + ordendeservico.cidade + " ";
        colunaData.innerHTML = dataFormatada;
        colunaacao.innerHTML = "  <button class='btn btn-danger' onclick='cancelar(this)'>Cancelar</button>" + " "
         + "<button type='button' class='btn btn-success'onclick='finalizar(this)'>Finalizar</button>"+""
         + " <img src='../3721678-whatsapp_108065.png' style='width: 40px; height: 40px;' onclick='whats(this)'></button>"
         
    }));
}
function cancelar(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let id = trelement.childNodes[0].innerHTML;
    let tabela = trelement.parentNode;
    let modalConfirma = new bootstrap.Modal(document.getElementById('modalconfirma'), {});
    modalConfirma.show();
    document.getElementById('btnConfirmarCancelamento').addEventListener('click', function () {
        modalConfirma.hide();
        deleteOrdendeservico(id).then(() => {
            tabela.deleteRow(trelement.rowIndex);
        });
    })
}
function whats(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let fone = trelement.childNodes[3].innerHTML;
    let numeroFormatado = fone.replace(/\D/g, '');
    window.open("https://wa.me/55"+numeroFormatado)
}


function finalizar(element) {
    let tdelement = element.parentNode;
    let trelement = tdelement.parentNode;
    let id = trelement.childNodes[0].innerHTML;
    const modalfinalizacao = new bootstrap.Modal(document.getElementById('modalfinalizacao'), {});
    modalfinalizacao.show();
    document.getElementById('btnConfirmarFinalizacao').addEventListener('click', function () {
        let valorCobrado = document.getElementById('valorCobrado').value;
        if (valorCobrado !== '') {
            modalfinalizacao.hide();
            putfinalizacao(id, valorCobrado).then(() => {
                document.getElementById('valorCobrado').value = "";
                apenasdodia()

            });
        }
    });
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
apenasdodia() 
