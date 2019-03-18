package com.severstore.severstore.dto;

public class OrderLineDTO {
    private long id;
    private long count;
    private long idOrderEntity;
    private long idGoodEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getIdOrderEntity() {
        return idOrderEntity;
    }

    public void setIdOrderEntity(long idOrderEntity) {
        this.idOrderEntity = idOrderEntity;
    }

    public long getIdGoodEntity() {
        return idGoodEntity;
    }

    public void setIdGoodEntity(long idGoodEntity) {
        this.idGoodEntity = idGoodEntity;
    }
}
