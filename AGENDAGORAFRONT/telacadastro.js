document.getElementById('cadastro').addEventListener('submit', function (event) {
  event.preventDefault();
  let email = document.getElementById('email').value;
  let senha = document.getElementById('senha').value;
  document.getElementById("spinner").style.display = 'inline-block';
  document.getElementById("cadastrar").disabled = true;
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition(function (position) {
      var latitude = position.coords.latitude;
      var longitude = position.coords.longitude;



      let nome = document.getElementById('nome').value;
      let sobrenome = document.getElementById('sobrenome').value;
      let telefone = document.getElementById('telefone').value;
      let cidade = document.getElementById('cidade').value;
      let rua = document.getElementById('rua').value;
      let bairro = document.getElementById('bairro').value;
      let numero = document.getElementById('numero').value;

      if (prestador.checked) {
        let checkboxcartao = document.getElementById('cartao');
        if (checkboxcartao.checked == true) {
          var recebecartao = "sim";
        } else {
          recebecartao = "não";
        }
        let checkboxpix = document.getElementById('pix');
        if (checkboxpix.checked == true) {
          var recebepix = "sim";
        } else {
          recebepix = "não";
        }
        let checkboxdinheiro = document.getElementById('dinheiro');
        if (checkboxdinheiro.checked == true) {
          var recebedinheiro = "sim";
        } else {
          recebedinheiro = "não";
        }
        let tipodeserviço = document.querySelector('#servico');
        let servico = tipodeserviço.options[tipodeserviço.selectedIndex].value;
        

        postPrestador(nome, telefone, cidade, rua, bairro, numero, recebecartao, recebedinheiro, recebepix, servico, email, senha, sobrenome, latitude,longitude).then(prestador => {
          if (prestador != null) {
          const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
          myModal.show();
          document.getElementById("cadastro").reset();
          document.getElementById("spinner").style.display = 'none';
          document.getElementById("cadastrar").disabled = false;
          document.getElementById("servico").style.display = "none";
          document.getElementById("servicolabel").style.display = "none";
          document.getElementById("cartao").style.display = "none";
          document.getElementById("pix").style.display = "none";
          document.getElementById("dinheiro").style.display = "none";
          document.getElementById("pag").style.display = "none";
          document.getElementById("labelc").style.display = "none";
          document.getElementById("labelp").style.display = "none";
          document.getElementById("labeld").style.display = "none";
          }else{
            const myModal = new bootstrap.Modal(document.getElementById('modaljacadastrado'), {})
            myModal.show();
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("cadastrar").disabled = false;

          }

        });
      } else {
        postContratante(nome, telefone, cidade, rua, bairro, numero, latitude, longitude, email, senha, sobrenome).then(contratante => {
          if (contratante !=null) {
            const myModal = new bootstrap.Modal(document.getElementById('modal'), {})
            myModal.show();
            document.getElementById("cadastro").reset();
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("cadastrar").disabled = false;
          }else{
            const myModal = new bootstrap.Modal(document.getElementById('modaljacadastrado'), {})
            myModal.show();
            document.getElementById("spinner").style.display = 'none';
            document.getElementById("cadastrar").disabled = false;

          }

        });
      }
    }, function (error) {
      console.error("Erro ao obter localização: " + error.message);
    });
  } else {
    console.error("Navegador não suporta geolocalização.");
  }
});


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
    document.getElementById("servicolabel").style.display = "block";
    document.getElementById("cartao").style.display = "block";
    document.getElementById("pix").style.display = "block";
    document.getElementById("dinheiro").style.display = "block";
    document.getElementById("pag").style.display = "block";
    document.getElementById("labelc").style.display = "block";
    document.getElementById("labelp").style.display = "block";
    document.getElementById("labeld").style.display = "block";
    document.getElementById("servico").setAttribute("required", "required");


  }

}
);
contratante.addEventListener("change", (el) => {
  if (contratante.checked) {
    document.getElementById("servico").style.display = "none";
    document.getElementById("servicolabel").style.display = "none";
    document.getElementById("cartao").style.display = "none";
    document.getElementById("pix").style.display = "none";
    document.getElementById("dinheiro").style.display = "none";
    document.getElementById("pag").style.display = "none";
    document.getElementById("labelc").style.display = "none";
    document.getElementById("labelp").style.display = "none";
    document.getElementById("labeld").style.display = "none";
    document.getElementById("servico").removeAttribute("required")
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
