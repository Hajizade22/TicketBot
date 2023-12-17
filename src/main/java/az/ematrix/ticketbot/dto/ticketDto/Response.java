package az.ematrix.ticketbot.dto.ticketDto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Response {
    public ArrayList<Event> events;
    public ArrayList<Object> venues;
}
