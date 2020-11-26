package top.ptcc9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Pratik_shiku
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerCustomerProfile30002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerCustomerProfile30002.class,args);
    }
}
