package assignMate.example.AssignMate.Repositories;

import assignMate.example.AssignMate.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    Notification findByNotificationId(String notificationId);
}
