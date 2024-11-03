package assignMate.example.AssignMate.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedBack")
@Getter
@Setter
@NoArgsConstructor
public class FeedBack {
    @Id
    private String feedBackId;
    private String assignmentId;
    private String submissionId;
    private String feedBack;
    private String feedBackDate;
}
