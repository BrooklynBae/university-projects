package database;

import java.io.Console;
import java.util.Scanner;

public class SignUp implements DataBaseCommand{
    Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;
    private User user = new User();

    @Override
    public User login() {
        while (true) {
            System.out.println("Введите имя пользователя (длина не превышает 15 символов) :");
            username = scanner.nextLine();
            if (username == null || username.trim().isEmpty() || username.length() > 15 || username.contains(" ")) {
                System.out.println("Некорректный формат имени пользователя! Попробуйте еще раз.");
            } else {
                user.setUsername(username);
                break;
            }
        }
        while (true) {
            System.out.println("Введите пароль (длина не превышает 15 символов) :");

            Console console = System.console();
            if(console != null) {
                char[] symbols = console.readPassword();
                if (symbols == null) continue;
                password = String.valueOf(symbols);
            }
            else password = scanner.nextLine();

            if (password == null | password.trim().isEmpty() | password.trim().length() > 15 | password.contains(" ")) {
                System.out.println("Некорректный формат пароля! Попробуйте еще раз.");
            } else {
                user.setPassword(password);
                break;
            }
        }
        user.setRegFlag(1);
        return user;
    }
}
