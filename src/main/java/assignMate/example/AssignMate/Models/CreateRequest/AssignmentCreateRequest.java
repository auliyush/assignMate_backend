package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignmentCreateRequest {
    private String adminId;
    private String assignmentName;
    private String assignmentDescription;
    private String createDate;
    private String dueDate;
}
