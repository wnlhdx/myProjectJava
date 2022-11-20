package com.myproject.springboot.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

/**
 * @author lkxl
 */
@SpringBootTest
class RedisUtilTest {
    private static final Logger log= LoggerFactory.getLogger(RedisUtilTest.class);
    @Autowired
    private RedisUtil RedisUtil;

    @Test
    void testRedis(){
        Jedis jedis=RedisUtil.jedis;
        jedis.set("test","test1");
        log.info(jedis.getConnection().toString());
        log.info(jedis.get("testx"));
        jedis.close();
    }
}