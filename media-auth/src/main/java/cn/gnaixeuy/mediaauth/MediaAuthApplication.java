package cn.gnaixeuy.mediaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScans(
        value = {
                @ComponentScan(value = {"cn.gnaixeuy.mediauser"})
        }
)
public class MediaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaAuthApplication.class, args);
    }

}
