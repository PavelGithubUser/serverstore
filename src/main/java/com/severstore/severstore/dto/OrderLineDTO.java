package com.severstore.severstore.dto;

public class OrderLineDTO {
    private long id;
    private long count;
    private long idOrderEntity;
    private GoodDTO goodDTO;

    public OrderLineDTO() {

    }

    public OrderLineDTO(long id, long count, long idOrderEntity, GoodDTO goodDTO) {
        this.id = id;
        this.count = count;
        this.idOrderEntity = idOrderEntity;
        this.goodDTO = goodDTO;
    }

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

    public GoodDTO getGoodDTO() {
        return goodDTO;
    }

    public void setGoodDTO(GoodDTO goodDTO) {
        this.goodDTO = goodDTO;
    }
}
