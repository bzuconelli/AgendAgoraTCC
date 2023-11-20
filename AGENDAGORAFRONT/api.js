
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

        const login = await response.json();
        return login;



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

    if (response.status === 400) { return null; }
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
    if (response.status === 400) { return null; }

    let contratante = await response.json();
    return contratante

}
async function putcontratante(id, nome, sobrenome, telefone, cidade, rua, bairro, numero, email, senha, latitude, longitude, idendereco) {
    let response = await fetch("http://localhost:8080/contratante/" + id, {
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
async function getPretadores(data, tipoPagamento, tipoServico, distancia, lat, lng) {
    let url = `http://localhost:8080/prestador/?data=${data}&tipopag=${tipoPagamento}&tiposervico=${tipoServico}&distancia=${distancia}&lat=${lat}&lng=${lng}`;
    let response = await fetch(url, {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },
    });
    if (response.status === 404) { return null; }

    let prestadores = await response.json();

    return prestadores;
}
async function postOrdendeservico(idprestador, idcontratante, data, formadepagamento, servicoaserrealizado, tipoServico) {
    let response = await fetch("http://localhost:8080/ordendeservico/", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },

        body: JSON.stringify({
            descricao: servicoaserrealizado,
            data: data,
            idtiposervico: tipoServico,
            formapagamento: formadepagamento,
            idprerst: idprestador,
            idcontratante: idcontratante
        })
    });
    if (response.status === 400) { return null; }

    let ordendeservico = await response.json();
    return ordendeservico
}
async function deleteOrdendeservico(id) {
    let response = await fetch("http://localhost:8080/ordendeservico/" + id, {
        method: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('token')
        },
        
    })
    
}




