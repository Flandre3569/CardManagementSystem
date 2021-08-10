package com.mx.BusinessCardManagementSystem.servlet;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.bean.User;
import com.mx.BusinessCardManagementSystem.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/modify")
public class modifyServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String newPwd=request.getParameter("newPwd");
        try {
            User u=userDao.queryById(id);
            u.setPassword(newPwd);
            userDao.update(id,newPwd);
            Card card=cardDao.queryById(id);
            card.setUserpassword(newPwd);
            cardDao.update(id,card);

            request.setAttribute("user",u);
            request.setAttribute("card",card);
            request.getRequestDispatcher("/User_index.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
