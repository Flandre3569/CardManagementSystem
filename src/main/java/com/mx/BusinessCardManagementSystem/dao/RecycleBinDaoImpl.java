package com.mx.BusinessCardManagementSystem.dao;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.bean.RecycleBin;
import com.mx.BusinessCardManagementSystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class RecycleBinDaoImpl implements RecycleBinDao {
	private DBUtil util = new DBUtil();
	@Override
	public List<RecycleBin> queryAll() throws Exception {
		List<RecycleBin> RecycleList = new ArrayList<RecycleBin>();
        Connection con = util.getCon();
        String sql = "select * from recyclebin;";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
			String id = rs.getString("id");
			String username = rs.getString("username");
			String sex = rs.getString("sex");
			String userid = rs.getString("userid");
			String userpwd = rs.getString("userpassword");
			String email = rs.getString("email");
			RecycleBin b = new RecycleBin(id,username,sex,userid,userpwd,email);
            RecycleList.add(b);
        }
        util.close(rs, pst, con);
        return RecycleList;
	}

	@Override
	public void add(RecycleBin b) throws Exception {
		Connection con=util.getCon();
		String sql="insert into recyclebin(id,username,sex,userid,userpassword,email) values(?,?,?,?,?,?);";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,b.getId());
		ps.setString(2,b.getUsername());
		ps.setString(3,b.getSex());
		ps.setString(4,b.getUserid());
		ps.setString(5,b.getUserpassword());
		ps.setString(6,b.getEmail());
		ps.executeUpdate();
		util.close(null, ps, con);
	}


	@Override
	public void delete(String id) throws Exception {
		Connection conn=util.getCon();
		String sql="delete from recyclebin where id="+id+";";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.executeUpdate();
		util.close(null, ps, conn);

	}

	@Override
	public void rollback(String cardId) throws Exception {

	}

	@Override
	public RecycleBin queryById(String reid) throws Exception {
		Connection con = util.getCon();
		String sql = "select * from recyclebin where id = ?;";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,reid);
		ResultSet rs = pst.executeQuery();
		while (rs.next())
		{
			if(reid.equals(rs.getString("id"))){
				String id = rs.getString("id");
				String username = rs.getString("username");
				String sex = rs.getString("sex");
				String userid = rs.getString("userid");
				String userpwd = rs.getString("userpassword");
				String email = rs.getString("email");
				RecycleBin b = new RecycleBin(id,username,sex,userid,userpwd,email);
				util.close(rs, pst, con);
				return b;
			}
		}
		util.close(rs, pst, con);
		return null;
	}

}
