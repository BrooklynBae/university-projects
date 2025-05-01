function checkX(x) {
    if (x === "" || x === null || x === undefined) {
        return false;
    }

    let parsedX = parseFloat(x);
    if (isNaN(parsedX)) {
        return false;
    }

    const MAX_FLOAT = Number.MAX_VALUE;
    const MIN_FLOAT = -Number.MAX_VALUE;

    if (parsedX > MAX_FLOAT || parsedX < MIN_FLOAT) {
        return false;
    }

    return parsedX > -3 && parsedX < 5;
}

function checkY(y) {
    if (y === "" || y === null || y === undefined) {
        return false;
    }

    let parsedY = parseFloat(y);
    if (isNaN(parsedY)) {
        return false;
    }

    const MAX_FLOAT = Number.MAX_VALUE;
    const MIN_FLOAT = -Number.MAX_VALUE;

    if (parsedY > MAX_FLOAT || parsedY < MIN_FLOAT) {
        return false;
    }

    return parsedY > -3 && parsedY < 3;
}