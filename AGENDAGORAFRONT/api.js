
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

    if (response.status === 200) {

        const token = await response.text();
        return token;



    } else if (response.status === 401) {
        return "Usuário não autorizado";
    } else {
        throw new Error("Falha na autenticação");
    }

}






async function postPrestador(nome, telefone, cidade, rua, bairro, numero, recebecartao, recebedinheiro, recebepix, servico, login, senha, sobrenome, latitude, longitude) {
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

async function putcontratante(id, nome, sobrenome, telefone, cidade, rua, bairro, numero, email, senha, latitude, longitude, idendereco) {
    let response = await fetch("http://localhost:8080/contratante/put/" + id, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },

        body: JSON.stringify({
            id: id,
            nome: nome,
            sobrenome: sobrenome,
            telefone: telefone,
            idendereco: idendereco,
            rua: rua,
            cidade: cidade,
            bairo: bairro,
            numero: numero,
            lat: latitude,
            lng: longitude,
            login: email,
            senha: senha

        })
    });

    let pessoa = await response.json();

    return pessoa;
}




