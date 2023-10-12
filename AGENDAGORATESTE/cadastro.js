document.getElementById('cadastro').addEventListener('submit', function (event) {
  event.preventDefault();
  let nome = document.getElementById('nome').value;
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  let telefone = document.getElementById('telefone').value;
  let cidade = document.getElementById('cidade').value;
  let rua = document.getElementById('rua').value;
  let bairro = document.getElementById('bairro').value;
  let numero = document.getElementById('numero').value;
  
    let checkbox = document.getElementById('cartao');
    //console.log(checkbox);

    console.log(checkbox.checked);


  




})

let handlePhone = (event) => {
  let input = event.target
  input.value = phoneMask(input.value)
}

let phoneMask = (value) => {
  if (!value) return ""
  value = value.replace(/\D/g, '')
  value = value.replace(/(\d{2})(\d)/, "($1) $2")
  value = value.replace(/(\d)(\d{4})$/, "$1-$2")
  return value
}

var prestador = document.querySelector("#prestador");
var contratante = document.querySelector("#contratante");
prestador.addEventListener("change", (el) => {
  if (prestador.checked) {
    document.getElementById("servico").style.display = "block";
    document.getElementById("teste").style.display = "block";
    document.getElementById("cartao").style.display = "block";
    document.getElementById("pix").style.display = "block";
    document.getElementById("dinheiro").style.display = "block";
    document.getElementById("pag").style.display = "block";
    document.getElementById("labelc").style.display = "block";
    document.getElementById("labelp").style.display = "block";
    document.getElementById("labeld").style.display = "block";
    //document.getElementById("contratante").style.display = "none;"
    //document.getElementById("ps").style.display = "none;"
  }

}
);
contratante.addEventListener("change", (el) => {
  if (contratante.checked) {
    document.getElementById("servico").style.display = "none";
    document.getElementById("teste").style.display = "none";
    document.getElementById("cartao").style.display = "none";
    document.getElementById("pix").style.display = "none";
    document.getElementById("dinheiro").style.display = "none";
    document.getElementById("pag").style.display = "none";
    document.getElementById("labelc").style.display = "none";
    document.getElementById("labelp").style.display = "none";
    document.getElementById("labeld").style.display = "none";
    let servicos = document.querySelector('#servico');
    servicos.selectedIndex = 0;
    var checkboxes = document.getElementsByName('formapag');
    for (let checkbox of checkboxes) {
      checkbox.checked = false;
    }
  }
});
function vizualizar() {
  let checkbox = document.getElementById('versenha');
  if (checkbox.checked) {
    document.getElementById('senha').type = 'text';
  } else {
    document.getElementById('senha').type = 'password';
  }
}
