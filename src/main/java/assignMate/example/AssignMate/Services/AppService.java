package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.LoginCreateRequest;
import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.Response.LoginResponse;

public interface AppService {

    boolean signUp(UserCreateRequest userCreateRequest);

    LoginResponse login(LoginCreateRequest loginCreateRequest);
}
