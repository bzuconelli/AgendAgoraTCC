if (sessionStorage.getItem("token") === null) {
    window.location.href = "login.html"

}
var year, month
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
        let dayElement = document.createElement("div");
        dayElement.classList.add("day");
        dayElement.innerHTML = `
            <div class="day h2">
                <p><strong>${day}</strong></p> 
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
    
}
obterAnoMesAtual();
createCalendar(month, year);
function coletarDados() {
    let dayElements = document.querySelectorAll(".day"); 
    let dadosSalvos = {};
    dayElements.forEach(function (dayElement) {
        let dayNumber = parseInt(dayElement.querySelector("strong").textContent);
        let inputValue = parseInt(dayElement.querySelector(".day-text").value);
        dadosSalvos[dayNumber] = inputValue;
    });
    console.log(dadosSalvos);
}
function deslogar() {
    deletetoken().then(() => {
        sessionStorage.clear();
        window.location.href="../login.html"

    })
}

