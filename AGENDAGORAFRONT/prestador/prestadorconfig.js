if (sessionStorage.getItem("token") === null) {
    window.location.href = "../login.html"

}
async function getPrestador() {
    let response = await fetch("http://localhost:8080/prestador/prestador", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem('token')
        },
    });

    let prestador = await response.json();

    return prestador;
}
getPrestador().then(prestador => {
    let id = prestador.id
    let idendereco = prestador.idendereco
    document.getElementById('nome').value = prestador.nome;
    document.getElementById('sobrenome').value = prestador.sobrenome;
    document.getElementById('telefone').value = prestador.telefone;
    document.getElementById('cidade').value = prestador.cidade;
    document.getElementById('rua').value = prestador.rua;
    document.getElementById('bairro').value = prestador.bairo;
    document.getElementById('numero').value = prestador.numero;
    document.getElementById('email').value = prestador.login;
    document.getElementById('senha').value = prestador.senha;
    if (prestador.recebepix == "sim") {
        let checkboxpix = document.getElementById('pix');
        checkboxpix.checked = true
    }
    if (prestador.recebecartao == "sim") {
        let checkboxcartao = document.getElementById('cartao');
        checkboxcartao.checked = true
    }
    if (prestador.dinheiro == "sim") {
        let checkboxdinheiro = document.getElementById('dinheiro');
        checkboxdinheiro.checked = true
    }
    let servicoSelect = document.getElementById("servico");
    for (let i = 0; i < servicoSelect.options.length; i++) {
        if (servicoSelect.options[i].value == prestador.idtiposervico) {
            servicoSelect.options[i].selected = true;
            break;
        }
    }
    document.getElementById('editarconfigpresta').addEventListener('submit', function (event) {
        event.preventDefault();
        const modalConfirma = new bootstrap.Modal(document.getElementById('modalconfirma'), {});
        modalConfirma.show();
        document.getElementById('btnConfirmarAlteracoes').addEventListener('click', function () {
            modalConfirma.hide();
            document.getElementById("spinner").style.display = 'inline-block';
            document.getElementById("Salvarconfig").disabled = true;
            if ("geolocation" in navigator) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;
                    let nome = document.getElementById('nome').value;
                    let sobrenome = document.getElementById('sobrenome').value;
                    let telefone = document.getElementById('telefone').value;
                    let cidade = document.getElementById('cidade').value;
                    let rua = document.getElementById('rua').value;
                    let bairro = document.getElementById('bairro').value;
                    let numero = document.getElementById('numero').value;
                    let email = document.getElementById('email').value;
                    let senha = document.getElementById('senha').value;
                    let checkboxcartao = document.getElementById('cartao');
                    if (checkboxcartao.checked == true) {
                        var recebecartao = "sim";
                    } else {
                        recebecartao = "não";
                    }
                    let checkboxpix = document.getElementById('pix');
                    if (checkboxpix.checked == true) {
                        var recebepix = "sim";
                    } else {
                        recebepix = "não";
                    }
                    let checkboxdinheiro = document.getElementById('dinheiro');
                    if (checkboxdinheiro.checked == true) {
                        var recebedinheiro = "sim";
                    } else {
                        recebedinheiro = "não";
                    }
                    let tipodeserviço = document.querySelector('#servico');
                    let servico = tipodeserviço.options[tipodeserviço.selectedIndex].value;
                    putprestador(id, nome, sobrenome, telefone, cidade, rua, bairro, numero, email, senha, latitude, longitude, idendereco, recebecartao, recebedinheiro, recebepix, servico).then(() => {
                        document.getElementById("spinner").style.display = 'none';
                        window.location.href = "../login.html";
                    })
                }, function (error) {
                    console.error("Erro ao obter localização: " + error.message);
                });
            } else {
                console.error("Navegador não suporta geolocalização.");
            }
        })
    });
});
function visualizar() {
    let checkbox = document.getElementById('versenha');
    if (checkbox.checked) {
        document.getElementById('senha').type = 'text';
    } else {
        document.getElementById('senha').type = 'password';
    }
}
let handlePhone = (event) => {
    let input = event.target
    input.value = phoneMask(input.value)
}
let phoneMask = (value) => {
    if (!value) return ""
    value = value.replace(/\D/g, '')
    value = value.replace(/(\d{2})(\d)/, "($1) $2")
    value = value.replace(/(\d)(\d{4})$/, "$1-$2")
    return value
}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href = "../login.html"
    })
}
let num = (event) => {
    let input = event.target
    input.value = input.value.replace(/\D/g, '');

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
        let configImagem = document.getElementById('configImagem');
        configImagem.src = "../Bazaart_20231122_091830_231_cinza.png-removebg-preview.png";        
        configImagem.classList.add('imagem-cinza');
    }
}
window.onload = highlightActiveLink;
