//package top.ptcc9.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author 何龙灿
// */
//public final class RedisUtil {
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//
//    /**
//     * 设置某  key  的缓存失效时间
//     * @param key
//     * @param time
//     * @return
//     */
//    public boolean expire(String key, long time){
//        try{
//            if(time>0){
//                redisTemplate.expire(key,time, TimeUnit.MINUTES);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * 获取key过期时间
//     *     key不可为空
//     *     返回  0  代表永久有效
//     * @param key
//     * @return
//     */
//    public long getExpire(String key){
//        return redisTemplate.getExpire(key);
//    }
//
//
//
//    /**
//     * 判断 key 是否存在
//     * @param key
//     * @return
//     */
//    public boolean hasKey(String key){
//        try{
//            return redisTemplate.hasKey(key);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 删除某 key
//     *     可以传入一个或多个
//     * @param key
//     */
//    @SuppressWarnings("unchecked")
//    public void del(String... key){
//        if(key!=null&&key.length>0){
//            if(key.length==1){
//                redisTemplate.delete(key[0]);
//            }else{
//                redisTemplate.delete(CollectionUtils.arrayToList(key));
//            }
//
//        }
//    }
//
//    //===================================  String  ====================================
//    /**
//     * 获取String key
//     * @param key
//     * @return
//     */
//    public Object get(String key){
//        return key==null?null:redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 存入String key   不设置过期时间
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(String key, Object value){
//        try{
//            redisTemplate.opsForValue().set(key,value);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//    /**
//     * 存入String key   并设置过期时间
//     * @param key
//     * @param value
//     * @param time
//     * @return
//     */
//    public boolean set(String key, Object value, long time){
//        try{
//            if(time>0){
//                redisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
//            }else {
//                set(key,value);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * 设置递增    步长可自定义
//     * @param key
//     * @param delta
//     * @return
//     */
//    public long incr(String key, long delta){
//        if(delta<0){
//            throw new RuntimeException("递增必须大于1");
//        }else{
//            return redisTemplate.opsForValue().increment(key,delta);
//        }
//    }
//
//    /**
//     * 设置递减    步长可自定义
//     * @param key
//     * @param delta
//     * @return
//     */
//    public long decr(String key, long delta){
//        if(delta<0){
//            throw new RuntimeException("递减必须大于1");
//        }else{
//            return redisTemplate.opsForValue().decrement(key,delta);
//        }
//    }
//
//
//    //=================================== List =====================================
//
//    /**
//     * 获取list缓存的内容
//     * @param key 键
//     * @param start 开始
//     * @param end 结束 0 到 -1代表所有值
//     * @return
//     */
//    public List<Object> lGet(String key, long start, long end) {
//        try {
//            return redisTemplate.opsForList().range(key, start, end);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//    /**
//     * 获取list缓存的长度
//     * @param key 键
//     * @return
//     */
//    public long lGetListSize(String key) {
//        try {
//            return redisTemplate.opsForList().size(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//
//
//
//    /**
//     * 通过索引 获取list中的值
//     * @param key 键
//     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//     * @return
//     */
//    public Object lGetIndex(String key, long index) {
//        try {
//            return redisTemplate.opsForList().index(key, index);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if(time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//
//    /**
//     * 根据索引修改list中的某条数据
//     * @param key 键
//     * @param index 索引
//     * @param value 值
//     * @return
//     */
//    public boolean lUpdateIndex(String key, long index, Object value) {
//        try {
//            redisTemplate.opsForList().set(key, index, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // ================================Map=================================
//
//    /**
//     * HashGet
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     */
//    public Object hget(String key, String item) {
//        return redisTemplate.opsForHash().get(key, item);
//    }
//
//    /**
//     * 获取hashKey对应的所有键值
//     * @param key 键
//     * @return 对应的多个键值
//     */
//    public Map<Object, Object> hmget(String key) {
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * HashSet
//     * @param key 键
//     * @param map 对应多个键值
//     */
//    public boolean hmset(String key, Map<String, Object> map) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * HashSet 并设置时间
//     * @param key  键
//     * @param map  对应多个键值
//     * @param time 时间(秒)
//     * @return true成功 false失败
//     */
//    public boolean hmset(String key, Map<String, Object> map, long time) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @return true 成功 false失败
//     */
//    public boolean hset(String key, String item, Object value) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
//     * @return true 成功 false失败
//     */
//    public boolean hset(String key, String item, Object value, long time) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * 删除hash表中的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 可以使多个 不能为null
//     */
//    public void hdel(String key, Object... item) {
//        redisTemplate.opsForHash().delete(key, item);
//    }
//
//
//    /**
//     * 判断hash表中是否有该项的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     * @return true 存在 false不存在
//     */
//    public boolean hHasKey(String key, String item) {
//        return redisTemplate.opsForHash().hasKey(key, item);
//    }
//
//    /**
//     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要增加几(大于0)
//     */
//    public double hincr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, by);
//    }
//
//
//    /**
//     * hash递减
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要减少记(小于0)
//     */
//    public double hdecr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, -by);
//    }
//
//
//
//}
//
