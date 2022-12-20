package cn.gnaixeuy.mediafeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(value = {
        "cn.gnaixeuy.mediacommon",
        "cn.gnaixeuy.mediafile",
        "cn.gnaixeuy.mediafeed"})
@ComponentScans(value = {
        @ComponentScan(value = "cn.gnaixeuy.mediafile"),
        @ComponentScan(value = "cn.gnaixeuy.mediacommon")
})
public class MediaFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaFeedApplication.class, args);
    }

}
