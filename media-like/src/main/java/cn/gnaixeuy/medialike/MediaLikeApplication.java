package cn.gnaixeuy.medialike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(value = {"cn.gnaixeuy.mediacommon", "cn.gnaixeuy.medialike"})
public class MediaLikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaLikeApplication.class, args);
    }

}
