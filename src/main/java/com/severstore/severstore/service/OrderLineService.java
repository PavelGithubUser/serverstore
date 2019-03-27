package com.severstore.severstore.service;

import com.severstore.severstore.dto.OrderLineDTO;

import java.util.List;

public interface OrderLineService {

    OrderLineDTO getById(Long orderLineId);

    List<OrderLineDTO> getAll();

    OrderLineDTO save(OrderLineDTO orderLineEntity);

    boolean deleteById(Long orderLineId);

    List<OrderLineDTO> getOrderLineListByOrder(Long id);

}
