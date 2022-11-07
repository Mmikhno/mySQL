package ru.netology.login.test;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.login.data.DataHelper;
import ru.netology.login.data.SQLHelper;
import ru.netology.login.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

import static ru.netology.login.data.SQLHelper.cleanDB;

public class SQLTest {
    QueryRunner runner = new QueryRunner();

    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    void shouldTestEnterWithValidLoginAndPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authinfo = DataHelper.getAutInfo();
        var verPage = loginPage.validLogin(authinfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verPage.verify(verificationCode);
    }

    @Test
    void shouldTestEnterWithRandomLogin() {
        var loginRage = open("http://localhost:9999", LoginPage.class);
        var autoinfo = DataHelper.getRandomLogin();
        loginRage.validLogin(autoinfo);
        loginRage.notificationVisibility();
    }

    @Test
    void shouldTestEnterWithRandomPassword() {
        var loginRage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getRandomPassword();
        loginRage.validLogin(authInfo);
        loginRage.notificationVisibility();
    }

    @Test
    void shouldTestEnterWithRandomVerificationCode() {
        var loginRage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAutInfo();
        var verPage = loginRage.validLogin(authInfo);
        var verificationCode = DataHelper.getRandomVerificationCode();
        verPage.verifyRandomCode(verificationCode);
    }

}
