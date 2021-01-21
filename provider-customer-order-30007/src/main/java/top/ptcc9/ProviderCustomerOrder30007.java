package top.ptcc9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2021-01-07 17:30
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class ProviderCustomerOrder30007 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderCustomerOrder30007.class,args);
    }
}
