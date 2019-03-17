package com.severstore.severstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_LINE")
public class OrderLineEntity {
    private long id;
    private long count;
    private OrderEntity orderEntity;
    private GoodEntity goodEntity;

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
}
