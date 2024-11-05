package assignMate.example.AssignMate.Models.CreateRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NotificationCreateRequest {
    private String notificationTitle;
    private String notificationDescription;
    private Date notificationDate;
}
