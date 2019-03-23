package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    OrderRepository orderRepository;

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
    public boolean deleteById(Long idGood) {
        try {
            goodRepository.deleteById(idGood);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GoodEntity> getAllNotAddToOrder(Long id) {
        List<Long> goodsIdList = orderRepository.findById(id).get().getOrderLineEntities()
                .stream()
                .map(orderLineEntity -> orderLineEntity.getGoodEntity().getId())
                .collect(Collectors.toList());
        return goodRepository.findDistinctByIdNotIn(goodsIdList);
    }

}
