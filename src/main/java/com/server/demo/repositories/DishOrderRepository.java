package com.server.demo.repositories;

import com.server.demo.entities.DishOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DishOrderRepository extends CrudRepository<DishOrderEntity,Long> {
    /*@Query("select sum(amountDishes) from DishOrderEntity WHERE goList=true and orders.waiters.id=?2 and orders.orderTime>?1")
    Integer goListCount(Date date,Long waitersId);*/

    @Query("select sum(amountDishes) from DishOrderEntity WHERE goList=true and orders.waiters.id=?1 and orders.waiters.active=true")
    Integer goListCount(Long waitersId);

    @Query("select sum(amountDishes) from DishOrderEntity WHERE goList=true and orders.id=?1 and orders.waiters.active=true")
    Integer goListOrder(Long ordersId);

    /*@Query("select sum(amountDishes) from DishOrderEntity WHERE goList=true and orders.orderTime>?1")
    Integer sumGoList(Date date);*/

    @Query("select sum(amountDishes) from DishOrderEntity WHERE goList=true and orders.waiters.active=true")
    Integer sumGoList();
}
