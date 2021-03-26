package com.wwq.controller;

import com.wwq.bean.User;
import com.wwq.service.UserService;
import com.wwq.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/index")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getInstance();
        List<User> userList = userService.queryByName(new User());
        if (!userList.isEmpty()){
            System.out.println(userList);
            request.setAttribute("userList", userList);
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
