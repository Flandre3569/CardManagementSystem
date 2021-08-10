package com.mx.BusinessCardManagementSystem.servlet;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.dao.CardDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addcard")
public class addCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String username=request.getParameter("username");
        String sex=request.getParameter("sex");
        String userid=request.getParameter("userid");
        String userpwd=request.getParameter("userpwd");
        String email=request.getParameter("email");

        Card card=new Card(id,username,sex,userid,userpwd,email);
        CardDaoImpl cardDao=new CardDaoImpl();
        try {
//            往数据库中添加一个名片
            cardDao.add(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
