package com.mx.BusinessCardManagementSystem.servlet;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.bean.RecycleBin;
import com.mx.BusinessCardManagementSystem.bean.User;
import com.mx.BusinessCardManagementSystem.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userid=request.getParameter("id2");
        String userpwd=request.getParameter("password2");
        try {
            User u = userDao.queryById(userid);
            if(u==null || !u.getUserId().equals(userid)){
                User user = new User(userid,userpwd,"用户","未填写");
                Card card=new Card(userid,"未填写","未填写",userid,userpwd,"未填写");
                userDao.add(user);
                cardDao.add(card);
                request.getRequestDispatcher("login-index.jsp").forward(request,response);
            }else{
                request.setAttribute("msg2","注册失败，用户已存在或者格式不正确！");
                request.getRequestDispatcher("login-index.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
