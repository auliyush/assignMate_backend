package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.SubmissionCreateRequest;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionRequest;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionStatusRequest;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.SubmissionRepository;
import assignMate.example.AssignMate.Services.AssignmentService;
import assignMate.example.AssignMate.Services.NotificationService;
import assignMate.example.AssignMate.Services.SubmissionService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private NotificationService notificationService;

    @Override
    public Boolean createSubmission(SubmissionCreateRequest submissionCreateRequest) {
        Assignment assignment = assignmentService.getAssignmentById(submissionCreateRequest.getAssignmentId());
        // check assignment validation
        if(assignment != null){
            if(!assignment.isActiveStatus()){
                throw new ApplicationException("Assignment Due Date is complete");
            }
        }else {
            throw new ApplicationException("Assignment not Exist");
        }
        User user = userService.getUserById(submissionCreateRequest.getUserId());
        if(user == null){
            throw new ApplicationException("User not Exist");
        }
        if(getSubmissionByUserIdAndAssignmentId(submissionCreateRequest.getUserId(),
                submissionCreateRequest.getAssignmentId()) != null){
            throw new ApplicationException("You Already Submitted");
        }
        Submission submission = getSubmission(submissionCreateRequest);
        submissionRepository.save(submission);
        CompletableFuture.runAsync(() -> {
            try{
                notificationService.submissionCreateNotification(submission,assignment.getAdminId());
            }catch (Exception e){
                System.out.println(e);
            }
        });
        return true;
    }
    // this method for set submission data from createRequest (improve code readability)
    private static Submission getSubmission(SubmissionCreateRequest submissionCreateRequest) {
        Submission submission = new Submission();
        submission.setUserId(submissionCreateRequest.getUserId());
        submission.setUserName(submissionCreateRequest.getUserName());
        submission.setAssignmentId(submissionCreateRequest.getAssignmentId());
        submission.setSubmissionTitle(submissionCreateRequest.getSubmissionTitle());
        submission.setSubmissionDescription(submissionCreateRequest.getSubmissionDescription());
        submission.setSubmissionDate(submissionCreateRequest.getSubmissionDate());
        submission.setFile(submissionCreateRequest.getFile());
        submission.setSubmissionStatus("Pending");
        return submission;
    }

    @Override
    public List<Submission> getAllSubmissionByAssignmentId(String assignmentId) {
        if(assignmentService.getAssignmentById(assignmentId) == null){
            throw new ApplicationException("Assignment not exist");
        }
        return submissionRepository.findAllByAssignmentId(assignmentId);
    }

    @Override
    public Submission getSubmissionByUserId(String userId) {
        if(userService.getUserById(userId) == null){
            throw new ApplicationException("User Not Exist");
        }
        return submissionRepository.findByUserId(userId);
    }

    @Override
    public Submission getSubmissionById(String submissionId) {
        return submissionRepository.findBySubmissionId(submissionId);
    }

    @Override
    public Submission getSubmissionByUserIdAndAssignmentId(String userId, String assignmentId) {
        return submissionRepository.findByUserIdAndAssignmentId(userId, assignmentId);
    }

    @Override
    public Boolean updateSubmission(UpdateSubmissionRequest updateSubmissionRequest) {
        Submission submission = getSubmissionById(updateSubmissionRequest.getSubmissionId());
        if(submission == null){
            throw new ApplicationException("Submission not Exists");
        }
        if(submission.getSubmissionStatus().equalsIgnoreCase("Reviewed")){
            throw new ApplicationException("Submission is reviewed");
        }
        if(!updateSubmissionRequest.getUserId().equals(submission.getUserId())){
            throw new ApplicationException("This is Not Your Submission you can't Update");
        }
        submission.setSubmissionTitle(updateSubmissionRequest.getSubmissionTitle());
        submission.setSubmissionDescription(updateSubmissionRequest.getSubmissionDescription());
        submission.setFile(updateSubmissionRequest.getFile());
        submission.setSubmissionDate(updateSubmissionRequest.getSubmissionDate());
        submissionRepository.save(submission);
        return true;
    }

    @Override
    public Boolean updateSubmissionStatus(UpdateSubmissionStatusRequest statusRequest) {
        User user = userService.getUserById(statusRequest.getAdminId());
        if(user != null){
            if(!user.getUserRole().equalsIgnoreCase("admin")){
                throw new ApplicationException("You are Not an admin you can't update");
            }
        }else {
            throw new ApplicationException("User Not Exists");
        }
        Submission submission = getSubmissionById(statusRequest.getSubmissionId());
        if(submission == null){
            throw new ApplicationException("Submission Not Exists");
        }
        Assignment assignment = assignmentService.getAssignmentById(submission.getAssignmentId());
        if(assignment.getAdminId().equals(statusRequest.getAdminId())){
           submission.setSubmissionStatus(statusRequest.getStatus());
           CompletableFuture.runAsync(() -> {
               try {
                   notificationService.submissionStatusUpdateNotification(submission);
               }catch (Exception e){
                   System.out.println(e);
               }
           });
           submissionRepository.save(submission);
           return true;
        }else {
            throw new ApplicationException("You are not able to update this Submission");
        }
    }
}
