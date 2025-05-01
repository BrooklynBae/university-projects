function sendRequest(x, y, r, flag){
    const data = {
        x: x,
        y: y,
        r: r,
        flag: flag
    }
    fetch("controller", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        return response.text()
    }).then(result => {
        const jsonFormat = JSON.parse(result)
        console.log("jsonFormat:", jsonFormat);

        drawPoint(x, y, r, jsonFormat.answer)
    })
}


