package cn.gnaixeuy.mediauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan(value = {"cn.gnaixeuy.mediacommon", "cn.gnaixeuy.mediauser"})
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MediaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaUserApplication.class, args);
    }

}
