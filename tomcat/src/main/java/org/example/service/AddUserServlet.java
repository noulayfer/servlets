package org.example.service;

import lombok.SneakyThrows;
import org.example.model.User;
import org.example.repository.JdbcRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CreateUser", value = "/create-user")
public class AddUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService(new JdbcRepository());
        userService.createTableIfNotExists();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        try {
            Object[] array = userService.getAllUsers().stream().filter(x -> x.getUserId().equals(userId))
                    .findFirst().stream().toArray();
            if (array.length != 0) {
                resp.sendRedirect("/tomcat/api");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");
        userService.createUser(firstName, lastName, Integer.parseInt(age));
        resp.sendRedirect("/tomcat/api");
    }

}
