package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MoneyController {
    private final MoneyService moneyService;

    @GetMapping("min")
    public String minPrice(@RequestParam int price) throws IOException {
        return moneyService.min(price);
    }
    @GetMapping("max")
    public String maxPrice(@RequestParam int price) throws IOException {
        return moneyService.max(price);
    }
    @GetMapping("price")
    public String price(@RequestParam int min,int max) throws IOException {
        return moneyService.price(min,max);
    }
    @GetMapping("maxPrice")
    public String maxPrice() throws IOException {
         return moneyService.findMaxPrice();
    }
}
