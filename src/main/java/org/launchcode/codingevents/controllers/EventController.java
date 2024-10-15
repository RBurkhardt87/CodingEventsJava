package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    //TODO: MODEL BINDING ::::: AND ADDING VALIDATION
    //* IT IS IMPORTANT TO UNDERSTAND THAT WE NEED VALIDATION ON BOTH THE USER AND SERVER SIDE. WE CREATE ANNOTATIONS FOR OUR FIELDS ON THE MODEL END OF IT, BUT WE
    //* ALSO NEED TO ADDRESS VALIDATION INSIDE OUR CONTROLLERS TO MAKE SURE THAT THOSE ANNOTATIONS ARE BEING READ AND CARRIED OUT BY SPRING BOOT. AND ONCE THEY ARE,
    //* WE NEED CREATE ERROR MESSAGES THAT CAN HELP GUIDE THE USER TO UNDERSTAND HOW TO FIX ANY DATA MISTAKES. MAKE IT MORE USER FRIENDLY...


    @GetMapping
    public String displayEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";

    }

    //lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        return "events/create";
    }


    //TODO: Replace the @RequestParam for eventName and eventDescription to be... @ModelAttribute Event newEvent
    //It will look at the request data and look for fields that match the identifiers
    //We can then just add an event by doing .add(newEvent)   Spring will do the work and create event for us because of the @ModelAttribute
    //? Although we added contactEmail, we don't have to do anything here in the controller because of Model Binding. As long as the email is apart of the form
    //? And is named according to the field, this doesn't need to be updated with anything.

    //? The Model validation is meant to define what the validation params are. It is meant to determine what good data looks like.
    //? The Controller is responsible for checking those criteria.
    //? By adding the @Valid annotation, it says that not only should Spring Boot do Model Binding when it comes in, but it should check any validation annotations on the fields
    //? for this class and make sure that they are satisfied. ---> w/o @Valid on the controller. Our validation annotations on the fields won't work. However, it returns a nasty error display So... we must fix that
    //! The Errors object added into the params will be populated by any validation errors that occur when Spring Boot does validation according to the annotations we assigned on our fields
    //! If there are errors, we want to send the user back to the create event form, which is above this... Because we are sending a "title", we must add the Model model like above.
    //! So, this is saying that if there are errors send to the @GetMapping handler displayCreateEventForm, and if no errors, create the event and redirect to the events page.
    //* After we created that, it stopped displaying the funky error page and instead just had user start back at the createEventForm. But without any message displayed. So we added
    //* "errorMsg" to our model and now we will head over to our create.html template (since that's where we are sending them back to) to get it functioning for us...
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad Data!");
            return "events/create";
        }

        EventData.add(newEvent);
        return "redirect:/events";
    }



    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }



    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }



    //TODO: Create a method to display an edit form
    //PathVariable---> the eventId param goes inside {}
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title);
        return "/events/edit";
    }

    //TODO: Create a method to process the Edit Form
    //Here we are using setters to set the args passed in as the values of the fields of the event
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:/events";
    }

    //Get Handlers typically display the forms and the Post Handlers are going to process the forms
}
