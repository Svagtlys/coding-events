package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    //lives at /events
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute Event createdEvent){
        EventData.add(createdEvent);

        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required=false) int[] eventIds){
        if(eventIds != null){
            for(int id : eventIds){
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String renderEditEventForm(Model model, @PathVariable int eventId){
        Event selectedEvent = EventData.getById(eventId);
        model.addAttribute("event", selectedEvent);
        model.addAttribute("title", "Edit Event " + selectedEvent.getName() + " (id=" + selectedEvent.getId() + ")");
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditEvent(int eventId, String name, String description){
        Event selectedEvent = EventData.getById(eventId);
        selectedEvent.setName(name);
        selectedEvent.setDescription(description);

        return "redirect:/events";
    }

}
