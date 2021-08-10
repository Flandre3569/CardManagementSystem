package com.mx.BusinessCardManagementSystem.dao;


import com.mx.BusinessCardManagementSystem.bean.Card;

import java.io.File;
import java.util.List;

public interface CardDao {
    //查询所有名片
    List<Card> queryAll() throws Exception;
    //通过id查询名片
    Card queryById(String cardId) throws Exception;
    //增加名片
    void add(Card card) throws Exception;
    //删除名片
    void delete(String cardId) throws Exception;
    //修改名片
    void update(String cardId,Card card) throws Exception;
    //通过姓名查询名片
    Card queryByName(String cardName)throws Exception;
    //删除名片到回收站
    void deleteToRecycleBin(String cardId) throws Exception;
    //模糊查询
    List<Card> queryByKeyword(String name) throws Exception;
    //从excel中查询数据
    List<Card> queryByExcel(File file) throws Exception;
    //将数据导出到excel中
    void toExcel() throws Exception;
}
