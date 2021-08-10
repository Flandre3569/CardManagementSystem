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

@WebServlet("/rollback")
public class rollbackServlet extends HttpServlet {
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
            RecycleBin rc=recycleBinDao.queryById(id);
            Card c=new Card(rc.getId(),rc.getUsername(),rc.getSex(),rc.getUserid(),rc.getUserpassword(),rc.getEmail());
//            将名片从回收站中导出到名片数据库中
            cardDao.add(c);
            recycleBinDao.delete(id);
            userList=userDao.queryAll();
            cardList=cardDao.queryAll();
            recycleBinList=recycleBinDao.queryAll();
            request.setAttribute("userList",userList);
            request.setAttribute("cardList",cardList);
            request.setAttribute("checkList",null);
            request.setAttribute("recyclebinList",recycleBinList);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
