package cn.gnaixeuy.mediafile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(value = {"cn.gnaixeuy.mediacommon", "cn.gnaixeuy.mediafile"})
public class MediaFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaFileApplication.class, args);
    }

}
