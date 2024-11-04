package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.FeedBackCreateRequest;
import assignMate.example.AssignMate.Models.FeedBack;

import java.util.List;

public interface FeedBackService {

    Boolean createFeedBack(FeedBackCreateRequest feedBackCreateRequest);

    List<FeedBack> getAllFeedbackBySubmissionId(String submissionId);
}
