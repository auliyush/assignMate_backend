package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Models.CreateRequest.FeedBackCreateRequest;
import assignMate.example.AssignMate.Models.FeedBack;
import assignMate.example.AssignMate.Services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignMate/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/create")
    public ApiResponse<Boolean> createFeedBack(@RequestBody FeedBackCreateRequest feedBackCreateRequest){
        return new ApiResponse<>(feedBackService.createFeedBack(feedBackCreateRequest), HttpStatus.OK);
    }
    @GetMapping("/get/by/submissionId")
    public ApiResponse<FeedBack> getFeedbackBySubmissionId(@RequestParam String submissionId){
        return new ApiResponse<>(feedBackService.getFeedbackBySubmissionId(submissionId), HttpStatus.OK);
    }
}
