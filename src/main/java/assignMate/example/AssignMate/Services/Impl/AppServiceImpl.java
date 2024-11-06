package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.CreateRequest.LoginCreateRequest;
import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.Response.LoginResponse;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Services.AppService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private UserService userService;

    @Override
    public boolean signUp(UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest) != null;
    }

    @Override
    public LoginResponse login(LoginCreateRequest loginCreateRequest) {
        String MOBILE_NUMBER_REGEX = "^[1-9][0-9]{9}$";
        if(!loginCreateRequest.getPhoneNumber().matches(MOBILE_NUMBER_REGEX)){
            throw new ApplicationException("Mobile Number Not Valid");
        }
        User user = userService.getUserByPhoneNumber(loginCreateRequest.getPhoneNumber());
        if(user == null) {
            throw new ApplicationException("Mobile number Not Found");
        }
        if(user.getPassword().equals(loginCreateRequest.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLoggedId(user.getUserId());
            loginResponse.setLoggedRole(user.getUserRole());
            return loginResponse;
        }else {
            throw new ApplicationException("Mobile Number or Password not match");
        }
    }
}
