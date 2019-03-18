package com.severstore.severstore.controller;

import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.OrderLineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {

    @Autowired
    OrderLineService orderLineService;

    @GetMapping(value = "/get/{id}")
    public OrderLineDTO getOrderLineById(@PathVariable("id") Long id){
        return new ModelMapper().map(orderLineService.getById(id), OrderLineDTO.class);
    }

    @PostMapping(value = "/save")
    public OrderLineDTO save(@RequestBody OrderLineDTO orderLineDTO){
        ModelMapper modelMapper = new ModelMapper();
        OrderLineEntity orderLineEntity = modelMapper.map(orderLineDTO, OrderLineEntity.class);
        return modelMapper.map(orderLineService.save(orderLineEntity), OrderLineDTO.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteById(@PathVariable("id") Long id){
        return orderLineService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderLineDTO> getOrderLinesList(){
        ModelMapper modelMapper = new ModelMapper();
        return orderLineService.getAll()
                .stream()
                .map(orderLineEntity -> modelMapper.map(orderLineEntity, OrderLineDTO.class))
                .collect(Collectors.toList());
    }
}