async function getLogin() {
    let response = await fetch("https://650a2f88f6553137159c7cc2.mockapi.io/login")
    let logins = await response.json();
    console.log(logins);
    return logins;
}
async function postLogin(email, senha) {
    let response = await fetch("https://650a2f88f6553137159c7cc2.mockapi.io/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({

            email: email,
            senha: senha,

        })
    });

    let login = await response.json();

    return login;
}

async function postPrestador(idlogin, nome, telefone, cidade, rua, bairro, numero, recebecartao, recebedinheiro, recebepix, servico, qtdvagas) {
    let response = await fetch("https://650f142154d18aabfe99d018.mockapi.io/Servicos", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
            idlogin: idlogin,
            nome: nome,
            telefone: telefone,
            cidade: cidade,
            bairro: bairro,
            rua: rua,
            numero: numero,
            tipodeservico: servico,
            aceitacartao: recebecartao,
            aceitapix: recebepix,
            aceitadinheiro: recebedinheiro,
            qtdvagas: qtdvagas,



        })
    });

    let prestador = await response.json();

    return prestador;
}
async function postContratante(idlogin, nome, telefone, cidade, rua, bairro, numero) {
    let response = await fetch("https://65298d9455b137ddc83efbf7.mockapi.io/contratante", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
            idlogin: idlogin,
            nome: nome,
            telefone: telefone,
            cidade: cidade,
            bairro: bairro,
            rua: rua,
            numero: numero,




        })
    });

    let contratante = await response.json();

    return contratante;
}

