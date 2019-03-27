package com.severstore.severstore.controller;

import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping(value = "/get/{id}")
    public GoodDTO getGoodById(@PathVariable("id") Long id){
        return goodService.getById(id);
    }

    @PostMapping(value = "/save")
    public GoodDTO save(@RequestBody GoodDTO goodDTO) {
        return goodService.save(goodDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        goodService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<GoodDTO> getGoodsList(){
        return goodService.getAll();
    }

    @GetMapping(value = "/allnotaddtoorder/{id}")
    public List<GoodDTO> getAllNotAddToOrder(@PathVariable("id") Long id){
        return goodService.getAllNotAddToOrder(id);
    }

}
