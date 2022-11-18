package com.myproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author lkxl
 */
@Component
public class RedisUtil {
    Jedis jedis=new Jedis("172.30.163.98",6379);
}
