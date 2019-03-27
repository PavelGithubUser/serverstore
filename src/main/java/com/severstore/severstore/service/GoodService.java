package com.severstore.severstore.service;

import com.severstore.severstore.dto.GoodDTO;

import java.util.List;

public interface GoodService {

    GoodDTO getById(Long idGood);

    List<GoodDTO> getAll();

    GoodDTO save(GoodDTO goodDTO);

    boolean deleteById(Long goodId);

    List<GoodDTO> getAllNotAddToOrder(Long orderId);

}
