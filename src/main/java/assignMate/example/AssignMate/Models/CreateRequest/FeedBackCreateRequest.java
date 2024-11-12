package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FeedBackCreateRequest {
    private String adminId;
    private String submissionId;
    private String feedBack;
    private LocalDateTime feedBackDate;
}
