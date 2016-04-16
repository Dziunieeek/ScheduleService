package pl.edu.agh.schedule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    private CalendarManager calendar = new CalendarManager();

    @RequestMapping("/events-by-location")
    public List<Event> getScheduleByLocation(@RequestParam(value = "loc") String location) {
        return calendar.getEventsByLocation(location);
    }

    @RequestMapping("/events-by-id")
    public List<Event> getScheduleById(@RequestParam(value = "id") String id) {
        return calendar.getEventsById(id);
    }
}
