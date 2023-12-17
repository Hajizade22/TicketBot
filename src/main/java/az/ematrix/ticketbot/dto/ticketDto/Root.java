package az.ematrix.ticketbot.dto.ticketDto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Root{
    public int status;
    public String result;
    public Response response;
}

