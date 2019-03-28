package com.severstore.severstore.service.impl;

import com.severstore.severstore.dao.GoodRepository;
import com.severstore.severstore.dao.OrderLineRepository;
import com.severstore.severstore.dao.OrderRepository;
import com.severstore.severstore.dto.GoodDTO;
import com.severstore.severstore.dto.OrderLineDTO;
import com.severstore.severstore.entity.OrderLineEntity;
import com.severstore.severstore.service.OrderLineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

//    @Autowired
//    GoodService goodService;
//
//    @Autowired
//    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    GoodRepository goodRepository;

    @Override
    public OrderLineDTO getById(Long orderLineId) {
        ModelMapper modelMapper = new ModelMapper();
        OrderLineEntity orderLineEntity = orderLineRepository.findById(orderLineId).get();
        OrderLineDTO orderLineDTO = modelMapper.map(orderLineEntity, OrderLineDTO.class);
        orderLineDTO.setGoodDTO(modelMapper.map(orderLineEntity.getGoodEntity(), GoodDTO.class));
        return orderLineDTO;
//        return new ModelMapper().map(orderLineRepository.findById(orderLineId).get(), OrderLineDTO.class);
    }

    @Override
    public List<OrderLineDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return orderLineRepository.findAll()
                .stream()
                .map(orderLineEntity -> {
                    OrderLineDTO orderLineDTO = modelMapper.map(orderLineEntity, OrderLineDTO.class);
                    orderLineDTO.setGoodDTO(modelMapper.map(orderLineEntity.getGoodEntity(), GoodDTO.class));
                    return orderLineDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderLineDTO save(OrderLineDTO orderLineDTO) {
        ModelMapper modelMapper = new ModelMapper();
        OrderLineEntity orderLineEntity = modelMapper.map(orderLineDTO, OrderLineEntity.class);
        orderLineEntity.setOrderEntity(orderRepository.findById(orderLineDTO.getorderEntityId()).get());
        orderLineEntity.setGoodEntity(goodRepository.findById(orderLineDTO.getGoodDTO().getId()).get());

        orderLineDTO = modelMapper.map(orderLineRepository.save(orderLineEntity), OrderLineDTO.class);
        orderLineDTO.setGoodDTO(modelMapper.map(orderLineEntity.getGoodEntity(), GoodDTO.class));
        return orderLineDTO;
    }

    @Override
    public boolean deleteById(Long orderLineId){
        try {
            orderLineRepository.deleteById(orderLineId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderLineDTO> getOrderLineListByOrder(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        List<OrderLineEntity> orderLineEntitys = orderRepository.findById(id).get().getOrderLineEntities();
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
