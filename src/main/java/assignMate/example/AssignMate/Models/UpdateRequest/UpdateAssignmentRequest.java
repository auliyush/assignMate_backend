package assignMate.example.AssignMate.Models.UpdateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAssignmentRequest {
    private String adminId;
    private String assignmentId;
    private String assignmentName;
    private String assignmentDescription;
    private String assignmentFile;
    private LocalDate dueDate;
    private List<String> studentsId;
}
