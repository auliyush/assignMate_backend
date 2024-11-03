package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginCreateRequest {
    private String phoneNumber;
    private String password;
}
