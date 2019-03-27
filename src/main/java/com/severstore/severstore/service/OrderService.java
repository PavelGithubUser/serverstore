package com.severstore.severstore.service;

import com.severstore.severstore.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO getById(Long orderId);

    List<OrderDTO> getAll();

    OrderDTO save(OrderDTO orderDTO);

    boolean deleteById(Long orderId);

}
