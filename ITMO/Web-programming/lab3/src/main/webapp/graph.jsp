<%--<%@ page import="utils.Point" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="java.util.ArrayList" %>--%>
<%--<%@ page import="com.google.gson.Gson" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Lab2</title>--%>
<%--    <script src="js/funClear.js"></script>--%>
<%--    <script src="js/funRequestHandler.js"></script>--%>
<%--    <script src="js/funPointsHandler.js"></script>--%>
<%--    <script src="js/funUpdate.js"></script>--%>
<%--    <script src="js/funValidate.js"></script>--%>
<%--    <link rel="stylesheet" href="style.css">--%>
<%--    <link rel="preconnect" href="https://fonts.googleapis.com">--%>
<%--    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="gif-background-table"></div>--%>
<%--<div class="container">--%>
<%--    <table class="plot-container">--%>
<%--        <tr>--%>
<%--            <td class="left">--%>
<%--                <section class="block plot-section">--%>
<%--                    <div class="graph-background">--%>
<%--                        <div class="graph">--%>
<%--                            <svg height="300" width="300" xmlns="http://www.w3.org/2000/svg" id="graph-svg">--%>
<%--                                <!-- Оси со стрелками -->--%>
<%--                                <line stroke="gray" x1="0" x2="300" y1="150" y2="150"></line>--%>
<%--                                <line stroke="gray" x1="150" x2="150" y1="0" y2="300"></line>--%>
<%--                                <polygon fill="white" points="150,0 144,15 156,15" stroke="white"></polygon>--%>
<%--                                <polygon fill="white" points="300,150 285,156 285,144" stroke="white"></polygon>--%>

<%--                                <!-- Засечки -->--%>
<%--                                <line stroke="gray" x1="200" x2="200" y1="155" y2="145"></line>--%>
<%--                                <line stroke="gray" x1="250" x2="250" y1="155" y2="145"></line>--%>

<%--                                <line stroke="gray" x1="50" x2="50" y1="155" y2="145"></line>--%>
<%--                                <line stroke="gray" x1="100" x2="100" y1="155" y2="145"></line>--%>

<%--                                <line stroke="gray" x1="145" x2="155" y1="100" y2="100"></line>--%>
<%--                                <line stroke="gray" x1="145" x2="155" y1="50" y2="50"></line>--%>

<%--                                <line stroke="gray" x1="145" x2="155" y1="200" y2="200"></line>--%>
<%--                                <line stroke="gray" x1="145" x2="155" y1="250" y2="250"></line>--%>

<%--                                <!-- Подписи к засечкам    -->--%>
<%--                                <text fill="white" x="195" y="140">R/2</text>--%>
<%--                                <text fill="white" x="248" y="140">R</text>--%>

<%--                                <text fill="white" x="40" y="140">-R</text>--%>
<%--                                <text fill="white" x="90" y="140">-R/2</text>--%>

<%--                                <text fill="white" x="160" y="105">R/2</text>--%>
<%--                                <text fill="white" x="160" y="55">R</text>--%>

<%--                                <text fill="white" x="160" y="205">-R/2</text>--%>
<%--                                <text fill="white" x="160" y="255">-R</text>--%>

<%--                                <text fill="white" x="160" y="10">Y</text>--%>
<%--                                <text fill="white" x="290" y="140">X</text>--%>

<%--                                <!-- Прямоугольник -->--%>
<%--                                <rect x="150" y="150" width="100" height="50" fill="black" fill-opacity="0.2" stroke="white"></rect>--%>

<%--                                <!-- Треугольник -->--%>
<%--                                <polygon fill="black" fill-opacity="0.2" points="100,150 150,250 150,150" stroke="white"></polygon>--%>

<%--                                <!-- Четверть круга -->--%>
<%--                                <path d="M 250 150 A 100, 100, 0, 0, 0, 150 50 L 150 150 Z" fill-opacity="0.2" fill="black" stroke="white"></path>--%>
<%--                            </svg>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </section>--%>
<%--            </td>--%>
<%--            <td class="right">--%>
<%--                <h2 class="input">Enter X</h2>--%>
<%--                <input type="number" step="any" min="-3" max="3" id="inputX" placeholder="A value between -3 and 5">--%>

<%--                <h2 class="input">Enter Y</h2>--%>
<%--                <input type="number" step="any" min="-3" max="3" id="inputY" placeholder="A value between -3 and 3">--%>


<%--                <h2 class="input">Chose R</h2>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="R" value="1"> 1--%>
<%--                </label>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="R" value="2"> 2--%>
<%--                </label>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="R" value="3"> 3--%>
<%--                </label>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="R" value="4"> 4--%>
<%--                </label>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="R" value="5"> 5--%>
<%--                </label>--%>

<%--                <div>--%>
<%--                    <button class="styled-button" id="check">Confirm</button>--%>
<%--                </div>--%>

<%--                <div>--%>
<%--                    <button class="styled-button" onclick="window.location.href='table.jsp';">Goto table</button>--%>
<%--                </div>--%>

<%--                <div>--%>
<%--                    <button class="styled-button" id="clean">Clean</button>--%>
<%--                </div>--%>

<%--                <div>--%>
<%--                    <h1 id="result"></h1>--%>
<%--                </div>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</div>--%>

<%--<%--%>
<%--    List<Point> results = (List<Point>) session.getAttribute("results");--%>
<%--    String resultsJson = new Gson().toJson(results != null ? results : new ArrayList<>());--%>
<%--%>--%>

<%--<script charset="utf-8" type="text/javascript">--%>
<%--    const graph = document.getElementById("graph-svg")--%>
<%--    const results =  <%= resultsJson %>;--%>

<%--    graph.addEventListener("click", event => {--%>
<%--        let flagFromGraph = true;--%>
<%--        const rect = graph.getBoundingClientRect();--%>
<%--        const gX = (event.clientX - rect.left - 150) / 100;--%>
<%--        const gY = (event.clientY - rect.top - 150) / 100 * -1;--%>

<%--        console.log(`Mouse X: ${event.clientX}, Mouse Y: ${event.clientY}`);--%>
<%--        console.log(`Graph X: ${gX}, Graph Y: ${gY}`);--%>

<%--        const selectedRs = getSelectedRs();--%>

<%--        selectedRs.forEach(r => {--%>
<%--            sendRequest(gX, gY, String(r), flagFromGraph); // Отправляем запрос для каждого R--%>
<%--        });--%>
<%--    });--%>

<%--    document.getElementById("check").onclick = function () {--%>
<%--        let flagFromGraph = false;--%>
<%--        let x = document.getElementById("inputX").value;--%>
<%--        let y = document.getElementById("inputY").value;--%>
<%--        const selectedRs = getSelectedRs();--%>
<%--        const resultElement = document.getElementById("result");--%>

<%--        if (!checkX(x) && checkY(y)) {--%>
<%--            document.getElementById("result").innerText = "Uncorrected X";--%>
<%--            resultElement.style.visibility = 'visible';--%>
<%--        } else if (!checkY(y) && checkX(x)) {--%>
<%--            document.getElementById("result").innerText = "Uncorrected Y";--%>
<%--            resultElement.style.visibility = 'visible';--%>
<%--        } else if (!checkX(x) && !checkY(y)) {--%>
<%--            document.getElementById("result").innerText = "Uncorrected X and Y";--%>
<%--            resultElement.style.visibility = 'visible';--%>
<%--        } else {--%>
<%--            document.getElementById("result").innerText = "";--%>
<%--            resultElement.style.visibility = 'hidden';--%>
<%--            selectedRs.forEach(r => {--%>
<%--                sendRequest(x, y, r, flagFromGraph);--%>
<%--            });--%>
<%--        }--%>
<%--    };--%>

<%--    document.getElementById("clean").onclick = function () {--%>
<%--        clearGraph();--%>
<%--        clearSession();--%>
<%--    }--%>

<%--    document.querySelectorAll('input[name="R"]').forEach(checkbox => {--%>
<%--        checkbox.addEventListener("change", updateGraph);--%>
<%--    });--%>

<%--    function updateGraph() {--%>
<%--        clearGraph(); // Очищаем график--%>

<%--        const selectedRs = getSelectedRs();--%>
<%--        results--%>
<%--            .filter(result => selectedRs.includes(result.r.toString()))--%>
<%--            .forEach(result => drawPoint(result.x, result.y, result.r, result.answer));--%>
<%--    }--%>
<%--    updateGraph();--%>
<%--    // Инициализация событий на чекбоксах и вызов updateGraph--%>
<%--    document.querySelectorAll('input[name="R"]').forEach(checkbox => {--%>
<%--        checkbox.addEventListener("change", updateGraph);--%>
<%--    });--%>

<%--    // Первоначальное обновление графика при загрузке страницы--%>
<%--    updateGraph();--%>

<%--    window.onload = clearGraph;--%>

<%--    function getSelectedRs() {--%>
<%--        const rCheckboxes = document.querySelectorAll('input[name="R"]:checked');--%>
<%--        return Array.from(rCheckboxes).map(checkbox => checkbox.value); // Преобразуем NodeList в массив и берем значения--%>
<%--    }--%>
<%--</script>--%>
<%--<%--%>
<%--    double x = 0;--%>
<%--    double y = 0;--%>
<%--    boolean answer = false;--%>
<%--    List<Point> results = (List<Point>) session.getAttribute("results");--%>
<%--    if (results != null) {--%>
<%--        for (Point result : results) {--%>
<%--            x = result.x;--%>
<%--            y = result.y;--%>
<%--            answer = result.answer;--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>
<%--            <script>drawPoint(<%=x%>, <%=y%>, <%=answer%>)</script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page import="utils.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab2</title>
    <script src="js/funClear.js"></script>
    <script src="js/funRequestHandler.js"></script>
    <script src="js/funPointsHandler.js"></script>
    <script src="js/funUpdate.js"></script>
    <script src="js/funValidate.js"></script>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
</head>
<body>
<div class="gif-background-table"></div>
<div class="container">
    <table class="plot-container">
        <tr>
            <td class="left">
                <section class="block plot-section">
                    <div class="graph-background">
                        <div class="graph">
                            <svg height="300" width="300" xmlns="http://www.w3.org/2000/svg" id="graph-svg">
                                <!-- Оси со стрелками -->
                                <line stroke="gray" x1="0" x2="300" y1="150" y2="150"></line>
                                <line stroke="gray" x1="150" x2="150" y1="0" y2="300"></line>
                                <polygon fill="white" points="150,0 144,15 156,15" stroke="white"></polygon>
                                <polygon fill="white" points="300,150 285,156 285,144" stroke="white"></polygon>

                                <!-- Засечки -->
                                <line stroke="gray" x1="200" x2="200" y1="155" y2="145"></line>
                                <line stroke="gray" x1="250" x2="250" y1="155" y2="145"></line>

                                <line stroke="gray" x1="50" x2="50" y1="155" y2="145"></line>
                                <line stroke="gray" x1="100" x2="100" y1="155" y2="145"></line>

                                <line stroke="gray" x1="145" x2="155" y1="100" y2="100"></line>
                                <line stroke="gray" x1="145" x2="155" y1="50" y2="50"></line>

                                <line stroke="gray" x1="145" x2="155" y1="200" y2="200"></line>
                                <line stroke="gray" x1="145" x2="155" y1="250" y2="250"></line>

                                <!-- Подписи к засечкам    -->
                                <text fill="white" x="195" y="140">R/2</text>
                                <text fill="white" x="248" y="140">R</text>

                                <text fill="white" x="40" y="140">-R</text>
                                <text fill="white" x="90" y="140">-R/2</text>

                                <text fill="white" x="160" y="105">R/2</text>
                                <text fill="white" x="160" y="55">R</text>

                                <text fill="white" x="160" y="205">-R/2</text>
                                <text fill="white" x="160" y="255">-R</text>

                                <text fill="white" x="160" y="10">Y</text>
                                <text fill="white" x="290" y="140">X</text>

                                <!-- Прямоугольник -->
                                <rect x="150" y="150" width="100" height="50" fill="black" fill-opacity="0.2" stroke="white"></rect>

                                <!-- Треугольник -->
                                <polygon fill="black" fill-opacity="0.2" points="100,150 150,250 150,150" stroke="white"></polygon>

                                <!-- Четверть круга -->
                                <path d="M 250 150 A 100, 100, 0, 0, 0, 150 50 L 150 150 Z" fill-opacity="0.2" fill="black" stroke="white"></path>
                            </svg>
                        </div>
                    </div>
                </section>
            </td>
            <td class="right">
                <h2 class="input">Enter X</h2>
                <input type="number" step="any" min="-3" max="3" id="inputX" placeholder="A value between -3 and 5">

                <h2 class="input">Enter Y</h2>
                <input type="number" step="any" min="-3" max="3" id="inputY" placeholder="A value between -3 and 3">


                <h2 class="input">Chose R</h2>
                <label>
                    <input type="checkbox" name="R" value="1"> 1
                </label>
                <label>
                    <input type="checkbox" name="R" value="2"> 2
                </label>
                <label>
                    <input type="checkbox" name="R" value="3"> 3
                </label>
                <label>
                    <input type="checkbox" name="R" value="4"> 4
                </label>
                <label>
                    <input type="checkbox" name="R" value="5"> 5
                </label>

                <div>
                    <button class="styled-button" id="check">Confirm</button>
                </div>

                <div>
                    <button class="styled-button" onclick="window.location.href='table.jsp';">Goto table</button>
                </div>

                <div>
                    <button class="styled-button" id="clean">Clean</button>
                </div>

                <div>
                    <h1 id="result"></h1>
                </div>
            </td>
        </tr>
    </table>
</div>

<script charset="utf-8" type="text/javascript">
    const graph = document.getElementById("graph-svg")

    graph.addEventListener("click", event => {
        let flagFromGraph = true;
        const rect = graph.getBoundingClientRect();
        const gX = (event.clientX - rect.left - 150) / 100;
        const gY = (event.clientY - rect.top - 150) / 100 * -1;

        console.log(`Mouse X: ${event.clientX}, Mouse Y: ${event.clientY}`);
        console.log(`Graph X: ${gX}, Graph Y: ${gY}`);

        const selectedRs = getSelectedRs();

        selectedRs.forEach(r => {
            sendRequest(gX, gY, String(r), flagFromGraph); // Отправляем запрос для каждого R
        });
    });

    document.getElementById("check").onclick = function () {
        let flagFromGraph = false;
        let x = document.getElementById("inputX").value;
        let y = document.getElementById("inputY").value;
        const selectedRs = getSelectedRs();
        const resultElement = document.getElementById("result");

        if (!checkX(x) && checkY(y)) {
            document.getElementById("result").innerText = "Uncorrected X";
            resultElement.style.visibility = 'visible';
        } else if (!checkY(y) && checkX(x)) {
            document.getElementById("result").innerText = "Uncorrected Y";
            resultElement.style.visibility = 'visible';
        } else if (!checkX(x) && !checkY(y)) {
            document.getElementById("result").innerText = "Uncorrected X and Y";
            resultElement.style.visibility = 'visible';
        } else {
            document.getElementById("result").innerText = "";
            resultElement.style.visibility = 'hidden';
            selectedRs.forEach(r => {
                sendRequest(x, y, r, flagFromGraph);
            });
        }
    };

    document.getElementById("clean").onclick = function () {
        clearGraph();
        clearSession();
    }

    document.querySelectorAll('input[name="R"]').forEach(checkbox => {
        checkbox.addEventListener("change", updateGraph);
    });

    window.onload = clearGraph;

    function getSelectedRs() {
        const rCheckboxes = document.querySelectorAll('input[name="R"]:checked');
        return Array.from(rCheckboxes).map(checkbox => checkbox.value); // Преобразуем NodeList в массив и берем значения
    }
</script>
<%
    double x = 0;
    double y = 0;
    boolean answer = false;
    ArrayList<Point> results = (ArrayList<Point>) request.getSession().getAttribute("results");
    if (results != null) {
        for (Point result : results) {
            x = result.x;
            y = result.y;
            answer = result.answer;
        }
    }
%>
<script>drawPoint(<%=x%>, <%=y%>, <%=answer%>)</script>
</body>
</html>
