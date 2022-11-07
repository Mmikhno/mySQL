package ru.netology.login.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.*;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {

    }

    @SneakyThrows
    private static Connection getConnection() {
        var conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/app", "app", "pass");
        return conn;
    }

    public static DataHelper.VerificationCode getVerificationCode() {
        var dataSQL = "SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConnection()) {
            var verCode = runner.query(conn, dataSQL, new BeanHandler<>(DataHelper.AuthorizationCode.class));
            return new DataHelper.VerificationCode(verCode.getCode());
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        runner.execute(conn, "DELETE FROM auth_codes");
        runner.execute(conn, "DELETE FROM card_transactions");
        runner.execute(conn, "DELETE FROM cards");
        runner.execute(conn, "DELETE FROM users");


    }


}
