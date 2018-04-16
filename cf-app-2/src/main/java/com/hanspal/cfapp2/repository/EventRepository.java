package com.hanspal.cfapp2.repository;

import com.hanspal.cfapp2.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {
}
