package assignMate.example.AssignMate.Repositories;

import assignMate.example.AssignMate.Models.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {

    Assignment findByAssignmentId(String assignmentId);

    List<Assignment> findAllByAdminId(String adminId);

    List<Assignment> findAllByActiveStatus(boolean status);
}
