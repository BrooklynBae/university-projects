package utils;

import java.io.Serializable;

public class Point implements Serializable {
    public double x;
    public double y;
    public double r;
    public boolean answer;
    public long time;
    public String scriptTime;

    public Point(double x, double y, double r, boolean answer, long time, String scriptTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.answer = answer;
        this.time = time;
        this.scriptTime = scriptTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(String scriptTime) {
        this.scriptTime = scriptTime;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", answer=" + answer +
                ", time='" + time + '\'' +
                ", scriptTime=" + scriptTime +
                '}';
    }
}
