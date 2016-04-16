package pl.edu.agh.schedule;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CalendarManager {

    private static final String EVENT_NAME = "VEVENT";
    private static final String PROPERTY_LOCATION_NAME = "LOCATION";

    private Calendar calendar;
    private Map<String, String> deviceList = new HashMap<>();

    public CalendarManager() {
        try {
            readCalendar();
            readDeviceList();
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    private void readCalendar() throws IOException, ParserException {
        CalendarBuilder builder = new CalendarBuilder();
        this.calendar = builder.build(new FileInputStream("src/main/resources/kalendarz.ics"));
    }

    private void readDeviceList() throws IOException {
        List<String> deviceData = Files.readAllLines(Paths.get("src/main/resources/baza.csv"));
        for (String line : deviceData) {
            String[] splitLine = line.split(",");
            deviceList.put(splitLine[0], splitLine[1]);
        }
    }

    public List<Event> getEventsByLocation(String location) {
        List<Event> list = new LinkedList<>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {
            Component component = (Component) i.next();
            if (component.getName().equals(EVENT_NAME)) {
                if (component.getProperty(PROPERTY_LOCATION_NAME).getValue().equals(location)) {
                    Map<String, String> map = new HashMap<>();
                    for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                        Property property = (Property) j.next();
                        map.put(property.getName(), property.getValue());
                    }
                    list.add(new Event(map));
                }
            }
        }
        return list;
    }

    public List<Event> getEventsById(String id) {
        return getEventsByLocation(deviceList.get(id));
    }

}
