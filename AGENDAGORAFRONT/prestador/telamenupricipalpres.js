if (sessionStorage.getItem("token") === null) {
    window.location.href = "../login.html"

}
async function getDiastrabalhados(mes, ano) {
    let url = `http://localhost:8080/agenda/?mes=${mes}&ano=${ano}`
    let response = await fetch(url, {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem('token')
        },
    });

    let diastrabalho = await response.json();

    return diastrabalho;
}
var year, month;
async function escolherAnoMes() {
    [year, month] = document.getElementById('month').value.split('-');
    createCalendar(month, year);
}
function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}
function createCalendar(month, year) {
    let calendar = document.querySelector(".calendar");
    while (calendar.firstChild) {
        calendar.removeChild(calendar.firstChild);
    }

    getDiastrabalhados(month, year).then(diastrabalho => {
        let daysInThisMonth = daysInMonth(month, year);
        let mostrarSalvar = true;
        let mostrarEditar = false;
        console.log('Diastrabalho:', diastrabalho);

        for (let day = 1; day <= daysInThisMonth; day++) {
            let date = new Date(year, month - 1, day);
            let formattedDate = `${(day < 10 ? '0' : '') + day}`;
            let dataBuscada = `${year}-${(month < 1 ? '0' : '') + (month)}-${formattedDate}`;
            let dadosDia = diastrabalho.find(dados => dados.data === dataBuscada);         
            let valorVaga = dadosDia ? dadosDia.quantidade : 0;
            if (valorVaga > 0) {
                mostrarSalvar = false;
                mostrarEditar = true;
            }
            let dayElement = document.createElement("div");
            dayElement.classList.add("day");
            dayElement.classList.add(`month-${month}`);
            dayElement.setAttribute("data-day", day);
            dayElement.setAttribute("data-month", month);
            dayElement.setAttribute("data-year", year);
            dayElement.innerHTML = `
                <div class="day h2">
                    <p><strong>${formattedDate}</strong></p> 
                    <input type="number" class="form-control day-text" min="0" value="${valorVaga}">
                </div>`;
            calendar.appendChild(dayElement);
        }
        let botaoSalvar = document.getElementById("salvar");
        let botaoEditar = document.getElementById("editar");
        botaoSalvar.style.display = mostrarSalvar ? 'block' : 'none';
        botaoEditar.style.display = mostrarEditar ? 'block' : 'none';
    });
}
function obterAnoMesAtual() {
    let dataAtual = new Date();
    year = dataAtual.getFullYear();
    month = dataAtual.getMonth() + 1;
    let mesFormatado = month < 10 ? `0${month}` : month.toString();
    let valorInput = `${year}-${mesFormatado}`;
    document.getElementById('month').value = valorInput;
    createCalendar(month, year);
}
obterAnoMesAtual();
function coletarDados() {
    let dayElements = document.querySelectorAll(`.day.month-${month}`);
    let dadosSalvos = [];
    dayElements.forEach(function (dayElement) {
        let day = dayElement.querySelector("strong").textContent;
        let inputValue = parseInt(dayElement.querySelector(".day-text").value);
        let date = new Date(year, month - 1, parseInt(day));
        let formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1 < 10 ? '0' : '') + (date.getMonth() + 1)}-${(date.getDate() < 10 ? '0' : '') + date.getDate()}`;
        let dados = {
            "data": formattedDate,
            "quantidade": inputValue
        };

        dadosSalvos.push(dados);
    });
    postdias(dadosSalvos).then(() => {
        let myModal = new bootstrap.Modal(document.getElementById('dias'), {});
        myModal.show();
    })
}
function coletarDadoseditado() {
    let dayElements = document.querySelectorAll(`.day.month-${month}`);
    let dadosSalvos = [];
    dayElements.forEach(function (dayElement) {
        let day = dayElement.querySelector("strong").textContent;
        let inputValue = parseInt(dayElement.querySelector(".day-text").value);
        let date = new Date(year, month - 1, parseInt(day));
        let formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1 < 10 ? '0' : '') + (date.getMonth() + 1)}-${(date.getDate() < 10 ? '0' : '') + date.getDate()}`;
        let dados = {
            "data": formattedDate,
            "quantidade": inputValue
        };

        dadosSalvos.push(dados);
    });
    editardias(dadosSalvos, month, year).then(() => {
        let myModal = new bootstrap.Modal(document.getElementById('diaseditado'), {});
        myModal.show();
    })
}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href = "../login.html";
    });
}


let path = window.location.pathname;
function highlightActiveLink() {
    document.querySelectorAll('.nav-link').forEach(function (link) {
        link.classList.remove('ativo');
    });
    if (path.includes("agedarservicocomoprestador.html")) {
        document.getElementById('agendarLink').classList.add('ativo');
    } else if (path.includes("prestadorservicos.html")) {
        document.getElementById('meusServicosLink').classList.add('ativo');
    } else if (path.includes("prestadorservicosdodia.html")) {
        document.getElementById('servicosFazerLink').classList.add('ativo');
    } else if (path.includes("telamenuinicialpres.html")) {
        document.getElementById('calendarioVagasLink').classList.add('ativo');
    }
    else if (path.includes("prestadorconfig.html")) {
        document.getElementById('configLink').classList.add('ativo');
    }
}
window.onload = highlightActiveLink;
function ajustarDataParaUTC(year, month, day) {
    return new Date(Date.UTC(year, month - 1, day));
}