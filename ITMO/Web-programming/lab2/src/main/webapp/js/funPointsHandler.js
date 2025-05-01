function drawPoint(dX, dY, r, answer) {
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", dX * 100 + 150)
    circle.setAttribute("cy", - dY * 100 + 150)
    circle.setAttribute("r", 3)

    if (answer) {
        circle.setAttribute("fill", "green")
    } else {
        circle.setAttribute("fill", "red")
    }

    graph.appendChild(circle)
}