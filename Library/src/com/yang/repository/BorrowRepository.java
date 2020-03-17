package com.yang.repository;

import com.yang.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerid,String borrowtime,String returntime,Integer adminid,Integer state);
    public int count(Integer id);
    public int countByState(Integer state);
    public List<Borrow> findAllByReaderId(Integer readerid,Integer index,Integer limit);
    public List<Borrow> findAllByState(Integer state,Integer index,Integer limit);
    public void handle(Integer borrowId,Integer state,Integer adminId);
}
