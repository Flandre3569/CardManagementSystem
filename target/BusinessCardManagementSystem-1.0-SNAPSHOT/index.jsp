<%@ page import="com.mx.BusinessCardManagementSystem.bean.Card" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mx.BusinessCardManagementSystem.bean.RecycleBin" %>
<%@ page import="com.mx.BusinessCardManagementSystem.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Fan
  Date: 2021/6/29
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
    <link rel="stylesheet" href="./index.css">
  </head>
  <body>
  <!--head-->
  <div class="header">
    <div class="inner">
      <h1>名片管理系统</h1>
      <div class="headlink">
        <span>你好</span>
        &nbsp;|&nbsp;
        <span>管理员</span>
        &nbsp;|
        <span>${swf}</span>
      </div>
    </div>
  </div>
  <div class="box">
    <img src="./images/搜索1.png" alt="" class="btn">
  </div>
  <!-- --------------------------------------------- -->
  <!--head end-->
  <!--middle-->
  <div class="middle">
    <div class="cont">
      <!--菜单-->
      <div class="class">
        <ul>
          <h3 style="color:#FFF;">操作指引</h3>
          <a href="#" class="net"><li class="tab">所有名片管理</li></a>
          <a href="#" class="_all2"><li class="tab">用户信息管理</li></a>
          <a href="#" class="_all3"><li class="tab">回收站管理</li></a>
          <a href="#" class="_all4"><li class="tab">导入导出</li></a>
          <a href="#" class="_all5"><li class="tab" id="query">名片查询</li></a>
        </ul>
      </div>
      <!--菜单 end-->
      <!--指南-->
      <div class="class_a">
        <div class="all1" id="all_1">
          <h4 style="color:#fff">所有名片操作页面</h4>
        </div>
        <div class="all1" id="all_2">
          <h4 style="color:#fff">用户信息管理</h4>
        </div>
        <div class="all1" id="all_3">
          <h4 style="color:#fff">回收站管理主页</h4>
        </div>
        <div class="all1" id="all_4">
          <h4 style="color:#fff">导入导出</h4>
        </div>
        <div class="all1" id="all_5">
          <h4 style="color:#fff">名片查询</h4>
        </div>
<!--        管理员主页-->
        <div id="tab-content1" class="tab-content">
          <%
            List<Card> cardList = (List<Card>) request.getAttribute("cardList");
            if(!cardList.isEmpty()){
          %>
          <table class="table_container">
            <tr>
              <td>cardID</td>
              <td>username</td>
              <td>usersex</td>
              <td>userid</td>
              <td>userpassword</td>
              <td>email</td>
            </tr>
            <%for (Card card:cardList) {%>
            <tr>
              <td><%=card.getId()%></td>
              <td><%=card.getUsername()%></td>
              <td><%=card.getSex()%></td>
              <td><%=card.getUserid()%></td>
              <td><%=card.getUserpassword()%></td>
              <td><%=card.getEmail()%></td>
              <td class="btn_td"><a class="borrow_btn" href="deletecard?id=<%=card.getId()%>">放入回收站</a></td>
              <td class="btn_td"><a class="borrow_btn" href="deletecardas?id=<%=card.getId()%>">彻底删除</a></td>
            </tr>
            <%}
            }%>
          </table>
        </div>
        <!--        用户信息管理-->
        <div id="tab-content2" class="tab-content">
          <%
            List<User> userList = (List<User>) request.getAttribute("userList");
            if(!userList.isEmpty()){
          %>
          <table class="table_container">
            <tr>
              <td>userid</td>
              <td>userpassword</td>
              <td>identity</td>
              <td>name</td>
            </tr>
            <%for (User user:userList) {%>
            <tr>
              <td><%=user.getUserId()%></td>
              <td><%=user.getPassword()%></td>
              <td><%=user.getIdentity()%></td>
              <td><%=user.getName()%></td>
              <td class="btn_td"><a class="borrow_btn" href="deleteuser?id=<%=user.getUserId()%>">删除用户</a></td>
            </tr>
            <%}
            }%>
          </table>
        </div>

        <!--        回收站管理主页-->
        <div id="tab-content3" class="tab-content">
          <%
            List<RecycleBin> recyclebinList = (List<RecycleBin>) request.getAttribute("recyclebinList");
            if(!recyclebinList.isEmpty()){
          %>
          <table class="table_container">
            <tr>
              <td>cardID</td>
              <td>username</td>
              <td>usersex</td>
              <td>userid</td>
              <td>userpassword</td>
              <td>email</td>
            </tr>
            <%for (RecycleBin rc:recyclebinList) {%>
            <tr>
              <td><%=rc.getId()%></td>
              <td><%=rc.getUsername()%></td>
              <td><%=rc.getSex()%></td>
              <td><%=rc.getUserid()%></td>
              <td><%=rc.getUserpassword()%></td>
              <td><%=rc.getEmail()%></td>
              <td class="btn_td"><a class="borrow_btn" href="rollback?id=<%=rc.getId()%>">名片恢复</a></td>
              <td class="btn_td"><a class="borrow_btn" href="deletecardas?id=<%=rc.getId()%>">彻底删除</a></td>
            </tr>
            <%}
            }%>
          </table>
        </div>
<!--        导入导出-->
        <div id="tab-content4" class="tab-content">
          <table class="table_container">
            <tr>
              <td class="btn_td"><a class="borrow_btn" href="toExcel">导出到excel</a></td>
              <td class="btn_td"><a class="borrow_btn" href="toMysql">导入到mysql</a></td>
            </tr>
          </table>
        </div>
        <!--        查询-->
        <div id="tab-content5" class="tab-content" style="text-align: center">
          <%
            List<Card> checkList = (List<Card>) request.getAttribute("checkList");
            if(checkList!=null){
          %>
          <table class="table_container">
            <tr>
              <td>cardID</td>
              <td>username</td>
              <td>usersex</td>
              <td>userid</td>
              <td>userpassword</td>
              <td>email</td>
            </tr>
            <%for (Card cd:checkList) {%>
            <tr>
              <td><%=cd.getId()%></td>
              <td><%=cd.getUsername()%></td>
              <td><%=cd.getSex()%></td>
              <td><%=cd.getUserid()%></td>
              <td><%=cd.getUserpassword()%></td>
              <td><%=cd.getEmail()%></td>
            </tr>
            <%}
            }else{%>
            <h2>无查询内容</h2>
            <%}%>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="foot"></div>

  <form action="querycard" method="post">
    <div id="login" class="login">
      <div id="title" class="login-title">名片查询
        <span><a id="closeBtn" href="javascript:void(0);" class="close-login">关闭</a></span>
      </div>
      <div class="login-input-content">
        <div class="login-input">
          <label>输入用户名：</label>
          <input type="text" placeholder="请输入关键字" name="check" id="check" class="list-input">
        </div>
        <p style="color: red ;size: 10px">&nbsp;&nbsp;${message}</p>
      </div>
      <div id="loginBtn" class="login-button"><input type="submit" id="login-button-submit" value="提交"></div>
    </div>
  </form>
  <!-- 遮盖层 -->
  <div id="bg" class="login-bg"></div>
  </div>



  <script>
    let tab_contents=document.querySelectorAll('.tab-content');
    let tabs=document.querySelectorAll('.tab');
    let all1s=document.querySelectorAll('.all1');
    for(let i=0;i<tabs.length;i++){
        tabs[i].addEventListener('click',function (){
          for(let j=0;j<tab_contents.length;j++){
              tab_contents[j].style.display='none';
              all1s[j].style.display='none';
            }
          tab_contents[i].style.display='block';
          all1s[i].style.display='block';
        })
    }

    let btn=document.querySelector('.btn');
    let login=document.querySelector('.login');
    let mask=document.querySelector('.login-bg');
    let closebtn=document.querySelector('#closeBtn');
    let title=document.querySelector('#title');

    btn.addEventListener('click',function(){
      mask.style.display='block';
      login.style.display='block';
    })
    closeBtn.addEventListener('click',function(){
      mask.style.display='none';
      login.style.display='none';
    })
    //实现拖拽效果
    title.addEventListener('mousedown',function(e){
      let x=e.pageX-login.offsetLeft;
      let y=e.pageY-login.offsetTop;
      document.addEventListener('mousemove',fn);
      function fn(e){
        login.style.left=e.pageX-x+'px';
        login.style.top=e.pageY-y+'px';
      }
      document.addEventListener('mouseup',function(){
        document.removeEventListener('mousemove',fn);
      })
    })
  </script>
  </body>
</html>
