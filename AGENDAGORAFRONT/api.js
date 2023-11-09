
async function postLogin(email, senha) {
    let response = await fetch("http://localhost:8080/login/", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({

            login: email,
            senha: senha,

        })
    });

    let login = await response;

    return login;
}

async function postPrestador( nome, telefone, cidade, rua, bairro, numero, recebecartao, recebedinheiro, recebepix, servico, login, senha, sobrenome,latitude,longitude) {
    let response = await fetch("http://localhost:8080/prestador/", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({

            login: login,
            senha: senha,
            nome: nome,
            sobrenome: sobrenome,
            telefone: telefone,
            cidade: cidade,
            rua: rua,
            numero: numero,
            bairo: bairro,
            lat: latitude,
            lng: longitude,
            recebepix: recebepix,
            recebecartao: recebecartao,
            dinheiro: recebedinheiro,
            idtiposervico: servico
        })
    });

    let prestador = await response.json();

    return prestador;
}
async function postContratante(nome, telefone, cidade, rua, bairro, numero, latitude, longitude, login, senha, sobrenome) {
    let response = await fetch("http://localhost:8080/contratante/", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
            login: login,
            senha: senha,
            nome: nome,
            sobrenome: sobrenome,
            telefone: telefone,
            cidade: cidade,
            rua: rua,
            numero: numero,
            bairo: bairro,
            lat: latitude,
            lng: longitude
        })
    });

    let contratante = await response.json();

    return contratante;
}
async function getPretadores() {
    let response = await fetch("https://650f142154d18aabfe99d018.mockapi.io/Servicos")
    let prestadores = await response.json();
    console.log(prestadores);
    return prestadores;
}
async function putcontratante(id, nome, idade) {
    let response = await fetch("https://65298d9455b137ddc83efbf7.mockapi.io/contratante/" + id, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
            name: nome,
            age: idade

        })
    });

    let pessoa = await response.json();
    //console.log(pessoas);
    return pessoa;
}




