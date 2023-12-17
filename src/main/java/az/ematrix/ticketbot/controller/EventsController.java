package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @GetMapping("events")
    public String events() throws IOException {
        return eventsService.events();
    }
    @GetMapping("eventsPlace")
    public String eventsPlace (String place) throws IOException {
        return eventsService.processEvents(place);
    }
    @GetMapping("ageLimit")
    public String ageLimit (String place) throws IOException {
        return eventsService.ageLimit(place);
    }
    @GetMapping("where")
    public String where (String place) throws IOException {
        return eventsService.where(place);
    }
}
