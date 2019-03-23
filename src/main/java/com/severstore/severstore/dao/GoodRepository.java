package com.severstore.severstore.dao;

import com.severstore.severstore.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<GoodEntity, Long> {

    List<GoodEntity> findDistinctByIdNotIn(List<Long> id);

}
