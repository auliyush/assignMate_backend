package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.Submission;

public interface NotificationService {

    Notification assignmentCreateNotification(Assignment assignment);

    Notification submissionCreateNotification(Submission submission, String adminId);

    Notification submissionStatusUpdateNotification(Submission submission);
}
