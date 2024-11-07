package assignMate.example.AssignMate.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "submissions")
@Getter
@Setter
@NoArgsConstructor
public class Submission {
    @Id
    private String submissionId;
    private String userId;
    private String userName;
    private String assignmentId;
    private String submissionTitle;
    private String submissionDescription;
    private String file;
    private LocalDate submissionDate;
    private String submissionStatus;
}
