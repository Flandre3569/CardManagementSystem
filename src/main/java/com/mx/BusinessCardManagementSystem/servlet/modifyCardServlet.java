package com.mx.BusinessCardManagementSystem.servlet;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.bean.User;
import com.mx.BusinessCardManagementSystem.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/modifycard")
public class modifyCardServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String email=request.getParameter("email");

        try {
            Card card=cardDao.queryById(id);
            card.setUsername(name);
            card.setSex(sex);
            card.setEmail(email);
            cardDao.update(id,card);
            User u=userDao.queryById(id);
            u.setName(name);
            userDao.updateinfo(id,name);

            request.setAttribute("card",card);
            request.setAttribute("user",u);
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
