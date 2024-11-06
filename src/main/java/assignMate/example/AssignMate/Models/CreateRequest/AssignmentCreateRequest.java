package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AssignmentCreateRequest {
    private String adminId;
    private String adminName;
    private String assignmentName;
    private String assignmentDescription;
    private String assignmentFile;
    private LocalDate createDate;
    private LocalDate dueDate;
}
