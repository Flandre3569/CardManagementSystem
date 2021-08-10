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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    RecycleBinDao recycleBinDao = new RecycleBinDaoImpl();
    CardDao cardDao = new CardDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String pwd=request.getParameter("password");
        String identity=request.getParameter("identity");

        try {
            List<User> userList=new ArrayList<User>();
            List<Card> cardList=new ArrayList<Card>();
            List<RecycleBin> recycleBinList=new ArrayList<RecycleBin>();
            User user=userDao.queryById(id);
            if(user.getUserId().equals(id)&&user.getPassword().equals(pwd)&&user.getIdentity().equals("管理员")){
                userList=userDao.queryAll();
                cardList=cardDao.queryAll();
                recycleBinList=recycleBinDao.queryAll();
//                传名片数组到页面
                request.setAttribute("userList",userList);
                request.setAttribute("cardList",cardList);
//                传回收站的名片到页面
                request.setAttribute("recyclebinList",recycleBinList);
                request.getSession().setAttribute("swf",user.getName());
                request.setAttribute("checkList",null);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }else if(user.getUserId().equals(id)&&user.getPassword().equals(pwd)&&user.getIdentity().equals("用户")){
                User user1=userDao.queryById(id);
                Card card1=cardDao.queryById(id);

                request.setAttribute("user",user1);
                request.setAttribute("card",card1);
                request.getRequestDispatcher("/User_index.jsp").forward(request,response);
            } else {
                request.getSession().setAttribute("message","您的账号、密码或身份错误");
                request.getRequestDispatcher("/login-index.jsp").forward(request,response);
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
