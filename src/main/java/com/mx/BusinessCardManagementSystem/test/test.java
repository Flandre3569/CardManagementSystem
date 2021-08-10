package com.mx.BusinessCardManagementSystem.test;

import com.mx.BusinessCardManagementSystem.bean.Card;
import com.mx.BusinessCardManagementSystem.dao.CardDao;
import com.mx.BusinessCardManagementSystem.dao.CardDaoImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        File file=new File("E://card.xls");
        CardDao cardDao=new CardDaoImpl();
        List<Card> cardList=new ArrayList<Card>();
        cardList= cardDao.queryByExcel(file);
        System.out.println(cardList.get(0).getUsername()+cardList.get(0).getEmail());
        System.out.println(cardList.get(1).getUsername());
    }
}
