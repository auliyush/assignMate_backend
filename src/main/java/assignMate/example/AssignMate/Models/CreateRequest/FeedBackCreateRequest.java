package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedBackCreateRequest {
    private String adminId;
    private String submissionId;
    private String feedBack;
    private String feedBackDate;
}
