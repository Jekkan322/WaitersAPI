package com.server.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DishOrderKey implements Serializable {

    @Column(name="menu_id")
    Long menuId;

    @Column(name="orders_id")
    Long ordersId;

    public DishOrderKey(){

    }
    public DishOrderKey(Long menuId, Long ordersId) {
        this.menuId = menuId;
        this.ordersId = ordersId;
    }


}
