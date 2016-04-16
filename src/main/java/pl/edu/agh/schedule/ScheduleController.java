package pl.edu.agh.schedule;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ScheduleController {

    @RequestMapping("/")
    public ArrayList<Event> getSchedule(@RequestParam(value="loc") String location) {
        CalendarManager calendar = new CalendarManager();
        return calendar.getEvents(location);
    }
}
