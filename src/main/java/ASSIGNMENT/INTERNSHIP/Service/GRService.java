package ASSIGNMENT.INTERNSHIP.Service;


import ASSIGNMENT.INTERNSHIP.Exception.BadRequestException;
import ASSIGNMENT.INTERNSHIP.Exception.TooManyRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GRService {

    private final StringRedisTemplate stringRedisTemplate;

    public void checkBotLimit(Long postId){

        String key = "post" + postId + "bot_count";

        Long count = stringRedisTemplate.opsForValue().increment(key);

        if(count != null && count > 100){
            throw new TooManyRequestException("Bot limit exceeded (max 100)");
        }
    }

    public void checkCool(Long botId, Long userId){

        String key = "cooldown bot_" + botId + "human_"+ userId;

        Boolean exists = stringRedisTemplate.hasKey(key);

        if(Boolean.TRUE.equals(exists)){
            throw new BadRequestException("COOLDOWN ACTIVE");
        }

        stringRedisTemplate.opsForValue().set(key, "1", java.time.Duration.ofMinutes(10));
    }
}
