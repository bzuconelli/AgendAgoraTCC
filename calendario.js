function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}

function createCalendar() {
    let calendar = document.querySelector(".calendar");

    // Defina o mês e ano desejados
    let month = 2; // Novembro (1 - janeiro, 2 - fevereiro, etc.)
    let year = 2023;

    let daysInThisMonth = daysInMonth(month, year);

    for (let day = 1; day <= daysInThisMonth; day++) {
        let dayElement = document.createElement("div");
        dayElement.classList.add("day");
        dayElement.innerHTML = `
        <div class="day h2">
        <p><strong>${day}</strong></p> 
        <input type="number" class="form-control day-text">
        </div>`;
        calendar.appendChild(dayElement);
    }
}

createCalendar();
