package assignMate.example.AssignMate.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Document(collection = "notification")
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    private String notificationId;
    private String notificationTitle;
    private String notificationDescription;
    private LocalDate notificationDate;
    private LocalTime notificationTime;
    private boolean hasSeen;
}
