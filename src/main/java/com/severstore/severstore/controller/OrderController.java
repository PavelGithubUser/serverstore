package com.severstore.severstore.controller;

import com.severstore.severstore.dto.OrderDTO;
import com.severstore.severstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/get/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id){
        return orderService.getById(id);
    }

    @PostMapping(value = "/save")
    public OrderDTO save(@RequestBody OrderDTO orderDTO){
        orderDTO.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        return orderService.save(orderDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        orderService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<OrderDTO> getOrdersList(){
        return orderService.getAll();
    }
}
