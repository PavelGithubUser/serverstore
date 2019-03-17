package com.severstore.severstore.controller;

import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.service.GoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping(value = "/get/{id}")
    public GoodDTO getGoodById(@PathVariable("id") Long id){
        return new ModelMapper().map(goodService.getById(id), GoodDTO.class);
    }

    @PostMapping(value = "/save")
    public GoodDTO getSave(@RequestBody GoodDTO goodDTO){
        ModelMapper modelMapper = new ModelMapper();
        GoodEntity goodEntity = modelMapper.map(goodDTO, GoodEntity.class);
        return modelMapper.map(goodService.save(goodEntity), GoodDTO.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean getDeleteById(@PathVariable("id") Long id){
        return goodService.deleteById(id);
    }

    @GetMapping(value = "/all")
    public List<GoodDTO> getGoodsList(){
        ModelMapper modelMapper = new ModelMapper();
        return goodService.getAll()
                .stream()
                .map(goodEntity -> modelMapper.map(goodEntity, GoodDTO.class))
                .collect(Collectors.toList());
    }

}
