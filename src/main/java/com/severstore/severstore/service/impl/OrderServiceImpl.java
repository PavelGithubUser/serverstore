package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.OrderDTO;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public OrderDTO getById(Long orderId) {
        return new ModelMapper().map(orderRepository.findById(orderId).get(), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return orderRepository.findAll()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        OrderEntity goodEntity = modelMapper.map(orderDTO, OrderEntity.class);
        return modelMapper.map(orderRepository.save(goodEntity), OrderDTO.class);
    }

    @Override
    public boolean deleteById(Long orderId){
        try {
            orderLineRepository.deleteAll(orderRepository.findById(orderId).get().getOrderLineEntities());
            orderRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
