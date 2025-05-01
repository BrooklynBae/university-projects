function clearGraph() {
    const circles = graph.querySelectorAll("circle");
    console.log("Graph successfully cleaned!");
    circles.forEach(circle => circle.remove());
}

function clearSession() {
    fetch('controller', {  // Путь до сервлета, который очищает сессию
        method: 'DELETE', // Используем метод DELETE
        headers: {
            'Content-Type': 'application/json', // Тип запроса
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка при очистке сессии');
            }
            return response.json(); // Предполагаем, что сервер возвращает JSON
        })
        .then(data => {
            console.log('Сессия успешно очищена:', data);
        })
        .catch(error => {
            console.error('Ошибка при очистке сессии:', error);
        });
}
