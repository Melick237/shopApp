package com.pomato.phone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;

    private Long price;

    private String name;

    private String imagePath;

    private String description;

    private Integer notation;

    private Long stock;



    public Phone() { }

    public Phone(Long price, String name, String imagePath, String description, Integer notation, Long stock) {
        this.price = price;
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.notation = notation;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNotation() {
        return notation;
    }

    public void setNotation(Integer notation) {
        this.notation = notation;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", notation=" + notation +
                ", stock=" + stock +
                '}';
    }
}
