package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.SubmissionCreateRequest;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionRequest;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.SubmissionRepository;
import assignMate.example.AssignMate.Services.AssignmentService;
import assignMate.example.AssignMate.Services.SubmissionService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Boolean createSubmission(SubmissionCreateRequest submissionCreateRequest) {
        Assignment assignment = assignmentService.getAssignmentById(submissionCreateRequest.getAssignmentId());
        if(assignment == null){
            throw new ApplicationException("Assignment not Exist");
        }
        User user = userService.getUserById(submissionCreateRequest.getUserId());
        if(user == null){
            throw new ApplicationException("User not Exist");
        }
        Submission submission = new Submission();
        submission.setUserId(submissionCreateRequest.getUserId());
        submission.setAssignmentId(submissionCreateRequest.getAssignmentId());
        submission.setSubmissionTitle(submissionCreateRequest.getSubmissionTitle());
        submission.setSubmissionDescription(submissionCreateRequest.getSubmissionDescription());
        submission.setSubmissionDate(submissionCreateRequest.getSubmissionDate());
        submission.setFile(submissionCreateRequest.getFile());
        submission.setSubmissionStatus("Pending");
        submissionRepository.save(submission);
        return true;
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
        //Assignment assignment = assignmentService.getAssignmentById(submission.getAssignmentId());
        // todo compare the date currently i use string instead of Date dataType
        submission.setSubmissionTitle(updateSubmissionRequest.getSubmissionTitle());
        submission.setSubmissionDescription(updateSubmissionRequest.getSubmissionDescription());
        submission.setFile(updateSubmissionRequest.getFile());
        submission.setSubmissionDate(updateSubmissionRequest.getSubmissionDate());
        submissionRepository.save(submission);
        return true;
    }

    @Override
    public Boolean updateSubmissionStatus(String adminId, String submissionId) {
        User user = userService.getUserById(adminId);
        if(user != null){
            if(!user.getUserRole().equalsIgnoreCase("admin")){
                throw new ApplicationException("You are Not an admin you can't update");
            }
        }else {
            throw new ApplicationException("User Not Exists");
        }
        Submission submission = getSubmissionById(submissionId);
        if(submission == null){
            throw new ApplicationException("Submission Not Exists");
        }
        Assignment assignment = assignmentService.getAssignmentById(submission.getAssignmentId());
        if(assignment.getAdminId().equals(adminId)){
           submission.setSubmissionStatus("Reviewed");
           submissionRepository.save(submission);
           return true;
        }else {
            throw new ApplicationException("You are not able to update this Submission");
        }
    }
}
