package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public OrderLineEntity getById(Long idOrderLine) {
        return orderLineRepository.findById(idOrderLine).get();
    }

    @Override
    public List<OrderLineEntity> getAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLineEntity save(OrderLineEntity orderEntity) {
        return orderLineRepository.save(orderEntity);
    }

    @Override
    public boolean deleteById(Long idOrderLine){
        try {
            orderLineRepository.deleteById(idOrderLine);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
