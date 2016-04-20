package pl.edu.agh.schedule;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    private final static String calendarLocation = "src/main/resources/kalendarz.ics";
    private final static String deviceListLocation = "src/main/resources/baza.csv";

    public static Calendar readCalendar() throws IOException, ParserException {
        CalendarBuilder builder = new CalendarBuilder();
        return builder.build(new FileInputStream(calendarLocation));
    }

    public static Map<String, String> readDeviceList() throws IOException {
        Map<String, String> deviceList = new HashMap<>();
        List<String> deviceData = Files.readAllLines(Paths.get(deviceListLocation));
        for (String line : deviceData) {
            String[] splitLine = line.split(",");
            deviceList.put(splitLine[0], splitLine[1]);
        }
        return deviceList;
    }

    public static long getLastModifiedCalendar() {
        File file = new File(calendarLocation);
        return file.lastModified();
    }

    public static long getLastModifiedDeviceList() {
        File file = new File(deviceListLocation);
        return file.lastModified();
    }

}
