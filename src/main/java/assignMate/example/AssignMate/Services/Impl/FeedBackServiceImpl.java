package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.FeedBackCreateRequest;
import assignMate.example.AssignMate.Models.FeedBack;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.FeedBackRepository;
import assignMate.example.AssignMate.Services.AssignmentService;
import assignMate.example.AssignMate.Services.FeedBackService;
import assignMate.example.AssignMate.Services.SubmissionService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private AssignmentService assignmentService;

    @Override
    public Boolean createFeedBack(FeedBackCreateRequest feedBackCreateRequest) {
        User user = userService.getUserById(feedBackCreateRequest.getAdminId());
        if(user != null){
            if(!user.getUserRole().equalsIgnoreCase("admin")){
                throw new ApplicationException("You Are not an admin");
            }
        }else {
            throw new ApplicationException("User Not Exists");
        }
        Submission submission = submissionService.getSubmissionById(
                feedBackCreateRequest.getSubmissionId());
        if(submission == null){
            throw new ApplicationException("Submission not exists");
        }
        Assignment assignment = assignmentService.getAssignmentById(submission.getAssignmentId());
        if(!assignment.getAdminId().equals(feedBackCreateRequest.getAdminId())){
            throw new ApplicationException("You can't give feedback of this submission");
        }

        if(getFeedbackBySubmissionId(feedBackCreateRequest.getSubmissionId()) != null){
            throw new ApplicationException("Feedback already submitted");
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setFeedBack(feedBackCreateRequest.getFeedBack());
        feedBack.setSubmissionId(feedBackCreateRequest.getSubmissionId());
        feedBack.setFeedBackDate(LocalDateTime.now());
        feedBackRepository.save(feedBack);
        return true;
    }

    @Override
    public FeedBack getFeedbackBySubmissionId(String submissionId) {

        return feedBackRepository.findBySubmissionId(submissionId);
    }
}
