
package com.severstore.severstore.dto;

import java.util.Objects;

public class GoodDTO {
    private long id;
    private String name;
    private double price;

    public GoodDTO() {

    }

    public GoodDTO(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodDTO goodDTO = (GoodDTO) o;
        return id == goodDTO.id &&
                Double.compare(goodDTO.price, price) == 0 &&
                Objects.equals(name, goodDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
