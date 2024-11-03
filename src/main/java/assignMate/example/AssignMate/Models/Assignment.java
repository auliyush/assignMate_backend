package assignMate.example.AssignMate.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "assignments")
@Getter
@Setter
public class Assignment {
    @Id
    private String assignmentId;
    private String adminId;
    private String assignmentName;
    private String assignmentDescription;
    private String createDate; // todo change String into Date
    private String dueDate;
    private List<Submission> submissionList;
    public Assignment(){
        this.submissionList = new ArrayList<>();
    }
}
