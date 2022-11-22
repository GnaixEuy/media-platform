package cn.gnaixeuy.mediaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MediaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaAuthApplication.class, args);
    }

}
