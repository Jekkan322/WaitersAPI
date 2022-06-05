package com.server.demo.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="menu")
public class MenuEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="typeDishes_id")
    private TypesDishesEntity typeOfDish;

    private String dishName;
    private int price;
    private int quantityInStock;
    private boolean goList;
    private boolean active;

    @OneToMany(mappedBy = "menu")
    Set<DishOrderEntity> dishOrder;

    public MenuEntity(){

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isGoList() {
        return goList;
    }

    public void setGoList(boolean goList) {
        this.goList = goList;
    }

    public Set<DishOrderEntity> getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(Set<DishOrderEntity> dishOrder) {
        this.dishOrder = dishOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypesDishesEntity getTypeOfDish() {
        return typeOfDish;
    }

    public void setTypeOfDish(TypesDishesEntity typeOfDish) {
        this.typeOfDish = typeOfDish;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
