<%@ page import="java.util.List" %>
<%@ page import="utils.Point" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
    <script src="js/funClear.js"></script>
    <script src="js/funRequestHandler.js"></script>
    <script src="js/funPointsHandler.js"></script>
    <script src="js/funUpdate.js"></script>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
</head>
<body class="table-page">

<div class="container">
    <div class="table-background">
        <table id="resultsTable">
            <thead>
            <tr>
                <th><h2>X</h2></th>
                <th><h2>Y</h2></th>
                <th><h2>R</h2></th>
                <th><h2>Answer</h2></th>
                <th><h2>ExecutionTime</h2></th>
                <th><h2>ServerTime</h2></th>
            </tr>
            </thead>
            <tbody>
            <%
                // Получаем список points из сессии
                List<Point> points = (List<Point>) session.getAttribute("results");
                if (points != null) {
                    for (Point point : points) {
            %>
            <tr>
                <td><%= point.getX() %></td>
                <td><%= point.getY() %></td>
                <td><%= point.getR() %></td>
                <td><%= point.isAnswer() ? "true" : "false" %></td>
                <td><%= point.getTime() %></td>
                <td><%= point.getScriptTime() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center;">No data available</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <div id="back-button">
            <button class="styled-button" id='prevResult' onClick="window.location.replace('index.jsp');" type="reset" onclick="">Return</button>
        </div>
    </div>
</div>

<div id="points">
</div>
<script>
    document.querySelectorAll('input[name="R"]').forEach(checkbox => {
        checkbox.addEventListener("change", () => {
            console.log("Checkbox state changed.");
            updateGraph();
        });
    });
</script>
</body>
</html>
