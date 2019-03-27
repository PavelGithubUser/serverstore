package com.severstore.severstore.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    private long id;
    private String client;
    private Date date;
    private String address;

    private List<OrderLineEntity> orderLineEntities;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public OrderEntity() {

    }

    public OrderEntity(String client, Date date, String address) {
        this.client = client;
        this.date = date;
        this.address = address;
    }

    public OrderEntity(long id, String client, Date date, String address) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CLIENT")
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Basic
    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    public List<OrderLineEntity> getOrderLineEntities() {
        return orderLineEntities;
    }

    public void setOrderLineEntities(List<OrderLineEntity> orderLineEntities) {
        this.orderLineEntities = orderLineEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                client.equals(that.client) &&
                date.equals(that.date) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, date, address);
    }
}
