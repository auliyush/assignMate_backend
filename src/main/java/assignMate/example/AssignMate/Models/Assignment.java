package assignMate.example.AssignMate.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "assignments")
@Getter
@Setter
public class Assignment {
    @Id
    private String assignmentId;
    private String adminName;
    private String adminId;
    private String assignmentName;
    private String assignmentDescription;
    private String assignmentFile;
    private boolean isActive;
    private LocalDate createDate;
    private LocalDate dueDate;
    private List<String> assignedStudentsIdList;
    public Assignment(){
        this.assignedStudentsIdList = new ArrayList<>();
    }
}
