package assignMate.example.AssignMate.Models.UpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UpdateSubmissionRequest {
    private String submissionId;
    private String userId;
    private String submissionTitle;
    private String submissionDescription;
    private String file;
}
