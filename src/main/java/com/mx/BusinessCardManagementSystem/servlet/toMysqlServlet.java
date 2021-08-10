package com.mx.BusinessCardManagementSystem.servlet;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.bean.RecycleBin;
import com.mx.BusinessCardManagementSystem.bean.User;
import com.mx.BusinessCardManagementSystem.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/toMysql")
public class toMysqlServlet extends HttpServlet {
        UserDao userDao = new UserDaoImpl();
        RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
        CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        File file=new File("E://card.xls");
        List<Card> list=new ArrayList<Card>();
        List<User> userList=new ArrayList<User>();
        List<Card> cardList=new ArrayList<Card>();
        List<RecycleBin> recycleBinList=new ArrayList<RecycleBin>();
        try {
            list=cardDao.queryByExcel(file);
            for(int i=0;i<list.size();i++){
                String id=list.get(i).getId();
                Card card=cardDao.queryById(id);
                if(card==null){
                    card=new Card(list.get(i).getId(),list.get(i).getUsername(),list.get(i).getSex(),list.get(i).getUserid(),list.get(i).getUserpassword(),list.get(i).getEmail());
                    cardDao.add(card);
                }
            }
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
