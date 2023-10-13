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
 
