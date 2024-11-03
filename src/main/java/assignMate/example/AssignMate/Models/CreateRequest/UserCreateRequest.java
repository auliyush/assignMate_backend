package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {
    private String userName;
    private String phoneNumber;
    private String password;
    private String role;
}

