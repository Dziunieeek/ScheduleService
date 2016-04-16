package pl.edu.agh.schedule;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CalendarManager {

    private static final String EVENT_NAME = "VEVENT";
    private static final String PROPERTY_NAME = "LOCATION";

    Calendar calendar = null;
    FileInputStream fin = null;
    CalendarBuilder builder = null;

    public CalendarManager() {
        try {
            this.fin = new FileInputStream("src/main/resources/kalendarz.ics");
            this.builder = new CalendarBuilder();
            this.calendar = builder.build(this.fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getEvents(String location) {
        ArrayList<Event> list = new ArrayList<Event>();
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext(); ) {
            Component component = (Component) i.next();
            if (component.getName().equals(EVENT_NAME)) {
                if (component.getProperty(PROPERTY_NAME).getValue().equals(location)) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    for (Iterator j = component.getProperties().iterator(); j.hasNext(); ) {
                        Property property = (Property) j.next();
                        map.put(property.getName(), property.getValue());
                    }
                    Event event = new Event(map);
                    list.add(event);
                }
            }
        }
        return list;
    }

}
