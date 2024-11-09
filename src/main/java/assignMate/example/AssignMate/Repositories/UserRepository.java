package assignMate.example.AssignMate.Repositories;

import assignMate.example.AssignMate.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByPhoneNumber(String phoneNumber);

    User findByUserId(String userId);

    List<User> findAllByUserRole(String role);
}
