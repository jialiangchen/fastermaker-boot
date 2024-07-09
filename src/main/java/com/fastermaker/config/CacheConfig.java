package com.fastermaker.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class CacheConfig {
    private  ConcurrentHashMap<String, Object> cacheMap;
    private  Map<String, Long> expiryMap=new HashMap<String,Long>();
    private  ScheduledExecutorService executorService;
    public CacheConfig() {
        cacheMap = new ConcurrentHashMap<>();
        expiryMap = new ConcurrentHashMap<>();
        executorService = new ScheduledThreadPoolExecutor(1);
    }
    public void set(String key, Object value, long expiryTimeInMillis) {
        cacheMap.put(key, value);
        expiryMap.put(key, System.currentTimeMillis() + expiryTimeInMillis*1000);
        scheduleExpiryCheck(key, expiryTimeInMillis);
    }

    public void set(String key,Object obj){
        cacheMap.put(key,obj);
    }
    public void remove(String key) {
        cacheMap.remove(key);
        expiryMap.remove(key);
    }
    public void removeByPrefix(String prefix){
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            String key = entry.getKey();
            if(key.startsWith(prefix)){
                remove(key);
            }
        }
    }
    public Object get(String key){
        if (isExpired(key)) {
            remove(key);
            return null;
        }
        return cacheMap.get(key);
    }
    public boolean containsKey(String key){
        if (isExpired(key)) {
            remove(key);
            return false;
        }
        return cacheMap.containsKey(key);
    }
    public List<Object> multiGet(String prefix,List<String> keyList){
        List<Object> result=new ArrayList<>();
        for(String key:keyList){
            String keyString=key;
            if(StringUtils.isNotEmpty(prefix)){
                keyString=prefix+keyString;
            }
            if(cacheMap.containsKey(keyString)){
                result.add(cacheMap.get(keyString));
            }
        }
        return result;
    }
    private void scheduleExpiryCheck(String key, long expiryTimeInMillis) {
        executorService.schedule(() -> {
            if (isExpired(key)) {
                remove(key);
            }
        }, expiryTimeInMillis, TimeUnit.SECONDS);
    }

    private boolean isExpired(String key) {
        Long expiryTime = expiryMap.get(key);
        return expiryTime != null && expiryTime < System.currentTimeMillis();
    }
}
