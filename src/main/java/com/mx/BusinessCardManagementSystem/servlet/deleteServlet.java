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

@WebServlet("/deletecard")
public class deleteServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        List<User> userList=new ArrayList<User>();
        List<Card> cardList=new ArrayList<Card>();
        List<RecycleBin> recycleBinList=new ArrayList<RecycleBin>();

        try {
            Card c=cardDao.queryById(id);
            RecycleBin rc=new RecycleBin(c.getId(),c.getUsername(),c.getSex(),c.getUserid(),c.getUserpassword(),c.getEmail());
//            在名片数据库中删除该名片，在回收站中添加该名片
            recycleBinDao.add(rc);
            cardDao.delete(id);
            cardList=cardDao.queryAll();
            userList=userDao.queryAll();
            recycleBinList=recycleBinDao.queryAll();
            request.setAttribute("userList",userList);
            request.setAttribute("cardList",cardList);
            request.setAttribute("recyclebinList",recycleBinList);
            request.setAttribute("checkList",null);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
