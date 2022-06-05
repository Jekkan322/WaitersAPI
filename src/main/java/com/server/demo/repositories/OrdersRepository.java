package com.server.demo.repositories;

import com.server.demo.entities.OrdersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity,Long> {
    /*@Query("SELECT COUNT(waiters.id) FROM OrdersEntity WHERE waiters.id=?2 and orderTime>?1 and orderStatus=true")
    Integer countClosedOrders(Date date, Long waitersId);*/

    @Query("SELECT COUNT(waiters.id) FROM OrdersEntity WHERE waiters.id=?1 and orderStatus=true")
    Integer countClosedOrders(Long waitersId);

    /*@Query("SELECT sum(orderPrice) FROM OrdersEntity WHERE waiters.id=?2 and orderTime>?1 and orderStatus=true")
    Integer waiterRevenue(Date date, Long waitersId);*/

    @Query("SELECT sum(orderPrice) FROM OrdersEntity WHERE waiters.id=?1 and orderStatus=true")
    Integer waiterRevenue(Long waitersId);

    @Query("select max(orderTime) from OrdersEntity where waiters.id=?1 and orderStatus=true")
    Date lastOrderWaiter(Long waitersId);

    @Query("select max(orderTime) from OrdersEntity where orderStatus=true")
    Date lastOrder();

    /*@Query("SELECT sum(orderPrice) FROM OrdersEntity WHERE orderTime>?1 and orderStatus=true")
    Integer sumRevenue(Date date);*/

    @Query("SELECT sum(orderPrice) FROM OrdersEntity WHERE orderStatus=true")
    Integer sumRevenue();

    /*@Query("SELECT COUNT(orderStatus) From OrdersEntity WHERE orderStatus=true and orderTime>?1")
    Integer sumOrdersClosed(Date date);*/

    @Query("SELECT COUNT(orderStatus) From OrdersEntity WHERE orderStatus=true")
    Integer sumOrdersClosed();

    @Query("SELECT AVG(orderPrice) from OrdersEntity WHERE waiters.id=?1")
    Integer averageRevenue(Long id);
}
