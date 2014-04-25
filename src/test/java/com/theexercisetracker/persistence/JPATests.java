package com.theexercisetracker.persistence;

import com.theexercisetracker.persistence.model.Activity;
import com.theexercisetracker.persistence.repositories.ActivityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class JPATests {

    @Autowired
    ActivityRepository repository;

    @Before
    public void clearRepository() {
        repository.deleteAll();
    }

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

    @Test
    public void returnActivitiesSortedByZonedDateTime() {
        Activity activity1 = new Activity();
        activity1.setIdAsString("123123");
        activity1.setDistanceInMeters(10);
        activity1.setStartTime(ZonedDateTime.of(2014, 1, 1, 10, 0, 0, 0, ZoneId.of("GMT")));
        activity1 = repository.save(activity1);

        Activity activity2 = new Activity();
        activity2.setIdAsString("0234234");
        activity2.setDistanceInMeters(11);
        activity2.setStartTime(ZonedDateTime.of(2014, 1, 2, 10, 0, 0, 0, ZoneId.of("GMT")));
        activity2 = repository.save(activity2);

        Activity activity3 = new Activity();
        activity3.setIdAsString("345345");
        activity3.setDistanceInMeters(9);
        activity3.setStartTime(ZonedDateTime.of(2014, 1, 1, 10, 0, 1, 0, ZoneId.of("GMT")));
        activity3 = repository.save(activity3);

        Sort sort = new Sort("startTime");
        Iterable<Activity> result = repository.findAll(sort);
        Iterator iter = result.iterator();
        Assert.assertEquals(iter.next(), activity1);
        Assert.assertEquals(iter.next(), activity3);
        Assert.assertEquals(iter.next(), activity2);
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void returningSortedActivitiesWorks() {
        Activity activity1 = new Activity();
        activity1.setIdAsString("123123");
        activity1.setDistanceInMeters(10);
        activity1 = repository.save(activity1);

        Activity activity2 = new Activity();
        activity2.setIdAsString("0234234");
        activity2.setDistanceInMeters(11);
        activity2 = repository.save(activity2);

        Activity activity3 = new Activity();
        activity3.setIdAsString("345345");
        activity3.setDistanceInMeters(9);
        activity3 = repository.save(activity3);

        Sort sort = new Sort("idAsString");
        Iterable<Activity> result = repository.findAll(sort);
        Iterator iter = result.iterator();
        Assert.assertEquals(iter.next(), activity2);
        Assert.assertEquals(iter.next(), activity1);
        Assert.assertEquals(iter.next(), activity3);
        Assert.assertFalse(iter.hasNext());

        sort = new Sort("distanceInMeters");
        result = repository.findAll(sort);
        iter = result.iterator();
        Assert.assertEquals(iter.next(), activity3);
        Assert.assertEquals(iter.next(), activity1);
        Assert.assertEquals(iter.next(), activity2);
        Assert.assertFalse(iter.hasNext());

        sort = new Sort(Sort.Direction.DESC, "distanceInMeters");
        result = repository.findAll(sort);
        iter = result.iterator();
        Assert.assertEquals(iter.next(), activity2);
        Assert.assertEquals(iter.next(), activity1);
        Assert.assertEquals(iter.next(), activity3);
        Assert.assertFalse(iter.hasNext());
    }
}
