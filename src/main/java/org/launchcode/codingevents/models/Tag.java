package org.launchcode.codingevents.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

    @Size(min=1,max=25,message="Tag must be between 1 and 25 characters.")
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public Tag(@Size(min = 1, max = 25, message = "Tag must be between 1 and 25 characters.") @NotBlank String name) {
        this.name = name;
    }

    public Tag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName(){
        return "#"+this.name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
