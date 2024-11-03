package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.User;

public interface UserService {

    User createUser(UserCreateRequest userCreateRequest);

    User getUserById(String userId);

    User getUserByPhoneNumber(String phoneNumber);
}
