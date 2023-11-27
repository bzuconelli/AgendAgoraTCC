if (sessionStorage.getItem("token") === null) {
    window.location.href = "login.html"

}
let nomep = window.sessionStorage.getItem('nomeprestador');
let idos = window.sessionStorage.getItem('idorden');
document.getElementById('prestador').value = nomep;
document.getElementById('avaliacao').addEventListener('submit', function (event) {
    event.preventDefault();
    let ratingElements = document.getElementsByName("rate");
    let nota = 0;
    for (let i = 0; i < ratingElements.length; i++) {
        if (ratingElements[i].checked) {
            nota = ratingElements[i].value;
            break;
        }
    }
    let observacao = document.getElementById('avaliacaot').value;
    putavaliacao(idos, nota, observacao).then(() => {
        const myModal = new bootstrap.Modal(document.getElementById('avaliacaoconcluida'), {});
        myModal.show();
        
        setTimeout(function () {
            if(sessionStorage.getItem('tipousuario')=="contratante"){
                window.location.href = "contratante/contratanteservicos.html"
            }else{
                window.location.href = "prestador/prestadorservicos.html"
            }
            
        }, 2000);
    });
});
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href.replace( "../login.html") 
    })
}