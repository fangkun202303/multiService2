package com.moduls.dubboprovider.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 使用redis作为缓存的配置，默认的是
 * 在类org.springframework.cache.support.AbstractCacheManager中有配置
 * ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
 */
@Configuration
public class MyRedisConfig extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(MyRedisConfig.class);

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        cacheConfig.entryTtl(Duration.ofSeconds(60))
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(cacheConfig)
                .transactionAware()
                .build();
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        RedisSerializer keySerializer = new StringRedisSerializer();
        // 设置key序列化类，否则key前面会多了一些乱码
         template.setKeySerializer(keySerializer);
         setValueSerializer(template);
        // 设置value序列化
         template.afterPropertiesSet();
         template.setEnableTransactionSupport(true);

        return template;
    }

    /**
     * value的序列化
     * @param template
     */
    private void setValueSerializer(StringRedisTemplate template) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }

    /**
     * 缓存的key是 包名+方法名+参数列表
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("::" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            logger.info("========》》》》这个"+target.getClass().getName()+"类的这个方法："+method+"缓存key是："+sb.toString());
            return sb.toString();
        };
    }
}
