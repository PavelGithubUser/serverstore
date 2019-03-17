package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderEntity getById(Long idOrder) {
        return orderRepository.findById(idOrder).get();
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public boolean deleteById(Long idOrder){
        try {
            orderRepository.deleteById(idOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
