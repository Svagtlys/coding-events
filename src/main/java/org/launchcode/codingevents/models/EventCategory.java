package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {

    @NotNull(message = "Category must be named.")
    @NotBlank(message = "Category must be named.")
    private String name;

    public EventCategory(){}

    public EventCategory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
