package service;

public class LoginManager {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private static final String CUSTOMER_USERNAME = "customer";
    private static final String CUSTOMER_PASSWORD = "customer123";

    private int attempts;

    public LoginManager() {
        attempts = 0;
    }

    public String login(
            String username,
            String password) {

        if (attempts >= 3) {
            return "BLOCKED";
        }

        if (username.equals(ADMIN_USERNAME) &&
                password.equals(ADMIN_PASSWORD)) {

            attempts = 0;
            return "Admin";

        } else if (
                username.equals(CUSTOMER_USERNAME) &&
                password.equals(CUSTOMER_PASSWORD)) {

            attempts = 0;
            return "Customer";
        }

        attempts++;

        if (attempts >= 3) {
            return "BLOCKED";
        }

        return null;
    }

    public int getAttempts() {
        return attempts;
    }
}