package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.dto.userDto.UserDto;
import az.ematrix.ticketbot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/save")
    public UserDto create(@RequestBody UserDto userDto) {
        return ticketService.create(userDto);
    }

    @GetMapping("all")
    public List<UserDto> getAllUsers() {
        return ticketService.findAll();
    }

    @GetMapping("findById")
    public UserDto findById(@RequestParam Long id) {
        return ticketService.findById(id);
    }

    @PutMapping("update")
    public UserDto update(@RequestBody Long id, @RequestBody UserDto userDTO) {
        return ticketService.update(id, userDTO);
    }

    @DeleteMapping("deleteAll")
    public void delete() {
        ticketService.delete();
    }

    @DeleteMapping("deleteById")
    public void deleteById(@RequestParam Long id) {
        ticketService.deleteById(id);
    }
}
