package com.server.demo.repositories;

import com.server.demo.entities.WaitersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.server.demo.entities.RatingEntity;

import java.util.*;

public interface WaitersRepository  extends CrudRepository<WaitersEntity,Long> {
    @Query("select u from WaitersEntity u where ((u.lastName like ?1%) and (u.firstName like ?2%) and (u.middleName like ?3%))")
    Collection<WaitersEntity> findByFullName(String lastName, String firstName, String middleName);

    Optional<WaitersEntity> findByUUID(String uuid);

}
