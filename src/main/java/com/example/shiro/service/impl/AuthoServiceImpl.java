package com.example.shiro.service.impl;

import com.example.shiro.entity.Authority;
import com.example.shiro.mapper.AuthoMapper;
import com.example.shiro.service.AuthoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthoServiceImpl implements AuthoService {

    @Autowired
    AuthoMapper authoMapper;

    @Override
    public List<Authority> getAllAuthorities() {
        return authoMapper.getAllAuthorities();
    }
}
