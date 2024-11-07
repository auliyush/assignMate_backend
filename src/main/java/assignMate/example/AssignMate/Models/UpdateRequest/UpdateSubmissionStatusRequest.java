package assignMate.example.AssignMate.Models.UpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateSubmissionStatusRequest {
    private String adminId;
    private String submissionId;
    private String status;
}
