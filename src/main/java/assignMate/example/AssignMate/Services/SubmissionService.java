package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.SubmissionCreateRequest;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionRequest;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionStatusRequest;

import java.util.List;

public interface SubmissionService {

    Boolean createSubmission(SubmissionCreateRequest submissionCreateRequest);

    List<Submission> getAllSubmissionByAssignmentId(String assignmentId);

    List<Submission> getAllSubmissionByUserId(String userId);

    Submission getSubmissionById(String submissionId);

    Submission getSubmissionByUserIdAndAssignmentId(String userId, String assignmentId);

    Boolean updateSubmission(UpdateSubmissionRequest updateSubmissionRequest);

    Boolean updateSubmissionStatus(UpdateSubmissionStatusRequest statusRequest);
}
