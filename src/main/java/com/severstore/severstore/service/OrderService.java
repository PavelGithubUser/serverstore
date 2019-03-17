package com.severstore.severstore.service;

import com.severstore.severstore.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity getById(Long idOrder);

    List<OrderEntity> getAll();

    OrderEntity save(OrderEntity orderEntity);

    boolean delete(OrderEntity orderEntity);

}
