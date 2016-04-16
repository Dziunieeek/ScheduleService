package pl.edu.agh.schedule;

import java.util.HashMap;

public class Event {

    public HashMap<String, String> propertiesMap;

    public Event(HashMap<String, String> map){
        this.propertiesMap = map;
    }
}
