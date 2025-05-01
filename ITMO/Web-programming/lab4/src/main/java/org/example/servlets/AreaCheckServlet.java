package org.example.servlets;

import com.google.gson.Gson;
import db.DataBaseManager;
import entities.Point;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "check", value = "/Check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestBody = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        try {
            logger.info("Полученный JSON: " + requestBody.toString());
            logger.info("entered try block");
            JSONObject jsonRequest = new JSONObject(requestBody.toString());
            logger.info("jsonrequest created from jsonobj");
            Double x = Double.valueOf(jsonRequest.optString("X"));
            Double y = Double.valueOf(jsonRequest.optString("Y"));
            Double r = Double.valueOf(jsonRequest.optString("R"));
            String owner = jsonRequest.optString("OWNER");
            logger.info("data were got from request");

            if (owner == null || owner.isEmpty()) {
                logger.severe("OWNER пустой! Данные не сохраняются.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid request parameters.");
                return;
            }


            if (x == null || y == null || r == null || owner == null || owner.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid request parameters.");
                logger.info("variable is null");
                return;
            }

            Boolean hit = areaConfirm(x, y, r);
//            logger.info("area confirmed");
            Point requestData = new Point(x, y, r, hit, owner);
            DataBaseManager.addData(requestData);
            logger.info("Data added to database" + requestData);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("X", x);
            jsonResponse.put("Y", y);
            jsonResponse.put("R", r);
            jsonResponse.put("HIT", hit);
            jsonResponse.put("OWNER", owner);
            logger.info("jsonResponse created");

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonResponse.toString());
            logger.info("made response to string");

        } catch (NumberFormatException e) {
            logger.severe("Ошибка формата числа: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid number format.");
        } catch (Exception e) {
            logger.severe("Внутренняя ошибка сервера: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An internal error occurred: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username");
        logger.info("login" + login);
        logger.info("request" + request);
        if (login == null || login.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Missing or empty username");
            return;
        }

        try {
            List<Point> requests = DataBaseManager.getRequestsByOwner(login);
            logger.info("Запросы из базы для пользователя" + login + ": " + requests);

            if (requests != null && !requests.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");

                String jsonResponse = convertRequestsToJson(requests);
                response.getWriter().write(jsonResponse);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Error: No requests found for the username");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: Internal server error");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username");
        String idParam = request.getParameter("id");

        if (login == null || login.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Missing or empty username");
            return;
        }

        try {
            if (idParam == null || idParam.trim().isEmpty()) {
                DataBaseManager.deleteAllRequestsByOwner(login);
                response.getWriter().write("{\"message\": \"Все данные успешно удалены\"}");
            } else {
                Long id = Long.parseLong(idParam);
                DataBaseManager.deleteRequestByIdAndOwner(id, login);
                response.getWriter().write("{\"message\": \"Запись успешно удалена\"}");
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Ошибка при удалении\"}");
            e.printStackTrace();
        }
    }

    private String convertRequestsToJson(List<Point> requests) {
        Gson gson = new Gson();
        return gson.toJson(requests);
    }

    public boolean areaConfirm(Double x, Double y, Double r) {
        if ((x >= -r && x <= 0) && (y >= 0 && y <= (double) r / 2)) {
            return true;
        }
        if ((x >= 0 && x <= r) && (y <= ((double) -x / 2 + (double) r / 2) && y >= 0)) {
            return true;
        }
        if ((x * x + y * y) <= (double) (r * r) / 4 && y <= 0 && x <= 0) {
            return true;
        }
        return false;
    }
}