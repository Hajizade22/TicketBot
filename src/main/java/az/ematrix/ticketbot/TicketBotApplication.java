package az.ematrix.ticketbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TicketBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBotApplication.class, args);
    }

}
