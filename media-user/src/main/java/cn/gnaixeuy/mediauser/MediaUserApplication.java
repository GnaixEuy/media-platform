package cn.gnaixeuy.mediauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MediaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaUserApplication.class, args);
    }

}
