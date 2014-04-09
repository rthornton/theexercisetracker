package com.theexercisetracker.persistence;

import com.theexercisetracker.persistence.model.Activity;
import com.theexercisetracker.persistence.repositories.ActivityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class JPATests {

    @Autowired
    ActivityRepository repository;

    @Test
    public void doesJPAWork() {
        Activity activity = new Activity();
        activity.setIdAsString("123123");
        activity = repository.save(activity);

        Activity activity2 = new Activity();
        activity2.setIdAsString("234234");
        activity2 = repository.save(activity2);

        List<Activity> result = repository.findByIdAsString("123123");
        assertThat(result.size(), is(1));
        assertThat(result, hasItem(activity));

        Iterable<Activity> findAll = repository.findAll();
        result = new ArrayList<>();
        for (Activity a : findAll) {
            result.add(a);
        }
        assertThat(result.size(), is(2));
        assertThat(result, hasItem(activity));
        assertThat(result, hasItem(activity2));
    }

    // TODO:  Read how to do dates - what class should Activity.getDate return?
    @Test
    public void loadActivitiesSortedByDate() {
        Activity activity = new Activity();
        activity.setIdAsString("123123");
        activity = repository.save(activity);

        Activity activity2 = new Activity();
        activity2.setIdAsString("234234");
        activity2 = repository.save(activity2);

        Activity activity3 = new Activity();
        activity3.setIdAsString("345345");
        activity3 = repository.save(activity3);

        List<Activity> result = repository.findAllSortedByDate();
        assertThat(result.size(), is(3));
        assertThat(result, hasItem(activity));
        assertThat(result, hasItem(activity2));
        assertThat(result, hasItem(activity3));
        Assert.assertEquals(activity2, result.get(0));
    }
}
