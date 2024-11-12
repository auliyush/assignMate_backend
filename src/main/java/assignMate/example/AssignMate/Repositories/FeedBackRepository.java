package assignMate.example.AssignMate.Repositories;

import assignMate.example.AssignMate.Models.FeedBack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends MongoRepository<FeedBack, String> {

    FeedBack findBySubmissionId(String submissionId);
}
