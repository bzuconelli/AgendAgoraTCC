if (sessionStorage.getItem("token") === null) {
    window.location.href = "login.html"

}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href = "login.html"
    })
}

