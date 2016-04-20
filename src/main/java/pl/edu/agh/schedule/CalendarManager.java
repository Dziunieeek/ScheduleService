package pl.edu.agh.schedule;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.IOException;
import java.util.*;

public class CalendarManager {

    private static final String EVENT_NAME = "VEVENT";
    private static final String PROPERTY_LOCATION_NAME = "LOCATION";


    private Calendar calendar;
    private Map<String, String> deviceList = new HashMap<>();
    private long lastModifiedCalendar;
    private long lastModifiedDeviceList;

    public CalendarManager() {
        try {
            setCurrentSettings();
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentSettings() throws IOException, ParserException {
            calendar = FileManager.readCalendar();
            deviceList = FileManager.readDeviceList();
            lastModifiedCalendar = FileManager.getLastModifiedCalendar();
            lastModifiedDeviceList = FileManager.getLastModifiedDeviceList();
    }

    public Map<String, String> getDeviceList(long date){
        if(lastModifiedDeviceList > date){
            return deviceList;
        }
        return null;
    }

    public List<Event> getEvents(long date){
        if(lastModifiedCalendar > date){
            return getAllEvents();
        }
        return null;
    }

    private List<Event> getAllEvents() {
        List<Event> list = new LinkedList<>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {
            Component component = (Component) i.next();
            if (component.getName().equals(EVENT_NAME)) {
                    Map<String, String> map = new HashMap<>();
                    for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                        Property property = (Property) j.next();
                        map.put(property.getName(), property.getValue());
                    }
                    list.add(new Event(map));
                }
            }
        return list;
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
