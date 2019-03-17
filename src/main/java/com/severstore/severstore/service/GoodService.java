package com.severstore.severstore.service;

import com.severstore.severstore.entity.GoodEntity;

import java.util.List;

public interface GoodService {

    GoodEntity getById(Long idGood);

    List<GoodEntity> getAll();

    GoodEntity save(GoodEntity goodEntity);

    boolean deleteById(Long idGood);

}
