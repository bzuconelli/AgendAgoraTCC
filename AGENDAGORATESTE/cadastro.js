document.getElementById('cadastro').addEventListener('submit', function (event) {
  event.preventDefault();
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  document.getElementById("spinner").style.display = 'inline-block';
  document.getElementById("cadastrar").disabled = true;
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var latitude = position.coords.latitude;
      var longitude = position.coords.longitude;
      var coordinatesElement = document.getElementById("coordinates");
      coordinatesElement.textContent = "Latitude: " + latitude + ", Longitude: " + longitude;
    }, function(error) {
      console.error("Erro ao obter localização: " + error.message);
    });
  } else {
    console.error("Navegador não suporta geolocalização.");
  }

  postLogin(email, senha).then(login => {
    let idlogin = login.id
    let nome = document.getElementById('nome').value;
    let telefone = document.getElementById('telefone').value;
    let cidade = document.getElementById('cidade').value;
    let rua = document.getElementById('rua').value;
    let bairro = document.getElementById('bairro').value;
    let numero = document.getElementById('numero').value;

    if (prestador.checked) {
      let checkboxcartao = document.getElementById('cartao');
      if (checkboxcartao.checked == true) {
        var recebecartao = "sim"
      } else {
        recebecartao = "não"
      }
      let checkboxpix = document.getElementById('pix');
      if (checkboxpix.checked == true) {
        var recebepix = "sim"
      } else {
        recebepix = "não"
      }
      let checkboxdinheiro = document.getElementById('dinheiro');
      if (checkboxdinheiro.checked == true) {
        var recebedinheiro = "sim"
      } else {
        recebedinheiro = "não"
      }
      let tipodeserviço = document.querySelector('#servico');
      let servico = tipodeserviço.options[tipodeserviço.selectedIndex].value;
      let qtdvagas = document.getElementById('qtdvagas').value;
      postPrestador(idlogin, nome, telefone, cidade, rua, bairro, numero, recebecartao, recebedinheiro, recebepix, servico, qtdvagas).then(prestador => {
        const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
        myModal.show();
        document.getElementById("cadastro").reset();
        document.getElementById("spinner").style.display = 'none';
        document.getElementById("cadastrar").disabled = false;
        document.getElementById("servico").style.display = "none";
        document.getElementById("teste").style.display = "none";
        document.getElementById("cartao").style.display = "none";
        document.getElementById("pix").style.display = "none";
        document.getElementById("dinheiro").style.display = "none";
        document.getElementById("pag").style.display = "none";
        document.getElementById("labelc").style.display = "none";
        document.getElementById("labelp").style.display = "none";
        document.getElementById("labeld").style.display = "none";
        document.getElementById("labelqtdvagas").style.display = "none";
        document.getElementById("qtdvagas").style.display = "none";
      })
    } else {
      postContratante(idlogin, nome, telefone, cidade, rua, bairro, numero).then(contratante => {
        const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
        myModal.show();
        document.getElementById("cadastro").reset();
        document.getElementById("spinner").style.display = 'none';
        document.getElementById("cadastrar").disabled = false;
      })

    }
  })








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
    document.getElementById("labelqtdvagas").style.display = "block";
    document.getElementById("qtdvagas").style.display = "block";
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
    document.getElementById("labelqtdvagas").style.display = "none";
    document.getElementById("qtdvagas").style.display = "none";
    document.getElementById("qtdvagas").value = "";
    let servicos = document.querySelector('#servico');
    servicos.selectedIndex = 0;
    var checkboxes = document.getElementsByName('formapag');
    for (let checkbox of checkboxes) {
      checkbox.checked = false;
    }
  }
});
function visualizar() {
  let checkbox = document.getElementById('versenha');
  if (checkbox.checked) {
    document.getElementById('senha').type = 'text';
  } else {
    document.getElementById('senha').type = 'password';
  }
}
