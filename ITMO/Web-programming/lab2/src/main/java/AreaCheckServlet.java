import com.google.gson.Gson;
import org.json.JSONObject;
import utils.Point;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AreaCheckServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long timer = System.nanoTime();
        HttpSession session = req.getSession();

        List<Point> points = (List<Point>) session.getAttribute("results");
        if (points == null) {
            points = new ArrayList<>();
            session.setAttribute("results", points);
        }

        try {
            String jsonData = "";
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonData += line + "\n";
                }
            }

            JSONObject jsonObject = new JSONObject(jsonData);
            Boolean flag = jsonObject.getBoolean("flag");

            double x_true;
            double y_true;
            double r;
            double x;
            double y;
            if (flag) {
                x_true = jsonObject.getDouble("x");
                y_true = jsonObject.getDouble("y");
                r = jsonObject.getDouble("r");
                x = x_true * r;
                y = y_true * r;
            } else {
                x = jsonObject.getDouble("x");
                y = jsonObject.getDouble("y");
                r = jsonObject.getDouble("r");
                x_true = x/r;
                y_true = y/r;
            }

            logger.info("Received values from request: x=" + x + ", y=" + y + ", r=" + r);

            boolean exists = points.stream().anyMatch(p ->
                    p.getX() == x && p.getY() == y && p.getR() == r
            );

            if (!exists) {
                boolean answer = checkHit(x, y, r);
                logger.info(x+ " "+ y+ " "+ r+ " " + answer);
                long currentTime = System.nanoTime() - timer;
                String scriptTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                Point point = new Point(x_true, y_true, r, answer, currentTime, scriptTime);
                points.add(point);
                session.setAttribute("results", points);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new Gson().toJson(point));
            }


        } catch (Exception e) {
            logger.warning("Error processing request: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid request\"}");
        }
    }

    private static boolean checkHit(double x, double y, double r) {
        System.out.println("КООРДИНАТЫ: " + x + " " + y + " " + r);
        boolean inCircle = (x * x + y * y <= r * r) && (x >= 0) && (y >= 0) && (x <= r) && (y <= r);

        boolean inRectangle = (x >= 0) && (x <= r) && (y <= 0) && (y >= -r / 2);

        boolean inTriangle = x <= 0 && y <= 0 && y >= x * -2 - r;

        return inCircle || inRectangle || inTriangle;
    }
}
