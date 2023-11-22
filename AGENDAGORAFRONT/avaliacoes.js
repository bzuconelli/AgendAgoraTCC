let nomep = window.localStorage.getItem('nomeprestador');
let idos = window.localStorage.getItem('idorden');
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
        const modalConfirma = new bootstrap.Modal(document.getElementById('avaliacaoconcluida'), {});
        modalConfirma.show();
        
        setTimeout(function () {
            window.location.href = "contratanteservicos.html"
        }, 2000);
    });
});