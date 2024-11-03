package assignMate.example.AssignMate.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String userId;
    private String userName;
    private String phoneNumber;
    private String password;
    private String userRole;
    private List<Submission> submissionList;
    private List<Assignment> assignmentList;

    public User(){
            this.assignmentList = new ArrayList<>();
            this.submissionList = new ArrayList<>();
    }
}
