package springboot.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

}
