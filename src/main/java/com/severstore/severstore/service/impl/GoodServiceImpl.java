package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.entity.GoodEntity;
import com.severstore.severstore.service.GoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public GoodDTO getById(Long goodId) {
        return new ModelMapper().map(goodRepository.findById(goodId).get(), GoodDTO.class);
    }

    @Override
    public List<GoodDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return goodRepository.findAll()
                .stream()
                .map(goodEntity -> modelMapper.map(goodEntity, GoodDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GoodDTO save(GoodDTO goodDTO) {
        ModelMapper modelMapper = new ModelMapper();
        GoodEntity goodEntity = modelMapper.map(goodDTO, GoodEntity.class);
        return modelMapper.map(goodRepository.save(goodEntity), GoodDTO.class);
    }

    @Override
    public boolean deleteById(Long goodId) {
        try {
            orderLineRepository.deleteAll(goodRepository.findById(goodId).get().getOrderLineEntities());
            goodRepository.deleteById(goodId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GoodDTO> getAllNotAddToOrder(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        List<Long> goodsIdList = orderRepository.findById(id).get().getOrderLineEntities()
                .stream()
                .map(orderLineEntity -> orderLineEntity.getGoodEntity().getId())
                .collect(Collectors.toList());
        return goodRepository.findDistinctByIdNotIn(goodsIdList)
                .stream()
                .map(goodEntity -> modelMapper.map(goodEntity, GoodDTO.class))
                .collect(Collectors.toList());
    }

}
