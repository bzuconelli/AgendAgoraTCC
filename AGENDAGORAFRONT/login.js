document.getElementById('formulario').addEventListener('submit', function (event) {
    event.preventDefault();
    let email = document.getElementById("email").value;
    let senha = document.getElementById("password").value;
    document.getElementById("spinner").style.display = 'inline-block';
    document.getElementById("entrar").disabled = true;
    postLogin(email, senha).then(login => {

        if (login != "Usuário não autorizado") {
                let token= login.token
                let tipousario= login.niveldeacesso
                sessionStorage.setItem('tipousuario',tipousario)
                sessionStorage.setItem('token',token)       
                  
            if ( tipousario== "contratante") {
                window.location.href = "contratante/telademenuinicialcont.html"
                document.getElementById("spinner").style.display = 'none';
                document.getElementById("entrar").disabled = false;
            } else {
                window.location.href = "prestador/telamenuinicialpres.html"
                document.getElementById("spinner").style.display = 'none';
                document.getElementById("entrar").disabled = false;

            }
        }else{
            const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
            myModal.show();
            document.getElementById("entrar").disabled = false;
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("formulario").reset();
            document.getElementById("email").focus;
        }
    })
});
function visualizar() {
    let checkbox = document.getElementById('versenha');
    if (checkbox.checked) {
        document.getElementById('password').type = 'text';
    } else {
        document.getElementById('password').type = 'password';
    }
}







