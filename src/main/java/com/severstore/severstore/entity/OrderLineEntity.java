package com.severstore.severstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORDER_LINE")
public class OrderLineEntity {
    private long id;
    private long count;
    private OrderEntity orderEntity;
    private GoodEntity goodEntity;

    public OrderLineEntity() {

    }

    public OrderLineEntity(long count, OrderEntity orderEntity, GoodEntity goodEntity) {
        this.count = count;
        this.orderEntity = orderEntity;
        this.goodEntity = goodEntity;
    }

    public OrderLineEntity(long id, long count, OrderEntity orderEntity, GoodEntity goodEntity) {
        this.id = id;
        this.count = count;
        this.orderEntity = orderEntity;
        this.goodEntity = goodEntity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COUNT")
    public Long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    @ManyToOne
    @JoinColumn(name = "GOOD_ID")
    public GoodEntity getGoodEntity() {
        return goodEntity;
    }

    public void setGoodEntity(GoodEntity goodEntity) {
        this.goodEntity = goodEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineEntity that = (OrderLineEntity) o;
        return id == that.id &&
                count == that.count &&
                orderEntity.equals(that.orderEntity) &&
                goodEntity.equals(that.goodEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, orderEntity, goodEntity);
    }
}
