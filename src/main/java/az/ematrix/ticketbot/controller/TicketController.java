package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.dto.userDto.UserDto;
import az.ematrix.ticketbot.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/tickets") // controllerler++
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public void create(@Valid @RequestBody UserDto userDto) {

        ticketService.create(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {//digerlerinde
        UserDto user = ticketService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody UserDto userDTO ) {

        UserDto updatedUser = ticketService.update(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void delete() {
        ticketService.delete();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}
