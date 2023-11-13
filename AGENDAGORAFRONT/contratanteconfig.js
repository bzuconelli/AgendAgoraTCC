async function getContratante() {
    let response = await fetch("http://localhost:8080/contratante/pequisar", {
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


getContratante().then(contratante => {

    let id = contratante.id
    let idendereco = contratante.idendereco
    document.getElementById('nome').value = contratante.nome;
    document.getElementById('sobrenome').value = contratante.sobrenome;
    document.getElementById('telefone').value = contratante.telefone;
    document.getElementById('cidade').value = contratante.cidade;
    document.getElementById('rua').value = contratante.rua;
    document.getElementById('bairro').value = contratante.bairo;
    document.getElementById('numero').value = contratante.numero;
    document.getElementById('email').value = contratante.login;
    document.getElementById('senha').value = contratante.senha;
    document.getElementById('editarconfigcontra').addEventListener('submit', function (event) {
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
                    putcontratante(id, nome, sobrenome, telefone, cidade, rua, bairro, numero, email, senha, latitude, longitude, idendereco).then(() => {
                        const myModal = new bootstrap.Modal(document.getElementById('modalconfigcont'), {})
                        myModal.show();
                        document.getElementById("spinner").style.display = 'none';
                        
                        setTimeout(function () {
                            window.location.href = "login.html";
                        }, 5000);
                        


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

