package org.example.service;

import lombok.SneakyThrows;
import org.example.model.User;
import org.example.repository.JdbcRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShowAll", value = "/api")
public class ShowAllServlet extends HttpServlet {
    JdbcRepository jdbcRepository;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcRepository = new JdbcRepository();
        userService = new UserService(jdbcRepository);
        jdbcRepository.createTableIfNotExists();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("all-users.jsp");
        requestDispatcher.forward(req, resp);
    }
}
