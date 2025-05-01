function clearGraph() {
    const circles = graph.querySelectorAll("circle");
    console.log("Graph successfully cleaned!");
    circles.forEach(circle => circle.remove());
}

function clearSession() {
    fetch('controller', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
}
