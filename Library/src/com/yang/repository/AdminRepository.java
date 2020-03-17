package com.yang.repository;

import com.yang.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
