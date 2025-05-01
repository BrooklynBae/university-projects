package database;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -1;
    private String username;
    private String password;
    private int regFlag = 0;

    public int getRegFlag() {
        return regFlag;
    }
    public void setRegFlag(int regFlag) {
        this.regFlag = regFlag;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
