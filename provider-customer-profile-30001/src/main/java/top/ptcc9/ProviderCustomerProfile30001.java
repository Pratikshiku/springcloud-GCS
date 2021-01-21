package top.ptcc9;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Pratik_shiku
 */
@SpringBootApplication
@EnableTransactionManagement
public class ProviderCustomerProfile30001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderCustomerProfile30001.class,args);
    }
}
