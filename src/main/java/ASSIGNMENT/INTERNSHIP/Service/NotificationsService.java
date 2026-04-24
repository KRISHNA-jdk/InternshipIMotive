package ASSIGNMENT.INTERNSHIP.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class NotificationsService {

    private final StringRedisTemplate redisTemplate;
    private final GRService grService;

    public void handleNotification(Long userId, String message){

        String cooldownKey = "notif:cooldown:" + userId;
        String listKey = "user:" + userId + ":pending_notifications";

        Boolean exists = redisTemplate.hasKey(cooldownKey);

        if(!Boolean.TRUE.equals(exists)){

            System.out.println("NOTIFICATION:" + message);

            redisTemplate.opsForValue().set(cooldownKey, "1", Duration.ofMinutes(15));
        }else{

            redisTemplate.opsForList().rightPush(listKey, message);
        }
    }
}
