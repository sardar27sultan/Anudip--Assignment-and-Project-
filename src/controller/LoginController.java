package controller;

import dao.LoginDAO;

public class LoginController {

    private LoginDAO loginDAO = new LoginDAO();

    public boolean login(String username, String password) {

        return loginDAO.login(username, password);

    }

}