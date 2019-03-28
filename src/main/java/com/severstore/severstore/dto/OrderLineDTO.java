package com.severstore.severstore.dto;

import java.util.Objects;

public class OrderLineDTO {
    private long id;
    private long count;
    private long orderEntityId;
    private GoodDTO goodDTO;

    public OrderLineDTO() {

    }

    public OrderLineDTO(long id, long count, long orderEntityId, GoodDTO goodDTO) {
        this.id = id;
        this.count = count;
        this.orderEntityId = orderEntityId;
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

    public long getorderEntityId() {
        return orderEntityId;
    }

    public void setorderEntityId(long orderEntityId) {
        this.orderEntityId = orderEntityId;
    }

    public GoodDTO getGoodDTO() {
        return goodDTO;
    }

    public void setGoodDTO(GoodDTO goodDTO) {
        this.goodDTO = goodDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineDTO that = (OrderLineDTO) o;
        return id == that.id &&
                count == that.count &&
                orderEntityId == that.orderEntityId &&
                Objects.equals(goodDTO, that.goodDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, orderEntityId, goodDTO);
    }
}
