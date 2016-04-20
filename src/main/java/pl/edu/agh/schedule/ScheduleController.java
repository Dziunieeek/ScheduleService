package pl.edu.agh.schedule;

import net.fortuna.ical4j.data.ParserException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleController {

    private CalendarManager calendar = new CalendarManager();


    @RequestMapping("/events")
    public List<Event> getSchedule(@RequestParam(value = "date") long date) {
        return calendar.getEvents(date);
    }

    @RequestMapping("/devices")
    public Map<String, String> getDevices(@RequestParam(value = "date") long date) {
        return calendar.getDeviceList(date);
    }

    @RequestMapping("/update")
    public void update() {
        try {
            calendar.setCurrentSettings();
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/events-by-location")
    public List<Event> getScheduleByLocation(@RequestParam(value = "loc") String location) {
        return calendar.getEventsByLocation(location);
    }

    @RequestMapping("/events-by-id")
    public List<Event> getScheduleById(@RequestParam(value = "id") String id) {
        return calendar.getEventsById(id);
    }

}
