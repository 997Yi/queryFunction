package com.wwq.controller;

import com.wwq.bean.User;
import com.wwq.service.UserService;
import com.wwq.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        //封装数据
        User user = new User();
        user.setName((String)request.getParameter("name"));
        user.setGender((String)request.getParameter("gender"));
        String strDate = (String)request.getParameter("birthday");
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(strDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(date);

        //查询数据
        List<User> userList = userService.queryByName(user);
        if (!userList.isEmpty() || userList == null){
            System.out.println(userList);
            request.setAttribute("userList", userList);
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
