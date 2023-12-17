package az.ematrix.ticketbot.dto.ticketDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    public String slug;
    public String category_slug;
    public String poster_url;
    public String poster_bg_url;
    public String name;
    public Object external_url;
    public Object meta_title;
    public Object meta_description;
    public Object meta_keywords;
}
