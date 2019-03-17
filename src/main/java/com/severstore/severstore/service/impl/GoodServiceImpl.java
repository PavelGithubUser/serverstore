package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Override
    public GoodEntity getById(Long idGood) {
        return goodRepository.findById(idGood).get();
    }

    @Override
    public List<GoodEntity> getAll() {
        return goodRepository.findAll();
    }

    @Override
    public GoodEntity save(GoodEntity goodEntity) {
        return goodRepository.save(goodEntity);
    }

    @Override
    public boolean delete(GoodEntity goodEntity) {
        try {
            goodRepository.delete(goodEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
