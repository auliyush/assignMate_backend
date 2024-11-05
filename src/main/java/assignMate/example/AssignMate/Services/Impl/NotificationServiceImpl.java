package assignMate.example.AssignMate.Services.Impl;

import assignMate.example.AssignMate.Models.CreateRequest.NotificationCreateRequest;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Repositories.NotificationRepository;
import assignMate.example.AssignMate.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(NotificationCreateRequest notificationCreateRequest) {
        Notification notification = new Notification();
        notification.setNotificationTitle(notificationCreateRequest.getNotificationTitle());
        notification.setNotificationDescription(notificationCreateRequest.getNotificationDescription());
        notification.setNotificationDate(notificationCreateRequest.getNotificationDate());
        return notificationRepository.save(notification);
    }
}
