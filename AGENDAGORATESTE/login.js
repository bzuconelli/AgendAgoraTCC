
var loginvalidado = false
document.getElementById('formulario').addEventListener('submit', function (event) {
    event.preventDefault();

    let email = document.getElementById("email").value;
    let senha = document.getElementById("password").value;
    document.getElementById("spinner").style.display = 'inline-block';
    document.getElementById("entrar").disabled = true;

    getLogin().then(logins => logins.forEach(login => {

        console.log(login);


        if (email == login.email && senha == login.senha) {
            loginvalidado = true;
        }
    }));

    if (loginvalidado == true) {
        document.getElementById("spinner").style.display = 'none';
        document.getElementById("entrar").disabled = false;
        window.location.href = "inicio.html"

    } else {
        const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
        myModal.show();
        document.getElementById("entrar").disabled = false;
        document.getElementById("spinner").style.display = 'none';
        document.getElementById("formulario").reset();
        document.getElementById("email").focus;


    }
});

function visualizar() {
    let checkbox = document.getElementById('versenha');
    if (checkbox.checked) {
        document.getElementById('password').type = 'text';
    } else {
        document.getElementById('password').type = 'password';
    }
}







