package com.theexercisetracker.persistence.repositories;


import com.theexercisetracker.persistence.model.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByIdAsString(String idDateAsString);
}
