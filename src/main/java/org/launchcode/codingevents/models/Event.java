package org.launchcode.codingevents.models;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Chris Bay
 */
public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;


    //TODO: Add a field to collect info about where the event will take place. Should NOT be null or blank
    @NotBlank(message = "location is required.")
    @NotNull
    private String location;

    //TODO: Add a field to collect info about whether an attendee must register for the event or not. For validation purposes, make field only able to be marked as true
    @AssertTrue
    private boolean mustRegister = true;


    //TODO: Add a field to collect info about the number of attendees of the event. Valid values for this field should be any number over zero.
    @Positive(message="Number of attendees must be one or more.")
    private int numberOfAttendees;


    //TODO: Add a field of my choosing and use a new annotation
    //@Future: The annotated element must be an instant, date or time in the future.
    @NotNull(message = "Date cannot be left blank.")
    @Future(message = "The event must be set for an upcoming date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    //TODO: BONUS-- Add another field of my choosing.
    @NotNull(message = "Time cannot be left blank.")
    @DateTimeFormat(pattern = "HH:mm a") // Format for time
    private LocalTime time;





    public Event(String name, String description, String contactEmail, String location, int numberOfAttendees, Date date, LocalTime time) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.mustRegister = true;
        this.numberOfAttendees = numberOfAttendees;
        this.date = date;
        this.time = time;


    }

    public Event() {
        this.id = nextId;
        nextId++;
    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }






    //TODO: Create getters & setters for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    //TODO: Create a getter only, we don't want to set mustRegister as anything other than true.
    public boolean isMustRegister() {
        return mustRegister;
    }


    //TODO: Create a getter/setter for numberOfAttendees
    @Positive(message = "Number of attendees must be one or more.")
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(@Positive(message = "Number of attendees must be one or more.") int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }


    //TODO: Create getter/setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    //TODO: Create getters/setters for time
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }






    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
