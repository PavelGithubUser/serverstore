package com.severstore.severstore.dao;

import com.severstore.severstore.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
}
