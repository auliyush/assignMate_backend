package assignMate.example.AssignMate.Repositories;

import assignMate.example.AssignMate.Models.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends MongoRepository<Submission, String> {

    List<Submission> findAllByAssignmentId(String assignmentId);

    Submission findByUserId(String userId);

    Submission findBySubmissionId(String submissionId);
}