package cn.gnaixeuy.mediaauth.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Configuration
public class RedisConfig {
    //RedisTemplate实例
    private static final Map<Integer, RedisTemplate<String, Object>> redisTemplateMap = new HashMap<>();
    //redis地址
    @Value("${spring.redis.host}")
    private String host;
    //redis端口号
    @Value("${spring.redis.port}")
    private int port;
    //redis密码
    @Value("${spring.redis.password}")
    private String password;
    //默认数据库
    private int defaultDb;
    //多个数据库集合
    @Value("${spring.redis.dbs}")
    private List<Integer> dbList;

    /**
     * 初始化连接池
     */
    @PostConstruct
    public void initRedisTemplate() {
        //设置默认数据库
        defaultDb = dbList.get(0);
        for (Integer db : dbList) {
            //存储多个RedisTemplate实例
            redisTemplateMap.put(db, redisTemplate(db));
        }
    }

    public LettuceConnectionFactory redisConnection(int db) {
        RedisStandaloneConfiguration server = new RedisStandaloneConfiguration();
        // 指定地址
        server.setHostName(host);
        // 指定数据库
        server.setDatabase(db);
        //指定端口
        server.setPort(port);
        //指定密码
        server.setPassword(password);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(server);
        //刷新配置
        factory.afterPropertiesSet();
        return factory;
    }

    //RedisTemplate模板
    public RedisTemplate<String, Object> redisTemplate(int db) {
        //为了开发方便，一般直接使用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //设置连接
        template.setConnectionFactory(redisConnection(db));
        //Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 指定数据库进行切换
     *
     * @param db 数据库索引
     * @return RedisTemplate<String, Object>
     */
    public RedisTemplate<String, Object> getRedisTemplateByDb(int db) {
        return redisTemplateMap.get(db);
    }

    /**
     * 使用默认数据库
     *
     * @return RedisTemplate<Object, Object>
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //默认的Key序列化器为：JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
