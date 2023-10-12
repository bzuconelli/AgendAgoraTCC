async function getLogin() {
    let response = await fetch("https://650a2f88f6553137159c7cc2.mockapi.io/login")
    let logins = await response.json();
    console.log(logins);
    return logins;
}
 
