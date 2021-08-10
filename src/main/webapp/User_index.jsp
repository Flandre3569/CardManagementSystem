<%@ page import="java.util.List" %>
<%@ page import="com.mx.BusinessCardManagementSystem.bean.User" %>
<%@ page import="com.mx.BusinessCardManagementSystem.bean.Card" %><%--
  Created by IntelliJ IDEA.
  User: Fan
  Date: 2021/6/19
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户个人信息管理</title>
    <link rel="stylesheet" href="./User_index_style.css">
  </head>
  <body>
  <div class="container">
    <header class="header">
      <span>管理界面</span>
      <h1>用户信息管理界面</h1>
      <img src="./images/左箭头.png" alt="" class="arrow">
    </header>

    <div class="box">
      <img src="./images/设置.png" alt="" class="btn">
    </div>

    <div class="main">
      <div class="content" id="box1">
        <%
          User user= (User) request.getAttribute("user");
          if(user!=null){
        %>
        <table class="table_container">
          <tr><td>个人信息：</td></tr>
          <tr>
            <td>userid</td>
            <td>userpassword</td>
            <td>identity</td>
            <td>name</td>
          </tr>
          <tr>
            <td><%=user.getUserId()%></td>
            <td><%=user.getPassword()%></td>
            <td><%=user.getIdentity()%></td>
            <td><%=user.getName()%></td>
          </tr>
          <%}%>
        </table>

        <%
          Card card = (Card) request.getAttribute("card");
          if(card!=null){
        %>
        <table class="table_container">
          <tr><td>名片信息：</td></tr>
          <tr>
            <td>id</td>
            <td>username</td>
            <td>sex</td>
            <td>userid</td>
            <td>userpassword</td>
            <td>email</td>
          </tr>
          <tr>
            <td><%=card.getId()%></td>
            <td><%=card.getUsername()%></td>
            <td><%=card.getSex()%></td>
            <td><%=card.getUserid()%></td>
            <td><%=card.getUserpassword()%></td>
            <td><%=card.getEmail()%></td>
          </tr>
          <tr style="text-align: center">
            <td>
              <a href="#" class="borrow_btn">修改信息</a>
            </td>
          </tr>
          <%}%>
        </table>
    </div>
  </div>
  </div>

  <form action="modify" method="post">
    <div id="login" class="login">
      <div id="title" class="login-title">Modify the password
        <span><a id="closeBtn" href="javascript:void(0);" class="close-login">关闭</a></span>
      </div>
      <div class="login-input-content">
        <div class="login-input">
          <label>输入用户名：</label>
          <input type="text" placeholder="请输入用户名" name="id" id="username" class="list-input">
        </div>
        <div class="login-input">
          <label>输入新密码：</label>
          <input type="password" placeholder="请输入新密码" name="newPwd" id="password" class="list-input">
        </div>
        <p style="color: red ;size: 10px">&nbsp;&nbsp;${message}</p>
      </div>
      <div id="loginBtn" class="login-button"><input type="submit" id="login-button-submit" value="提交"></div>
    </div>
  </form>

  <form action="modifycard" method="post">
    <div id="login2" class="login2">
      <div id="title2" class="login-title2">Modify the information
        <span><a id="closeBtn2" href="javascript:void(0);" class="close-login2">关闭</a></span>
      </div>
      <div class="login-input-content2">
        <div class="login-input2">
          <label>输入用户名：</label>
          <input type="text" placeholder="请输入用户名" name="id" id="username2" class="list-input2">
        </div>
        <div class="login-input2">
          <label>输入姓名：</label>
          <input type="text" placeholder="请输入姓名" name="name" id="name2" class="list-input2">
        </div>
        <div class="login-input2">
          <label>输入性别：</label>
          <input type="text" placeholder="请输入性别" name="sex" id="sex2" class="list-input2">
        </div>
        <div class="login-input2">
          <label>输入邮箱：</label>
          <input type="text" placeholder="请输入邮箱" name="email" id="email2" class="list-input2">
        </div>
        <p style="color: red ;size: 10px">&nbsp;&nbsp;${message}</p>
      </div>
      <div id="loginBtn2" class="login-button2"><input type="submit" id="login-button-submit2" value="提交"></div>
    </div>
  </form>

  <!-- 遮盖层 -->
  <div id="bg2" class="login-bg"></div>
    <script>
      let arrow=document.querySelector('.arrow');


      arrow.addEventListener('click',function (){
          window.location.href='./login-index.jsp';
      })

      let btn=document.querySelector('.btn');
      let login=document.querySelector('.login');
      let mask=document.querySelector('.login-bg');
      let closebtn=document.querySelector('#closeBtn');
      let title=document.querySelector('#title');

      let borrow_btn=document.querySelector('.borrow_btn');
      let login2=document.querySelector('.login2');
      let closebtn2=document.querySelector('.closebtn2');
      let title2=document.querySelector('.title2');


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


      borrow_btn.addEventListener('click',function(){
        mask.style.display='block';
        login2.style.display='block';
      })
      closeBtn2.addEventListener('click',function(){
        mask.style.display='none';
        login2.style.display='none';
      })
      //实现拖拽效果
      title2.addEventListener('mousedown',function(e){
        let x=e.pageX-login2.offsetLeft;
        let y=e.pageY-login2.offsetTop;
        document.addEventListener('mousemove',fn);
        function fn(e){
          login2.style.left=e.pageX-x+'px';
          login2.style.top=e.pageY-y+'px';
        }
        document.addEventListener('mouseup',function(){
          document.removeEventListener('mousemove',fn);
        })
      })
    </script>
  </body>
</html>
