package pl.edu.agh.schedule;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CalendarManagerTest {

    CalendarManager calendarManager = new CalendarManager();

    @Test
    public void shouldReturnEmptyListWhenEmptyLocation(){
        List<Event> eventsList = calendarManager.getEventsByLocation("");
        Assert.assertFalse(eventsList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyDeviceId(){
        List<Event> eventsList = calendarManager.getEventsById("");
        Assert.assertTrue(eventsList.isEmpty());
    }


}
