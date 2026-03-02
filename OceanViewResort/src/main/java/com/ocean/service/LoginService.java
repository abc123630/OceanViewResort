package com.ocean.service;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ocean.util.DBConnection;

public class LoginService {

    public static boolean authenticate(String username, String password) {

        boolean status = false;

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                status = true;
            }
            
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
