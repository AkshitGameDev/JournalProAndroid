package com.journal.journalpro;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class Event {


   public static ArrayList<Event> eventsList = new ArrayList<>();

   public static ArrayList<Event> eventsForDate(LocalDate date){
       ArrayList<Event> events = new ArrayList<>();
       for (Event event: eventsList){
           if(event.getTime().equals(date))
               events.add(event);
       }

       return events;
   }


    private String name;
    private LocalDate time;


    public Event(String name, LocalDate time) {
        this.name = name;
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalDate getTime() {
        return time;
    }
}
