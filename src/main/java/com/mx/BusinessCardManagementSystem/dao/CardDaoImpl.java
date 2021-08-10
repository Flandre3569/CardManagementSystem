package com.mx.BusinessCardManagementSystem.dao;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.utils.DBUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {

    private DBUtil util = new DBUtil();

    @Override
    public List<Card> queryAll() throws Exception {
        List<Card> cardList = new ArrayList<Card>();
        Connection con = util.getCon();
        String sql = "select * from card";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            String id = rs.getString("id");
            String username = rs.getString("username");
            String sex = rs.getString("sex");
            String userid = rs.getString("userid");
            String userpwd = rs.getString("userpassword");
            String email = rs.getString("email");
            Card b = new Card(id,username,sex,userid,userpwd,email);
            cardList.add(b);
        }
        util.close(rs, pst, con);
        return cardList;
    }

    @Override
    public Card queryById(String cardId) throws Exception {
        Connection con = util.getCon();
        String sql = "select * from card where id = ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,cardId);
        ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            if(cardId.equals(rs.getString("id"))){
                String id = rs.getString("id");
                String username = rs.getString("username");
                String sex = rs.getString("sex");
                String userid = rs.getString("userid");
                String userpwd = rs.getString("userpassword");
                String email = rs.getString("email");
                Card b = new Card(id,username,sex,userid,userpwd,email);
                util.close(rs, pst, con);
                return b;
            }
        }
        util.close(rs, pst, con);
        return null;
    }

    @Override
    public void add(Card card) throws Exception {
        Connection con = util.getCon();
        String sql = "insert into card(id,username,sex,userid,userpassword,email) values (?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,card.getId());
        pst.setString(2,card.getUsername());
        pst.setString(3,card.getSex());
        pst.setString(4,card.getUserid());
        pst.setString(5,card.getUserpassword());
        pst.setString(6,card.getEmail());
        pst.executeUpdate();
        util.close(null, pst, con);
    }

    @Override
    public void delete(String cardId) throws Exception {
        Connection con = util.getCon();
        String sql = "delete from card where id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,cardId);

        pst.executeUpdate();
        util.close(null, pst, con);
    }

    @Override
    public void update(String cardId,Card card) throws Exception {
        Connection con = util.getCon();
        String sql = "update card set id=?,username=?,sex=?,userid=?,userpassword=?,email=? where id=?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,card.getId());
        pst.setString(2,card.getUsername());
        pst.setString(3,card.getSex());
        pst.setString(4,card.getUserid());
        pst.setString(5,card.getUserpassword());
        pst.setString(6,card.getEmail());
        pst.setString(7,cardId);
        pst.executeUpdate();
        util.close(null, pst, con);
    }


    @Override
    public Card queryByName(String Username) throws Exception {
        Connection con = util.getCon();
        String sql = "select * from card where username = ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,Username);
        ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            if(rs.getString("username").equals(Username)) {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String sex = rs.getString("sex");
                String userid = rs.getString("userid");
                String userpwd = rs.getString("userpassword");
                String email = rs.getString("email");
                Card b = new Card(id, username, sex, userid, userpwd, email);
                util.close(rs, pst, con);
                return b;
            }
        }
        util.close(rs, pst, con);
        return null;
    }

    @Override
    public void deleteToRecycleBin(String cardId) throws Exception {

    }

    @Override
    public List<Card> queryByKeyword(String name) throws Exception {
        Connection con = util.getCon();
        String sql = "select * from card where Username like ?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,name);
        ResultSet rs = pst.executeQuery();
        List<Card> cardList=new ArrayList<Card>();
        while (rs.next())
        {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String sex = rs.getString("sex");
                String userid = rs.getString("userid");
                String userpwd = rs.getString("userpassword");
                String email = rs.getString("email");
                Card b = new Card(id, username, sex, userid, userpwd, email);
                cardList.add(b);
        }
        util.close(rs, pst, con);
        return cardList;
    }

    @Override
    public List<Card> queryByExcel(File file) throws Exception {
        List<Card> list=new ArrayList<Card>();
        try {
            Workbook rwb=Workbook.getWorkbook(file);
            Sheet rs=rwb.getSheet("Test Sheet 1");
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name=rs.getCell(j++, i).getContents();
                    String sex=rs.getCell(j++, i).getContents();
                    String userid=rs.getCell(j++, i).getContents();
                    String userpwd=rs.getCell(j++, i).getContents();
                    String email=rs.getCell(j++, i).getContents();
                    list.add(new Card(id, name, sex,userid,userpwd,email));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void toExcel() throws Exception {
        WritableWorkbook wwb = null;

        // 创建可写入的Excel工作簿
        String fileName = "E://card.xls";
        File file=new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        //以fileName为文件名来创建一个Workbook
        wwb = Workbook.createWorkbook(file);

        // 创建工作表
        WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);

        //查询数据库中所有的数据
        List<Card> list= queryAll();
        //要插入到的Excel表格的行号，默认从0开始
        Label labelId= new Label(0, 0, "名片号(id)");
        Label labelName= new Label(1, 0, "姓名(name)");
        Label labelSex= new Label(2, 0, "性别(sex)");
        Label labelUserid= new Label(3, 0, "用户账号(userid)");
        Label labelUserpassword= new Label(4, 0, "用户密码(userpwd)");
        Label labelEmail= new Label(5, 0, "邮箱(email)");

        ws.addCell(labelId);
        ws.addCell(labelName);
        ws.addCell(labelSex);
        ws.addCell(labelUserid);
        ws.addCell(labelUserpassword);
        ws.addCell(labelEmail);
        for (int i = 0; i < list.size(); i++) {
            Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
            Label labelName_i= new Label(1, i+1, list.get(i).getUsername()+"");
            Label labelSex_i= new Label(2, i+1, list.get(i).getSex()+"");
            Label labeluserid_i= new Label(3, i+1, list.get(i).getUserid()+"");
            Label labeluserpwd_i= new Label(4, i+1, list.get(i).getUserpassword()+"");
            Label labelemail_i= new Label(5, i+1, list.get(i).getEmail()+"");
            ws.addCell(labelId_i);
            ws.addCell(labelName_i);
            ws.addCell(labelSex_i);
            ws.addCell(labeluserid_i);
            ws.addCell(labeluserpwd_i);
            ws.addCell(labelemail_i);
        }
        //写进文档
        wwb.write();
        // 关闭Excel工作簿对象
        wwb.close();
    }

}
