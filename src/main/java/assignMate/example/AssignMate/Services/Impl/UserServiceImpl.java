package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.UserRepository;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {
        String MOBILE_NUMBER_REGEX = "^[1-9][0-9]{9}$";
        if(getUserByPhoneNumber(userCreateRequest.getPhoneNumber()) != null){
            throw new ApplicationException("Phone Number already Exist");
        } else if (!userCreateRequest.getPhoneNumber().matches(MOBILE_NUMBER_REGEX)) {
            throw new ApplicationException("Wrong Phone Number");
        } else {
            User user = new User();
            user.setUserName(userCreateRequest.getUserName());
            user.setPhoneNumber(userCreateRequest.getPhoneNumber());
            user.setPassword(userCreateRequest.getPassword());
            user.setUserRole(userCreateRequest.getRole());
            return userRepository.save(user);
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Boolean addNotificationInUser(Notification notification, String adminId) {
        for (User user : getAllUser()){
            if(!user.getUserId().equals(adminId)){
                user.getNotifications().add(notification);
                userRepository.save(user);
            }
        }
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
