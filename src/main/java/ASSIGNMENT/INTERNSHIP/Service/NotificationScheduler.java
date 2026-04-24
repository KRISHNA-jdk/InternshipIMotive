package ASSIGNMENT.INTERNSHIP.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final StringRedisTemplate redisTemplate;

    @Scheduled(fixedRate = 300000)
    public void processNotifications() {

        Set<String> keys = redisTemplate.keys("user:*:pending_notifications");

        if (keys == null || keys.isEmpty()) return;

        for (String key : keys) {

            Long size = redisTemplate.opsForList().size(key);

            if (size != null && size > 0) {

                List<String> messages = redisTemplate.opsForList()
                        .range(key, 0, -1);

                if (messages == null || messages.isEmpty()) continue;

                String firstBot = messages.get(0);

                long others = size - 1;

                if (others > 0) {
                    System.out.println(
                            "Summarized Notification: " +
                                    firstBot + " and " + others +
                                    " others interacted with your post"
                    );
                } else {
                    System.out.println(
                            "Notification: " +
                                    firstBot + " interacted with your post"
                    );
                }

                redisTemplate.delete(key);
            }
        }
    }
}
