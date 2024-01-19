package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.TicketDetailsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/ticketDetails")
public class TicketDetailsController {
    private final TicketDetailsService ticketDetailsService;

    @GetMapping("/minPrice/{minPrice}")
    public ResponseEntity<String> minPrice(@PathVariable double minPrice) throws IOException {
        return ticketDetailsService.ticketDetailsWithMinPrice(minPrice);
    }

    @GetMapping("/maxPrice/{maxPrice}")
    public ResponseEntity<String> maxPrice(@PathVariable double maxPrice) throws IOException {
        return ticketDetailsService.ticketDetailsWithMaxPrice(maxPrice);
    }


    @GetMapping("ticket-details-by-price")
    public ResponseEntity<String> price(@RequestParam double minPrice, @RequestParam double maxPrice) throws IOException {
        return ticketDetailsService.ticketDetailsWithMinAndMaxPrice(minPrice, maxPrice);
    }

    @GetMapping("findMaxPrice")
    public ResponseEntity<String> findMaxPrice() throws IOException {
        return ticketDetailsService.findMaxPrice();
    }
}
