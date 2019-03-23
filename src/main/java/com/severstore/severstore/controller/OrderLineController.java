package com.severstore.severstore.controller;

import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.GoodService;
import com.severstore.severstore.service.OrderLineService;
import com.severstore.severstore.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orderline")
public class OrderLineController {

    @Autowired
    GoodService goodService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderLineService orderLineService;

    @GetMapping(value = "/get/{id}")
    public OrderLineDTO getOrderLineById(@PathVariable("id") Long id){
        ModelMapper modelMapper = new ModelMapper();
        OrderLineEntity orderLineEntity = orderLineService.getById(id);
        OrderLineDTO orderLineDTO = modelMapper.map(orderLineEntity, OrderLineDTO.class);
        orderLineDTO.setGoodDTO(modelMapper.map(orderLineEntity.getGoodEntity(), GoodDTO.class));
        return orderLineDTO;
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody OrderLineDTO orderLineDTO){
        ModelMapper modelMapper = new ModelMapper();
        OrderLineEntity orderLineEntity = modelMapper.map(orderLineDTO, OrderLineEntity.class);
        orderLineEntity.setOrderEntity(orderService.getById(orderLineDTO.getIdOrderEntity()));
        orderLineEntity.setGoodEntity(goodService.getById(orderLineDTO.getGoodDTO().getId()));
        orderLineService.save(orderLineEntity);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        orderLineService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderLineDTO> getOrderLineList(){
        ModelMapper modelMapper = new ModelMapper();
        return orderLineService.getAll()
                .stream()
                .map(orderLineEntity -> modelMapper.map(orderLineEntity, OrderLineDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/allbyorder/{id}")
    public List<OrderLineDTO> getOrderLineListByOrder(@PathVariable("id") Long id){
        ModelMapper modelMapper = new ModelMapper();
        List<OrderLineEntity> orderLineEntitys = orderService.getById(id).getOrderLineEntities();
        List<OrderLineDTO> orderLinesDTO = orderLineEntitys
                .stream()
                .map(orderLineEntity -> {
                    OrderLineDTO orderLineDTO = modelMapper.map(orderLineEntity, OrderLineDTO.class);
                    orderLineDTO.setGoodDTO(modelMapper.map(orderLineEntity.getGoodEntity(), GoodDTO.class));
                    return orderLineDTO;
                })
                .collect(Collectors.toList());
        return orderLinesDTO;
    }

}
