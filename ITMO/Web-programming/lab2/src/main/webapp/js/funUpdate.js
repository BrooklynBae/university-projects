function updateGraph(results) {
    document.querySelectorAll('input[name="R"]').forEach(checkbox => {
        checkbox.addEventListener("change", () => {
            clearGraph();
            const selectedRs = getSelectedRs();
            results
                .filter(result => selectedRs.includes(result.r.toString()))
                .forEach(result => drawPoint(result.x, result.y, result.r, result.answer));
        });
    });
}