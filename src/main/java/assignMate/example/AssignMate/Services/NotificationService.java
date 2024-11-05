package assignMate.example.AssignMate.Services;

import assignMate.example.AssignMate.Models.CreateRequest.NotificationCreateRequest;
import assignMate.example.AssignMate.Models.Notification;

public interface NotificationService {
    Notification createNotification(NotificationCreateRequest notificationCreateRequest);
}
