package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.User;

import java.util.List;

public interface UserService {

    User createUser(UserCreateRequest userCreateRequest);

    User getUserById(String userId);

    User getUserByPhoneNumber(String phoneNumber);

    List<User> getAllUser();

    User saveUpdates(User user);

    List<User> getAllStudentUser(String role);
}
