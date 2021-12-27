package com.server.demo.repositories;

import com.server.demo.entities.DishOrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishOrderRepository extends CrudRepository<DishOrderEntity,Long> {
}
