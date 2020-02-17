package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.addAttribute("event", new Event());
        model.addAttribute("eventTypeValues", EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute @Valid Event createdEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
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
        model.addAttribute("eventTypeValues", EventType.values());
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditEvent(@ModelAttribute @Valid Event editedEvent, int eventId, Errors errors, Model model){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Edit Event " + editedEvent.getName() + " (id=" + editedEvent.getId() + ")");
            return "events/edit";
        }

        Event selectedEvent = EventData.getById(eventId);
        selectedEvent.setName(editedEvent.getName());
        selectedEvent.setDescription(editedEvent.getDescription());
        selectedEvent.setContactEmail(editedEvent.getContactEmail());
        selectedEvent.setLocation(editedEvent.getLocation());
        selectedEvent.setAttendeeMustRegister(editedEvent.isAttendeeMustRegister());
        selectedEvent.setNumberOfAttendees(editedEvent.getNumberOfAttendees());
        selectedEvent.setEntryFee(editedEvent.getEntryFee());

        return "redirect:/events";
    }

}
