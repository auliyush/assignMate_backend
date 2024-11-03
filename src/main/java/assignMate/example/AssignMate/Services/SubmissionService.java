package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.SubmissionCreateRequest;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateSubmissionRequest;

import java.util.List;

public interface SubmissionService {

    Boolean createSubmission(SubmissionCreateRequest submissionCreateRequest);

    List<Submission> getAllSubmissionByAssignmentId(String assignmentId);

    Submission getSubmissionByUserId(String userId);

    Submission getSubmissionById(String submissionId);

    Boolean updateSubmission(UpdateSubmissionRequest updateSubmissionRequest);

    Boolean updateSubmissionStatus(String adminId, String submissionId);
}
