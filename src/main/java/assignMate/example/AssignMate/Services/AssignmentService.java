package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.AssignmentCreateRequest;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface AssignmentService {

    Assignment createAssignment(AssignmentCreateRequest assignmentCreateRequest);

    Assignment getAssignmentById(String assignmentId);

    List<Assignment> getAllAssignmentByAdminId(String adminId);

    List<Assignment> getAllAssignments();
}
