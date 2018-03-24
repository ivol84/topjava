package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meals", MealsUtil.getFilteredWithExceeded(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
