package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    //place to put events
    private static final Map<Integer, Event> events = new HashMap<>();

    //get all events
    public static Collection<Event> getAll(){
        return events.values();
    }

    //add an event
    public static void add(Event event){
        events.put(event.getId(), event);
    }

    //get one event
    public static Event getById(Integer id){
        return events.get(id);
    }

    //remove an event
    public static void remove(Integer id){
        if(events.containsKey(id)){
            events.remove(id);
        }
    }

}
