package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assignMate/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/By/Id")
    public ApiResponse<User> getUserById(@RequestParam String userId){
        return new ApiResponse<>(userService.getUserById(userId), HttpStatus.OK);
    }

}
