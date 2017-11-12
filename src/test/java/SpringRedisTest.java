import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Jeffrey on 2017-11-11.
 */
public class SpringRedisTest {
  private static ApplicationContext applicationContext;

  static{
    applicationContext = new ClassPathXmlApplicationContext("config/springcontext.xml");
  }

  @Test
  public void testRedisConnection(){
    RedisTemplate redisTemplate = (RedisTemplate)applicationContext.getBean("redisTemplate");
    redisTemplate.renameIfAbsent("abc", "bbb");//如果key=abc存在，则将key修改为bbb
    System.out.println(redisTemplate);
  }
}
