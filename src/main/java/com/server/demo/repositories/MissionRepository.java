package com.server.demo.repositories;

import com.server.demo.entities.MissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends CrudRepository<MissionEntity,Long> {
}
