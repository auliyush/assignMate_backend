package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.AssignmentCreateRequest;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.UpdateRequest.UpdateAssignmentRequest;
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
                    for (String usersId : assignmentCreateRequest.getStudentsId()){
                        User user1 = userService.getUserById(usersId);
                        if(user1 != null){
                            user1.getAssignmentList().add(assignment);
                            Notification notification = notificationService.assignmentCreateNotification(assignment);
                            user1.getNotifications().add(notification);
                            userService.saveUpdates(user1);
                            assignment.getAssignedStudentsIdList().add(user1.getUserId());
                            assignmentRepository.save(assignment);
                        }
                    }
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
    public Boolean updateAssignment(UpdateAssignmentRequest updateAssignmentRequest) {
        User user = userService.getUserById(updateAssignmentRequest.getAdminId());
        if(user == null){
            throw new ApplicationException("User Not exist");
        } else if (!user.getUserRole().equalsIgnoreCase("admin")) {
            throw new ApplicationException("You are Not an Admin");
        }else {
            Assignment assignment = getAssignmentById(updateAssignmentRequest.getAssignmentId());
            if(assignment != null){
                if(!assignment.getAdminId().equals(updateAssignmentRequest.getAdminId())){
                    throw new ApplicationException("You can't update this assignment");
                }
            }else {
                throw new ApplicationException("Assignment not available");
            }
            assignment.setAssignmentName(updateAssignmentRequest.getAssignmentName());
            assignment.setAssignmentDescription(updateAssignmentRequest.getAssignmentDescription());
            assignment.setAssignmentFile(updateAssignmentRequest.getAssignmentFile());
            assignment.setDueDate(updateAssignmentRequest.getDueDate());
                    for (String usersId : updateAssignmentRequest.getStudentsId()){
                        User user1 = userService.getUserById(usersId);
                        if(user1 != null){
                            user1.getAssignmentList().add(assignment);
                            Notification notification = notificationService.assignmentCreateNotification(assignment);
                            user1.getNotifications().add(notification);
                            userService.saveUpdates(user1);
                            assignment.getAssignedStudentsIdList().add(user1.getUserId());
                        }
                    }
            assignmentRepository.save(assignment);
            return true;
        }
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
