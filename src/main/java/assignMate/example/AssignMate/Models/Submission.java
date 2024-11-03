package assignMate.example.AssignMate.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "submissions")
@Getter
@Setter
@NoArgsConstructor
public class Submission {
    @Id
    private String submissionId;
    private String userId;
    private String assignmentId;
    private String submissionTitle;
    private String submissionDescription;
    private String file;
    private String submissionDate;
    private String submissionStatus;
}
