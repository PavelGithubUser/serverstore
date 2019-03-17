package com.severstore.severstore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GOODS")
public class GoodEntity {
    private long id;
    private String name;
    private double price;

    private Set<OrderLineEntity> orderLineEntities;

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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "goodEntity")
    public Set<OrderLineEntity> getOrderLineEntities() {
        return orderLineEntities;
    }

    public void setOrderLineEntities(Set<OrderLineEntity> orderLineEntities) {
        this.orderLineEntities = orderLineEntities;
    }
}
