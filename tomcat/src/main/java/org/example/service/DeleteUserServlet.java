package org.example.service;

import org.example.repository.JdbcRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteUser", value = "/tomcat/delete-user")
public class DeleteUserServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService(new JdbcRepository());
        userService.createTableIfNotExists();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        try {
            Object[] array = userService.getAllUsers().stream().filter(x -> x.getUserId().equals(userIdParam))
                    .findFirst().stream().toArray();
            if (array.length == 0) {
                resp.sendRedirect("/tomcat/api");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userIdParam);
        String s = userService.deleteUser(userIdParam);
        System.out.println(s);
        resp.sendRedirect("/tomcat/api");
    }
}
