package com.server.demo.repositories;

import com.server.demo.entities.AchievementsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementsRepository extends CrudRepository<AchievementsEntity,Long> {

}
