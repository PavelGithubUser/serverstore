package com.severstore.severstore.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ORDER")
public class OrderEntity {
    private long id;
    private String client;
    private Date date;
    private String address;

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
    @Column(name = "CLIENT")
    public String getClient() {
        return client;
    }

    public void setClient(String nikname) {
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
    public String setAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
