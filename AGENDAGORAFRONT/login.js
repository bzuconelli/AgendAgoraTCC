

document.getElementById('formulario').addEventListener('submit', function (event) {
    event.preventDefault();
    let loginvalidado;
    let email = document.getElementById("email").value;
    let senha = document.getElementById("password").value;
    document.getElementById("spinner").style.display = 'inline-block';
    document.getElementById("entrar").disabled = true;

    

    let retornoLogin= postLogin(email,senha);
    console.log(retornoLogin);
    
    
    
    
    /*.then(login => {

        console.log(login);


        if (email == login.email && senha == login.senha) {
            loginvalidado = true;

        }
        if (loginvalidado == true) {
            window.location.href = "telademenuinicialcont.html"
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("entrar").disabled = false;
        } else {
            loginvalidado = false;
        }
        if (loginvalidado == false) {
            window.location.href = "login.html"
            const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
            myModal.show();
            document.getElementById("entrar").disabled = false;
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("formulario").reset();
            document.getElementById("email").focus;
        }
    })*/



});


function visualizar() {
    let checkbox = document.getElementById('versenha');
    if (checkbox.checked) {
        document.getElementById('password').type = 'text';
    } else {
        document.getElementById('password').type = 'password';
    }
}







