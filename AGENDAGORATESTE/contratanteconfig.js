async function getContratante() {
    let response = await fetch("https://65298d9455b137ddc83efbf7.mockapi.io/contratante")
    let contratantes = await response.json();
    console.log(contratantes);
    return contratantes;
}
var idcontratante;
getContratante().then(contratantes => contratantes.forEach(contratante => {
    if (contratante.id == 8) {
        idcontratante = contratante.id;
        document.getElementById('nome').value = contratante.nome;
        document.getElementById('telefone').value = contratante.telefone;
        document.getElementById('cidade').value = contratante.cidade;
        document.getElementById('rua').value = contratante.rua;
        document.getElementById('bairro').value = contratante.bairro;
        document.getElementById('numero').value = contratante.numero;


    }
    getLogin().then(logins => logins.forEach(login => {
        if (login.id == 10) {
            document.getElementById('email').value = login.email;
            document.getElementById('senha').value = login.senha;

        }

    }));
}));

document.getElementById('editarconfigcontra').addEventListener('submit', function (event) {
    event.preventDefault();
    let id= idcontratante
    let nome = document.getElementById('nome').value;
    let telefone = document.getElementById('telefone').value;
    let cidade = document.getElementById('cidade').value;
    let rua = document.getElementById('rua').value;
    let bairro = document.getElementById('bairro').value;
    let numero = document.getElementById('numero').value;
    let email = document.getElementById('email').value;
    let senha = document.getElementById('senha').value;
    putcontratante(id,nome,telefone,cidade,rua,bairro,numero)



})