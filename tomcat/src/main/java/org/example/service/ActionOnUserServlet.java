package org.example.service;

import org.example.repository.JdbcRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ActionOnUserServlet", value = "/tomcat/action-on-user")
public class ActionOnUserServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService(new JdbcRepository());
        userService.createTableIfNotExists();
        System.out.println("some");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");

        if ("Set".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/update-user?userId=" + URLEncoder.encode(userId, StandardCharsets.UTF_8)
                    + "&firstName=" + URLEncoder.encode(firstName, StandardCharsets.UTF_8)
                    + "&lastName=" + URLEncoder.encode(lastName, StandardCharsets.UTF_8)
                    + "&age=" + URLEncoder.encode(age, StandardCharsets.UTF_8));

        } else if ("Create".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/create-user?userId=" + URLEncoder.encode(userId, StandardCharsets.UTF_8)
                    + "&firstName=" + URLEncoder.encode(firstName, StandardCharsets.UTF_8)
                    + "&lastName=" + URLEncoder.encode(lastName, StandardCharsets.UTF_8)
                    + "&age=" + URLEncoder.encode(age, StandardCharsets.UTF_8));

        }
    }
}
