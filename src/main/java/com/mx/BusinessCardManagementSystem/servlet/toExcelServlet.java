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

@WebServlet("/toExcel")
public class toExcelServlet extends HttpServlet {
        UserDao userDao = new UserDaoImpl();
        RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
        CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<User> userList=new ArrayList<User>();
        List<Card> cardList=new ArrayList<Card>();
        List<RecycleBin> recycleBinList=new ArrayList<RecycleBin>();
        try {
            cardDao.toExcel();
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