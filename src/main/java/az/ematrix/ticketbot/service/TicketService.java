package az.ematrix.ticketbot.service;
import az.ematrix.ticketbot.dto.userDto.UserDto;
import az.ematrix.ticketbot.entity.TicketSearchDao;
import az.ematrix.ticketbot.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final MailService mailService;
    private final TicketRepository ticketRepository;

    @Scheduled(fixedRate = 30000)
    public void checkEvents() throws IOException {

        List<TicketSearchDao> all = ticketRepository.findAll();

        for (TicketSearchDao tickets : all) {

            String apiUrl = "https://api.iticket.az/v5/events/search?client=web&q=" + tickets.getSearch();

            Document pod = Jsoup.connect(apiUrl).ignoreContentType(true).get();
            String jsonString = pod.body().text();

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray events = response.getJSONArray("events");

            if (!events.isEmpty()) {
                mailService.sendEmail(tickets.getEmail());
                log.info("OK");

            } else {
                log.info(":(");

            }
        }
    }

    public void create(UserDto dto) {
        log.info("Creating user with DTO: " + dto.toString());
        TicketSearchDao ticketSearchDao = TicketSearchDao.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .search(dto.getSearch())
                .build();

        ticketRepository.save(ticketSearchDao);
        log.info("User created successfully.");
    }

    public List<UserDto> findAll() {
        log.info("Finding all users");
        List<TicketSearchDao> ticketSearchDaoList = ticketRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (TicketSearchDao ticket : ticketSearchDaoList) {
            UserDto userDto = UserDto.builder()
                    .userName(ticket.getUserName())
                    .email(ticket.getEmail())
                    .search(ticket.getSearch())
                    .build();
            userDtoList.add(userDto);
        }
        log.info("Found " + userDtoList.size() + " users");
        return userDtoList;
    }

    public UserDto findById(Long id) {
        log.info("Finding user by ID: " + id);
        TicketSearchDao ticketSearchDao = ticketRepository.findById(id).orElse(null);

        return UserDto.builder()
                .userName(ticketSearchDao.getUserName())
                .email(ticketSearchDao.getEmail())
                .search(ticketSearchDao.getSearch())
                .build();
    }

    public UserDto update(Long id, UserDto userDto) {
        log.info("Updating user with ID: " + id);
        TicketSearchDao existingUser = ticketRepository.findById(id).orElse(null);
        existingUser.setUserName(userDto.getUserName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setSearch(userDto.getSearch());

        TicketSearchDao save = ticketRepository.save(existingUser);

        log.info("User updated successfully");
        return UserDto.builder()
                .userName(save.getUserName())
                .email(save.getEmail())
                .search(save.getEmail())
                .build();
    }

    public void delete() {
        log.info("Deleting all users");
        ticketRepository.deleteAll();
        log.info("All users deleted");
    }

    public void deleteById(Long id) {
        log.info("Deleting user by ID: " + id);
        ticketRepository.deleteById(id);
        log.info("User deleted successfully");
    }
}






