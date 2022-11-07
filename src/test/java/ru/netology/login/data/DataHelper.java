package ru.netology.login.data;

import java.util.Locale;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DataHelper {

    private DataHelper() {

    }

    private static Faker faker = new Faker(new Locale("en"));


    public static class AuthInfo {
        String login;
        String password;

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    public static String getUserName() {
        String userName = faker.name().firstName().toLowerCase();
        return userName;
    }

    public static String getUserPassword() {
        String userPassword = faker.internet().password(7, 9);
        return userPassword;
    }

    public static AuthInfo getRandomLogin() {
        return new AuthInfo(getUserName(), "qwerty123");
    }

    public static AuthInfo getRandomPassword() {
        return new AuthInfo("vasya", getUserPassword());
    }

    public static AuthInfo getAutInfo() {
        AuthInfo authInfo = new AuthInfo("vasya", "qwerty123");
        return authInfo;
    }

    public static class VerificationCode {
        private String code;

        public VerificationCode(String code) {
            this.code = code;
        }

        public String getVerificationCode() {
            return code;
        }

        public void setVerificationCode(String code) {
            this.code = code;
        }
    }

    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("#####"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class AuthorizationCode {
        private String id;
        private String code;
        private String user_id;
        private String created;
    }


}



