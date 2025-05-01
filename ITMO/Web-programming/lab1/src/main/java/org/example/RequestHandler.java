package org.example;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class RequestHandler {
    private static final String RESULT_JSON = """
            {
                "isHit": %b,
                "executionTime": "%s",
                "serverTime": "%s"
            }
            """;

    private static final String HTTP_ERROR = """
        HTTP/1.1 400 Bad Request
        Content-Type: application/json
        Content-Length: %d
        
        %s
        """;

    private static final String HTTP_RESPONSE = """
        HTTP/1.1 200 OK
        Content-Type: application/json
        Content-Length: %d
        
        %s
        """;
    private static final String ERROR_JSON = """
        {
            "reason": "%s"
        }
        """;

    public static void handleRequest(long startTime) throws IOException {
        HashMap<String, String> query = Parser.parse(readRequestBody());

        double x;
        int y = 0;
        int r = 0;
        BigDecimal xValue;
        try {
             xValue = new BigDecimal(query.get("x"));
             x = xValue.setScale(2, RoundingMode.HALF_UP).floatValue();
        } catch (Exception e) {
            throw new IOException("incorrect X");
        }
        try {
             y = Integer.parseInt(query.get("y"));
        } catch (Exception e) {
            throw new IOException("incorrect Y");
        }
        try {
             r = Integer.parseInt(query.get("r"));
        } catch (Exception e) {
            throw new IOException("incorrect R");
        }

        if (x < -3 || x > 3 || y < -3 || y > 5 || r < 1 || r > 5) {
            throw new IOException("You have invalid data");
        }

        boolean answer = checkHit(x, y, r);

        var json = String.format(RESULT_JSON, answer, String.valueOf(System.nanoTime() - startTime), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        var responseBody = json.trim();
        var response = String.format(HTTP_RESPONSE, responseBody.getBytes(StandardCharsets.UTF_8).length, responseBody);
        FCGIInterface.request.outStream.write(response.getBytes(StandardCharsets.UTF_8));
        FCGIInterface.request.outStream.flush();
    }


    public static void handleError(Exception e) {
        var json = String.format(ERROR_JSON, e.getMessage());
        var responseBody = json.trim();
        var response = String.format(HTTP_ERROR, responseBody.getBytes(StandardCharsets.UTF_8).length, responseBody);
        try {
            FCGIInterface.request.outStream.write(response.getBytes(StandardCharsets.UTF_8));
            FCGIInterface.request.outStream.flush();
        } catch (Exception exception) {
            return;
        }
    }

    private static String readRequestBody() throws IOException {
        try {
            FCGIInterface.request.inStream.fill();
            int contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            var requestRows = new byte[readBytes];
            buffer.get(requestRows);
            buffer.clear();
            return new String(requestRows, StandardCharsets.UTF_8);
        } catch (NullPointerException e) {
            return e.getMessage();
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
