package com.yang.repository;

import com.yang.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
