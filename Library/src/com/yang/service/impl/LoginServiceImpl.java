package com.yang.service.impl;

import com.yang.repository.AdminRepository;
import com.yang.repository.ReaderRepository;
import com.yang.repository.impl.AdminRepositoryImpl;
import com.yang.repository.impl.ReaderRepositoryImpl;
import com.yang.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }
        return object;
    }

}
