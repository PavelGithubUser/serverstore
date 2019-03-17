package com.severstore.severstore.controller;

import com.severstore.severstore.dto.OrderDTO;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.entity.OrderEntity;
import com.severstore.severstore.service.OrderService;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/get/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id){
        return new ModelMapper().map(orderService.getById(id), OrderDTO.class);
    }

    @PostMapping(value = "/save")
    public OrderDTO save(@RequestBody OrderDTO orderDTO){
        ModelMapper modelMapper = new ModelMapper();
        OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
        return modelMapper.map(orderService.save(orderEntity), OrderDTO.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteById(@PathVariable("id") Long id){
        return orderService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderDTO> getOrdersList(){
        ModelMapper modelMapper = new ModelMapper();
        return orderService.getAll()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
