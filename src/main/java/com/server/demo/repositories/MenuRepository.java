package com.server.demo.repositories;

import com.server.demo.entities.MenuEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity,Long> {
}
