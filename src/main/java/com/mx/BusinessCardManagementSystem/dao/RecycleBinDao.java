package com.mx.BusinessCardManagementSystem.dao;

import com.mx.BusinessCardManagementSystem.bean.RecycleBin;

import java.util.List;

public interface RecycleBinDao {
	//查询回收站里所有名片
    public List<RecycleBin> queryAll() throws Exception;
    //增加一个名片
    public void add(RecycleBin b)throws Exception;
    //根据图书id彻底删除一个名片
    public void delete(String cardId)throws Exception;
    //恢复一个名片
    public void rollback(String cardId) throws Exception;
    //根据名片id查询回收站里的名片
    public RecycleBin queryById(String id)throws Exception;
}
