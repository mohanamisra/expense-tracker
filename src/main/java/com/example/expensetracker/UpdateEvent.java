package com.example.expensetracker;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateEvent extends Event {
    public static final EventType<UpdateEvent> UPDATE_EVENT_TYPE = new EventType<>("UPDATE_EVENT");

    public UpdateEvent() {
        super(UPDATE_EVENT_TYPE);
    }
}
