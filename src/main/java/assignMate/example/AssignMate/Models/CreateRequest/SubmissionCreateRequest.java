package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SubmissionCreateRequest {
    private String userId;
    private String assignmentId;
    private String submissionTitle;
    private String submissionDescription;
    private String file;
    private LocalDate submissionDate;
}
