package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Models.CreateRequest.SubmissionCreateRequest;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionRequest;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionStatusRequest;
import assignMate.example.AssignMate.Services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignMate/submission")
@CrossOrigin(origins = "*")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/create")
    public ApiResponse<Boolean> createSubmission(@RequestBody SubmissionCreateRequest submissionCreateRequest){
        return new ApiResponse<>(submissionService.createSubmission(submissionCreateRequest), HttpStatus.OK);
    }
    @GetMapping("/get/all/by/assignmentId")
    public ApiResponse<List<Submission>> getAllSubmissionByAssignmentId(@RequestParam String assignmentId){
        return new ApiResponse<>(submissionService.getAllSubmissionByAssignmentId(assignmentId),HttpStatus.OK);
    }
    @GetMapping("/get/by/userId")
    public ApiResponse<Submission> getSubmissionByUserId(@RequestParam String userId){
        return new ApiResponse<>(submissionService.getSubmissionByUserId(userId), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ApiResponse<Boolean> submissionUpdate(@RequestBody UpdateSubmissionRequest updateSubmissionRequest){
        return new ApiResponse<>(submissionService.updateSubmission(updateSubmissionRequest), HttpStatus.OK);
    }
    @PutMapping("/update/submission/status")
    public ApiResponse<Boolean> updateSubmissionStatus(@RequestBody UpdateSubmissionStatusRequest statusRequest){
        return new ApiResponse<>(submissionService.updateSubmissionStatus(statusRequest), HttpStatus.OK);
    }
}
