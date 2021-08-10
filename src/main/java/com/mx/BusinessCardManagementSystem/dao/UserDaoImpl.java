package com.mx.BusinessCardManagementSystem.dao;

import com.mx.BusinessCardManagementSystem.bean.User;
import com.mx.BusinessCardManagementSystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private DBUtil util = new DBUtil();


    @Override
    public List<User> queryAll() throws Exception {
        List<User> userList = new ArrayList<User>();
        Connection con = util.getCon();
        String sql = "select * from user;";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            String userId = rs.getString("id");
            String password = rs.getString("password");
            String identity = rs.getString("identity");
            String name = rs.getString("name");
            User u = new User(userId,password,identity,name);
            userList.add(u);
        }
        util.close(rs, pst, con);
        return userList;
    }

    @Override
    public User queryById(String userId) throws Exception {
        Connection con = util.getCon();
        String sql = "select * from user where id = ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,userId);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
                String id = rs.getString("id");
                String password = rs.getString("password");
                String identity = rs.getString("identity");
                String name = rs.getString("name");
                User u = new User(id,password,identity,name);
                util.close(rs, pst, con);
                return u;
        }
        util.close(rs, pst, con);
        return null;
    }

    @Override
    public void add(User user) throws Exception {
        Connection con = util.getCon();
        String sql = "insert into user(id,password,identity,name) values (?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,user.getUserId());
        pst.setString(2,user.getPassword());
        pst.setString(3,user.getIdentity());
        pst.setString(4,user.getName());
        pst.executeUpdate();
        util.close(null, pst, con);
    }

    @Override
    public void delete(String userId) throws Exception {
        Connection con = util.getCon();
        String sql = "delete from user where id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,userId);

        pst.executeUpdate();
        util.close(null, pst, con);
    }

    @Override
    public void update(String userId,String password) throws Exception {
        Connection con = util.getCon();
        String sql = "update user set password=? where id=?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,password);
        pst.setString(2,userId);
        pst.executeUpdate();
        util.close(null, pst, con);
    }

    @Override
    public void updateinfo(String userId, String name) throws Exception {
        Connection con = util.getCon();
        String sql = "update user set `name`=? where id=?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,userId);
        pst.executeUpdate();
        util.close(null, pst, con);
    }


}
