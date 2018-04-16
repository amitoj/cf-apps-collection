package com.hanspal.cfapp2.web;

import com.hanspal.cfapp2.model.Event;
import com.hanspal.cfapp2.model.EventType;
import com.hanspal.cfapp2.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository repository;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<Iterable<Event>> get() {
        List<Event> all = (List<Event>)repository.findAll();
        log.info("count = {}", all.size());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/populate-random")
    public ResponseEntity<Void> populateRandomEvents() {
        repository.saveAll(Arrays.asList(
                new Event(UUID.randomUUID().toString(), new Date(), EventType.USER_REGISTRATION, "User amitoj registered"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.USER_LOGIN, "User amitoj logged in"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.ADD_ITEM_BASKET, "Item Lego Batman added"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.ADD_ITEM_BASKET, "Item Lego mini figures added"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.ADD_ITEM_BASKET, "Item Minimaxi Scooter added"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.MODIFY_ITEM_BASKET, "Item Lego mini figures quantity changed"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.DELETE_ITEM_BASKET, "Item Lego Batman removed"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.CHECKOUT, "Checkout and pay"),
                new Event(UUID.randomUUID().toString(), new Date(), EventType.USER_LOGOUT, "User amitoj logout")
        ));
        return ResponseEntity.ok().build();
    }


}
