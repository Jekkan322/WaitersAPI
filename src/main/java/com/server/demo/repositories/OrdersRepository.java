package com.server.demo.repositories;

import com.server.demo.entities.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity,Long> {
}
