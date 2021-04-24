package com.HeYuxin.controller;

import com.HeYuxin.dao.UserDao;
import com.HeYuxin.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in");
        String username = request.getParameter("Username");
        String password = request.getParameter("password");
        String email = request.getParameter("Email");
        String gender = request.getParameter("sex");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = null;
        try {
            System.out.println(request.getParameter("BirthDate").trim());

            birth = simpleDateFormat.parse(request.getParameter("BirthDate").trim());
        } catch (ParseException e) {
            System.out.println(e);
        }
        String id = request.getParameter("id");
        User u = new User(Integer.valueOf(id), username, password, email, gender, birth);
        UserDao userDao = new UserDao();
            try {
                userDao.updateUser((Connection) getServletContext().getAttribute("con"), u);
            } catch (SQLException e) {
                System.out.println(e);
            }
            System.out.println(birth);
            request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);
        }
}