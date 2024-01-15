package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.DataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @GetMapping("start")
    public String start(@RequestParam String data) throws IOException {
        return dataService.start(data);
    }

    @GetMapping("end")
    public String end(@RequestParam String data) throws IOException{
        return dataService.end(data);
    }
    @GetMapping("startEnd")
    public String startEnd(@RequestParam String start,@RequestParam String end) throws IOException {
        return dataService.startAndEnd(start,end);
    }
    @GetMapping("startWithLocalDate")
    public String localDate() throws IOException {
        return dataService.startWithLocalDate();
    }
}
