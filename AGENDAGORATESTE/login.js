document.getElementById('formulario').addEventListener('submit', function (event) {
    event.preventDefault();

    let email = document.getElementById("email").value;
    let senha = document.getElementById("password").value;

    getLogin().then(logins => logins.forEach(login => {
        console.log(login)
        if (email == login.email && senha == login.senha) {
            window.location.href = "inicio.html"
        } else {
            alert("errou")

        }
    }));
})
function vizualizar() {
    let checkbox = document.getElementById('versenha');
    if (checkbox.checked) {
        document.getElementById('password').type = 'text';
    } else {
        document.getElementById('password').type = 'password';
    }
}







