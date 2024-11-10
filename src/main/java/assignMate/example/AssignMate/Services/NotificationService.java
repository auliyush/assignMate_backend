package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.Submission;

import java.util.List;

public interface NotificationService {

    Notification assignmentCreateNotification(Assignment assignment);

    Notification submissionCreateNotification(Submission submission, String adminId);

    Notification submissionStatusUpdateNotification(Submission submission);

    Boolean deleteNotification(String userId, String notificationId);

    Notification getNotificationBYId(String notificationId);

    Boolean updateSeenStatus(String notificationId, String userId);

}
