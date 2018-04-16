package com.hanspal.cfapp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
public class Event {

    @Id
    private String id;

    private Date timestamp;
    private EventType type;
    private String description;

    public Event() {
    }

}
