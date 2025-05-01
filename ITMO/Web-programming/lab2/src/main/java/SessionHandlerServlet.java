import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.Point;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<Point> points = (List<Point>) session.getAttribute("results");
        if (points == null) {
            points = new ArrayList<>();
            session.setAttribute("results", points);
        }

        // Считываем данные из тела запроса
        String json = new BufferedReader(new InputStreamReader(request.getInputStream())).lines()
                .collect(Collectors.joining());
        Gson gson = new Gson();
        Point newPoint = gson.fromJson(json, Point.class);

        // Проверяем, существует ли точка
        boolean exists = points.stream().anyMatch(p ->
                p.getX() == newPoint.getX() &&
                        p.getY() == newPoint.getY() &&
                        p.getR() == newPoint.getR()
        );

        if (!exists) {
            points.add(newPoint); // Добавляем точку, если её нет
            session.setAttribute("results", points); // Сохраняем обновленный список
        }

        // Возвращаем обновленный список точек
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(points));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<Point> points = (List<Point>) session.getAttribute("results");
        if (points == null) {
            points = new ArrayList<>();
        }

        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(points));
    }
}
