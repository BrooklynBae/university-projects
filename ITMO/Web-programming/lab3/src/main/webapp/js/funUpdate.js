// function updateGraph() {
//     clearGraph(); // Очищаем график
//
//     const selectedRs = getSelectedRs();
//     const results = JSON.parse(sessionStorage.getItem("results")) || [];
//
//     results
//         .filter(result => selectedRs.includes(result.r.toString()))
//         .forEach(result => drawPoint(result.x, result.y, result.r, result.answer));
// }
function updateGraph(results) {
    // Находим все чекбоксы "R" и добавляем обработчик для каждого из них
    document.querySelectorAll('input[name="R"]').forEach(checkbox => {
        checkbox.addEventListener("change", () => {
            clearGraph(); // Очищаем график

            const selectedRs = getSelectedRs();
            //const results = JSON.parse(sessionStorage.getItem("results")) || [];

            results
                .filter(result => selectedRs.includes(result.r.toString()))
                .forEach(result => drawPoint(result.x, result.y, result.r, result.answer));
        });
    });
}
