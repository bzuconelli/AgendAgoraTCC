var year, month;

function escolherAnoMes() {
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
    let daysInThisMonth = daysInMonth(month, year);
    for (let day = 1; day <= daysInThisMonth; day++) {
        let date = new Date(year, month - 1, day);
        let formattedDate = `${(day < 10 ? '0' : '') + day}`;
        let dayElement = document.createElement("div");
        dayElement.classList.add("day");
        dayElement.classList.add(`month-${month}`); // Adiciona uma classe única para o mês
        dayElement.setAttribute("data-day", day);
        dayElement.setAttribute("data-month", month);
        dayElement.setAttribute("data-year", year);
        dayElement.innerHTML = `
            <div class="day h2">
                <p><strong>${formattedDate}</strong></p> 
                <input type="number" class="form-control day-text" min=0 value="0">
            </div>`;
        calendar.appendChild(dayElement);
    }
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
            "qtdVagas": inputValue
        };

        dadosSalvos.push(dados);
    });

    console.log(dadosSalvos);
}

function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href = "../login.html";
    });
}