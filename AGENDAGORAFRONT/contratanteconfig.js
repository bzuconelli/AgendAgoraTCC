async function getContratante() {
    let response = await fetch("http://localhost:8080/prestador/", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem
        },
    });

    let contratantes = await response.json();

    return contratantes;
}

getContratante().then(contratantes => contratantes.forEach(contratante => {

    idcontratante = contratante.id;
    document.getElementById('nome').value = contratante.nome;
    document.getElementById('telefone').value = contratante.telefone;
    document.getElementById('cidade').value = contratante.cidade;
    document.getElementById('rua').value = contratante.rua;
    document.getElementById('bairro').value = contratante.bairro;
    document.getElementById('numero').value = contratante.numero;
    document.getElementById('email').value = login.login;
    document.getElementById('senha').value = login.senha;

}));

document.getElementById('editarconfigcontra').addEventListener('submit', function (event) {
    event.preventDefault();
    let id = idcontratante
    let nome = document.getElementById('nome').value;
    let telefone = document.getElementById('telefone').value;
    let cidade = document.getElementById('cidade').value;
    let rua = document.getElementById('rua').value;
    let bairro = document.getElementById('bairro').value;
    let numero = document.getElementById('numero').value;
    let email = document.getElementById('email').value;
    let senha = document.getElementById('senha').value;
    putcontratante(id, nome, telefone, cidade, rua, bairro, numero)



})