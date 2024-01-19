package az.ematrix.ticketbot.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private String email;

    private String password;

    private String role;


    public SignUpDto(String email, String password,String role) {
        this.email = email;
        this.password = password;
        this.role=role;
    }

}
