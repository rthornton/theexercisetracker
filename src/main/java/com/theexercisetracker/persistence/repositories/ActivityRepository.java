package com.theexercisetracker.persistence.repositories;


import com.theexercisetracker.persistence.model.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long> {
    List<Activity> findByIdAsString(String idDateAsString);
}
