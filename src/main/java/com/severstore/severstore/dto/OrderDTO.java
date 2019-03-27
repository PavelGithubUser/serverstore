
package com.severstore.severstore.dto;

import java.sql.Date;
import java.util.Objects;

public class OrderDTO {
    private long id;
    private String client;
    private Date date;
    private String address;

    public OrderDTO() {

    }

    public OrderDTO(long id, String client, Date date, String address) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id &&
                Objects.equals(client, orderDTO.client) &&
                Objects.equals(date, orderDTO.date) &&
                Objects.equals(address, orderDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, date, address);
    }
}
