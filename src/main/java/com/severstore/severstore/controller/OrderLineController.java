package com.severstore.severstore.controller;

import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.service.GoodService;
import com.severstore.severstore.service.OrderLineService;
import com.severstore.severstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return orderLineService.getById(id);
    }

    @PostMapping(value = "/save")
    public OrderLineDTO save(@RequestBody OrderLineDTO orderLineDTO){
        return orderLineService.save(orderLineDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        orderLineService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderLineDTO> getOrderLineList(){
        return orderLineService.getAll();
    }

    @GetMapping(value = "/allbyorder/{id}")
    public List<OrderLineDTO> getOrderLineListByOrder(@PathVariable("id") Long id){
        return orderLineService.getOrderLineListByOrder(id);
    }

}
