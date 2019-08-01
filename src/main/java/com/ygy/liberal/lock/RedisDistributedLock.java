//package com.ygy.liberal.lock;
//
//import com.google.common.base.Preconditions;
//import com.google.common.collect.Lists;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author guoyao
// * @date 2019-07-31
// */
//@Component(RedisDistributedLock.REDIS_DISTRIBUTED_LOCK)
//public class RedisDistributedLock  {
//
//    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
//
//    public static final String REDIS_DISTRIBUTED_LOCK = "redisDistributedLock";
//
//    /**
//     * 默认超时时间
//     */
//    private static final long DEFAULT_EXPIRE_TIME = 1000 * 20;
//
//    /**
//     * 最大超时时间
//     */
//    private static final long MAX_EXPIRE_TIME = 1000 * 60 * 60 * 24;
//
//    /**
//     * 默认重试次数
//     */
//    private static final int DEFAULT_RETRY_TIME = 1;
//
//    /**
//     * 每次重试睡眠时间
//     */
//    private static final long DEFAULT_RETRY_SLEEP_TIME = 50;
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
//
//    private static final String LUA_UNLOCK = new StringBuilder()
//            .append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ")
//            .append("then ")
//            .append("    return redis.call(\"del\",KEYS[1]) ")
//            .append("else ")
//            .append("    return 0 ")
//            .append("end ").toString();
//
//    @Override
//    public boolean lock(LockEnum lockEnum, String key, String requestId) {
//        return lock(lockEnum, key, requestId, DEFAULT_EXPIRE_TIME);
//    }
//
//    @Override
//    public boolean lock(LockEnum lockEnum, String key, String requestId, long expireTime) {
//        return lock(lockEnum, key, requestId, expireTime, DEFAULT_RETRY_TIME, DEFAULT_RETRY_SLEEP_TIME);
//    }
//
//    @Override
//    public boolean lock(LockEnum lockEnum, String key, String requestId, long expireTime, int retryTimes, long sleepTimes) {
//        boolean acquireResult = false;
//        if (retryTimes < 0) {
//            retryTimes = DEFAULT_RETRY_TIME;
//        }
//        if (sleepTimes <= 0) {
//            sleepTimes = 50;
//        }
//        while (!acquireResult || retryTimes-- >= 0) {
//            try {
//                acquireResult = acquireLock(lockEnum, key, requestId, expireTime);
//            } catch (Exception e) {
//                logger.info(LogHelper.buildLog(requestId, "调用redis加锁失败key:" + getLockKey(lockEnum, key)), e);
//            }
//            if (!acquireResult) {
//                try {
//                    Thread.sleep(sleepTimes);
//                } catch (Exception e) {}
//            }
//        }
//        return acquireResult;
//    }
//
//    private boolean acquireLock(LockEnum lockEnum, String key, String requestId, long expireTime) {
//        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "redis锁lock,key不能为空:" + key);
//        Preconditions.checkArgument(StringUtils.isNotEmpty(requestId), "redis锁lock,requestId不能为空:" + requestId);
//        String lockKey = getLockKey(lockEnum, key);
//        long positiveExpireTime = getExpireTime(expireTime);
//        String result = redisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//                return commands.set(lockKey, requestId, "NX", "PX", positiveExpireTime);
//            }
//        });
//        return StringUtils.isNotEmpty(result);
//    }
//
//    /**
//     * 获取过期时间
//     *
//     * @param expireTime
//     * @return
//     */
//    private long getExpireTime(long expireTime) {
//        if (expireTime <= 0) {
//            expireTime = DEFAULT_EXPIRE_TIME;
//        }
//        if (expireTime > MAX_EXPIRE_TIME) {
//            expireTime = MAX_EXPIRE_TIME;
//        }
//        return expireTime;
//    }
//
//    private String getLockKey(LockEnum lockEnum, String key) {
//        if (lockEnum == null) {
//            lockEnum = LockEnum.DEFAULT_LOCK;
//        }
//        return CacheKeyEnum.LOCK.getCode() + lockEnum.getCode() + key;
//    }
//
//    @Override
//    public boolean unLock(LockEnum lockEnum, String key, String requestId) {
//        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "redis锁unlock,key不能为空:" + key);
//        Preconditions.checkArgument(StringUtils.isNotEmpty(requestId), "redis锁unlock,requestId不能为空:" + requestId);
//        List<String> keys = Lists.newArrayList();
//        keys.add(getLockKey(lockEnum, key));
//        List<String> args = Lists.newArrayList();
//        args.add(requestId);
//        try {
//            Long result = redisTemplate.execute(new RedisCallback<Long>() {
//                @Override
//                public Long doInRedis(RedisConnection connection) throws DataAccessException {
//                    Object nativeConnection = connection.getNativeConnection();
//                    if (nativeConnection instanceof Jedis) {
//                        return (Long) ((Jedis) nativeConnection).eval(LUA_UNLOCK, keys, args);
//                    }
//                    return 0L;
//                }
//            });
//            return result != null && result > 0;
//        } catch (Exception e) {
//            logger.info(LogHelper.buildLog(requestId, "调用redis解锁失败key:" + getLockKey(lockEnum, key)), e);
//        }
//        return false;
//    }
//
//}
