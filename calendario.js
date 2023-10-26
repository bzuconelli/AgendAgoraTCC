function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}
var ano, mes
function escolherAnoMes() {
    [ano, mes] = document.getElementById('month').value.split('-');
    console.log(mes, ano);
    createCalendar(mes,ano);  

}




function createCalendar(month,year) {
    let calendar = document.querySelector(".calendar");


    

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



