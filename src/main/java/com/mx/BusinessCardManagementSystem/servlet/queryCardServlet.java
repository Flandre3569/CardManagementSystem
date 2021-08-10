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

@WebServlet("/querycard")
public class queryCardServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String check=request.getParameter("check");
        List<Card> cardList1=new ArrayList<Card>();
        List<User> userList=new ArrayList<User>();
        List<Card> cardList=new ArrayList<Card>();
        List<RecycleBin> recycleBinList=new ArrayList<RecycleBin>();
        try {
            cardList1=cardDao.queryByKeyword(check);
            cardList=cardDao.queryAll();
            userList=userDao.queryAll();
            recycleBinList=recycleBinDao.queryAll();
            request.setAttribute("checkList",cardList1);
            request.setAttribute("userList",userList);
            request.setAttribute("cardList",cardList);
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
