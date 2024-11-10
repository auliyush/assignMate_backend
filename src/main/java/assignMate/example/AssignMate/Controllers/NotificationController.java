package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("assignMate/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @DeleteMapping("/delete")
    public ApiResponse<Boolean> deleteNotification(@RequestParam String userId, String notificationId){
        return new ApiResponse<>(notificationService.deleteNotification(userId, notificationId), HttpStatus.OK);
    }
    @PutMapping("/update/seen/status")
    public ApiResponse<Boolean> updateSeenStatus(@RequestParam String notificationId, String userId){
        return new ApiResponse<>(notificationService.updateSeenStatus(notificationId, userId), HttpStatus.OK);
    }
}
