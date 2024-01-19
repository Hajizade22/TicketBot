package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.DataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @GetMapping("/start/{startDate}")
    public ResponseEntity<String> start(@PathVariable String startDate) throws IOException {
        return dataService.start(startDate);
    }

    @GetMapping("/end/{endDate}")
    public ResponseEntity<String> end(@PathVariable String endDate) throws IOException {
        return dataService.end(endDate);
    }
    @GetMapping("startAndEnd")
    public ResponseEntity<String> startEnd(@RequestParam String start,@RequestParam String end) throws IOException {
        return dataService.startAndEnd(start,end);
    }
    @GetMapping("startWithLocalDate")
    public ResponseEntity<String> localDate() throws IOException {
        return dataService.startWithLocalDate();
    }
}
