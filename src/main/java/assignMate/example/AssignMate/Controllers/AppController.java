package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Models.CreateRequest.LoginCreateRequest;
import assignMate.example.AssignMate.Models.CreateRequest.UserCreateRequest;
import assignMate.example.AssignMate.Models.Response.LoginResponse;
import assignMate.example.AssignMate.Services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assignMate/app")
@CrossOrigin(origins = "*")
public class AppController {
    @Autowired
    private AppService appService;

    @PostMapping("/signUp")
    public ApiResponse<Boolean> signUp(@RequestBody UserCreateRequest userCreateRequest){
        return new ApiResponse<Boolean>(appService.signUp(userCreateRequest), HttpStatus.ACCEPTED) ;
    }
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginCreateRequest loginCreateRequest){
        return new ApiResponse<LoginResponse>(appService.login(loginCreateRequest), HttpStatus.ACCEPTED);
    }
}
