package com.yonyou.base.limit.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yonyou.iuap.cache.CacheManager;

/**
 * Created by 大佬 2019年12月19日 19:32:43
 * redis锁实现限流调用操作
 */
@Component
public class RedisLock {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CacheManager cacheManager;

    /**
     * 加锁
     * @param key   锁id
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (cacheManager.getJedisTemplate().setnx(key, value)) {
            return true;
        }
        //避免死锁，且只让一个线程拿到锁
        String currentValue = cacheManager.getJedisTemplate().get(key);
        //如果锁过期了
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValues = cacheManager.getJedisTemplate().getSet(key, value);
            /*
               只会让一个线程拿到锁
               如果旧的value和currentValue相等，只会有一个线程达成条件，因为第二个线程拿到的oldValue已经和currentValue不一样了
             */
            if (!StringUtils.isEmpty(oldValues) && oldValues.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = cacheManager.getJedisTemplate().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                cacheManager.getJedisTemplate().del(key);
            }
        } catch (Exception e) {
            logger.error("『redis分布式锁』解锁异常，{}", e);
        }
    }


    public static void main(String[] a)throws RuntimeException {
        RedisLock redisLock=new RedisLock();//实际用的时候要注入一下
         String id="testid";//锁标记，可以按接口来，也可以按某一条记录来
        //加锁
        long time = System.currentTimeMillis() + 1000*10;  //超时时间：10秒，最好设为常量

        for(int i=1;i<10000;i++) {
            boolean isLock = redisLock.lock(id, String.valueOf(time));
            if (!isLock) {
                throw new RuntimeException("人太多了，换个姿势再试试~");
            }
        }
        //解锁
        redisLock.unlock(id,String.valueOf(time));

    }
}