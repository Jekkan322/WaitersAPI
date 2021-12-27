package com.server.demo.repositories;

import com.server.demo.entities.TypesDishesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesDishesRepository extends CrudRepository<TypesDishesEntity,Long> {
}
