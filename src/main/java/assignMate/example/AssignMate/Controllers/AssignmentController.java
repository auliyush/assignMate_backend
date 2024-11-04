package assignMate.example.AssignMate.Controllers;

import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.CreateRequest.AssignmentCreateRequest;
import assignMate.example.AssignMate.Services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignMate/assignment")
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/create")
    public ApiResponse<Assignment> createAssignment(@RequestBody AssignmentCreateRequest assignmentCreateRequest) {
        return new ApiResponse<>(assignmentService.createAssignment(assignmentCreateRequest), HttpStatus.OK);
    }
    @GetMapping("/get/by/Id")
    public ApiResponse<Assignment> getAssignmentById(@RequestParam String assignmentId) {
        return new ApiResponse<>(assignmentService.getAssignmentById(assignmentId), HttpStatus.OK);
    }
    @GetMapping("/get/all/by/AdminId")
    public ApiResponse<List<Assignment>> getAllAssignmentByAdminId(@RequestParam String adminId) {
        return new ApiResponse<>(assignmentService.getAllAssignmentByAdminId(adminId), HttpStatus.OK);
    }

}
