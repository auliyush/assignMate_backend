package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.AssignmentCreateRequest;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.AssignmentRepository;
import assignMate.example.AssignMate.Services.AssignmentService;
import assignMate.example.AssignMate.Services.NotificationService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private NotificationService notificationService;

    @Override
    public Assignment createAssignment(AssignmentCreateRequest assignmentCreateRequest) {
        User user = userService.getUserById(assignmentCreateRequest.getAdminId());
        if(user == null){
            throw new ApplicationException("User Not exist");
        } else if (!user.getUserRole().equalsIgnoreCase("admin")) {
            throw new ApplicationException("You are Not an Admin");
        }else {
            Assignment assignment = getAssignment(assignmentCreateRequest);
            assignmentRepository.save(assignment);
            CompletableFuture.runAsync(() -> {
                try {
                   notificationService.assignmentCreateNotification(assignment);
                }catch (Exception e){
                    System.out.println(e);
                }
            });
            return assignmentRepository.save(assignment);
        }
    }

    // extracted method for set assignment data from createRequest for improve code readability
    private static Assignment getAssignment(AssignmentCreateRequest assignmentCreateRequest) {
        Assignment assignment = new Assignment();
        assignment.setAdminId(assignmentCreateRequest.getAdminId());
        assignment.setAssignmentName(assignmentCreateRequest.getAssignmentName());
        assignment.setAssignmentDescription(assignmentCreateRequest.getAssignmentDescription());
        assignment.setAssignmentFile(assignmentCreateRequest.getAssignmentFile());
        assignment.setAdminName(assignmentCreateRequest.getAdminName());
        assignment.setActive(true);
        assignment.setCreateDate(assignmentCreateRequest.getCreateDate());
        assignment.setDueDate(assignmentCreateRequest.getDueDate());
        return assignment;
    }

    @Override
    public Assignment getAssignmentById(String assignmentId) {
        return assignmentRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<Assignment> getAllAssignmentByAdminId(String adminId) {
        return assignmentRepository.findAllByAdminId(adminId);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAllByIsActive(true);
    }
}
