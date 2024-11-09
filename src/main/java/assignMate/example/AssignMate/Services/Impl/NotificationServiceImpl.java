package assignMate.example.AssignMate.Services.Impl;
import assignMate.example.AssignMate.Exception.ApplicationException;
import assignMate.example.AssignMate.Models.Assignment;
import assignMate.example.AssignMate.Models.Notification;
import assignMate.example.AssignMate.Models.Submission;
import assignMate.example.AssignMate.Models.User;
import assignMate.example.AssignMate.Repositories.NotificationRepository;
import assignMate.example.AssignMate.Services.NotificationService;
import assignMate.example.AssignMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserService userService;

    @Override
    public Notification assignmentCreateNotification(Assignment assignment) {
        Notification notification = new Notification();
        notification.setNotificationTitle("You have received new assignment");
        notification.setNotificationDescription("Assignment's submissions starts from Now," +
                " due date Of submissions "+ assignment.getDueDate()+"\n"+"Hurry! Complete Assignment\n By - "+assignment.getAdminName());
        notification.setNotificationDate(LocalDate.now());
        notification.setNotificationTime(LocalTime.now());
        notification.setHasSeen(false);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification submissionCreateNotification(Submission submission, String adminId) {
        Notification notification =  new Notification();
        notification.setNotificationTitle("New Submission submitted");
        notification.setNotificationDescription(submission.getUserName()+" Submitted assignment's submission");
        notification.setNotificationDate(LocalDate.now());
        notification.setNotificationTime(LocalTime.now());
        notification.setHasSeen(false);
        notificationRepository.save(notification);
        for (User user : userService.getAllUser()){
            if(user.getUserId().equals(adminId)){
                user.getNotifications().add(notification);
                userService.saveUpdates(user);
                return notificationRepository.save(notification);
            }
        }
        return notificationRepository.save(notification);
    }

    @Override
    public Notification submissionStatusUpdateNotification(Submission submission) {
        Notification notification = new Notification();
        notification.setNotificationTitle("Your Submission has Update");
        notification.setNotificationDescription("Your Submission was "+submission.getSubmissionStatus());
        notification.setNotificationDate(LocalDate.now());
        notification.setNotificationTime(LocalTime.now());
        notificationRepository.save(notification);
        for (User user : userService.getAllUser()){
            if(user.getUserId().equals(submission.getUserId())){
                user.getNotifications().add(notification);
                userService.saveUpdates(user);
                return notificationRepository.save(notification);
            }
        }
        return notification;
    }

    @Override
    public Boolean deleteNotification(String userId, String notificationId) {
        Notification notification = getNotificationBYId(notificationId);
        if(notification == null){
            throw new ApplicationException("Invalid Notification");
        }
        for(User user : userService.getAllUser()){
            if(user.getUserId().equals(userId)){
                List<Notification> notificationList = user.getNotifications();
                notificationList.remove(notification);
                user.setNotifications(notificationList);
                userService.saveUpdates(user);
                return true;
            }
        }
        throw new ApplicationException("User Id not Exists");
    }

    @Override
    public Notification getNotificationBYId(String notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }

    @Override
    public Boolean updateSeenStatus(String notificationId, String userId) {
        Notification notification = getNotificationBYId(notificationId);
        if(notification == null){
            throw new ApplicationException("Notification not available");
        }
        User user = userService.getUserById(userId);
        if(user == null){
            throw new ApplicationException("User not available");
        }
        for(Notification notification1 : user.getNotifications()){
            if(notification1.getNotificationId().equals(notificationId)){
                notification1.setHasSeen(true);
                userService.saveUpdates(user);
                return true;
            }
        }
        return false;
    }
}
