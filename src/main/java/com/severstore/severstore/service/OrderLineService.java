package com.severstore.severstore.service;

import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.entity.OrderLineEntity;

import java.util.List;

public interface OrderLineService {

    OrderLineEntity getById(Long idOrderLine);

    List<OrderLineEntity> getAll();

    OrderLineEntity save(OrderLineEntity orderLineEntity);

    boolean deleteById(Long idOrderLine);

}
