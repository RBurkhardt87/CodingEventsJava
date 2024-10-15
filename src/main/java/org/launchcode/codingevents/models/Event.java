package org.launchcode.codingevents.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Created by Chris Bay
 */
public class Event {

    private int id;
    private static int nextId = 1;


    //? We can make sure that the field is required by using @NotBlank. This will ensure that whitespaces aren't counted as characters
    //? You can specify the min and max characters of the field input by user by using @Size
    //? You can add a specific message to help the user understand what they entered wrong
    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;


    //? We are allowing the field to be blank if user decides, or up to 500 characters.
    @Size(max = 500, message = "Description too long!")
    private String description;


    //? We added a contactEmail field using the @Email annotation-- specify that the string should conform to the rules that dictate common emails
    //? After creating the contactEmail field, we added it to the constructor and created getters and setters for it as well.
    @Email(message = "Invalid email. Try again")
    private String contactEmail;


    public Event(String name, String description, String contactEmail) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
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

    //* My getters and setters included the @Email in them, but I removed since they weren't generated in the follow along video
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
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
