package database;

import java.util.Scanner;

public class SignIn implements DataBaseCommand{
    Scanner scanner = new Scanner(System.in);
    private String username = null;
    private String password = null;
    private User user = new User();

    @Override
    public User login() {
        while (true) {
            System.out.print("Введите имя пользователя (длина не превышает 15 символов) :");
            username = scanner.nextLine();
            if (username == null | username.trim().isEmpty() | username.length() > 15 | username.contains(" ")) {
                System.out.println("Некорректный формат имени пользователя! Попробуйте еще раз.");
            } else {
                user.setUsername(username);
                break;
            }
        }
        while (true) {
            System.out.print("Введите пароль (длина не превышает 15 символов) :");
            password = scanner.nextLine();
            if (password == null | password.trim().isEmpty() | password.length() > 15 | password.contains(" ")) {
                System.out.println("Некорректный формат пароля! Попробуйте еще раз.");
            } else {
                user.setPassword(password);
                break;
            }
        }
        user.setRegFlag(0);
        return user;
    }
}
