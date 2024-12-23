package com.example.ignite.server.interceptor;

import com.example.ignite.server.model.User;
import org.apache.ignite.cache.CacheInterceptorAdapter;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.cache.Cache;

@Component
public class UserCacheInterceptor<Long, User>  extends CacheInterceptorAdapter<Long, User> {

    @Override
    public @Nullable User onBeforePut(Cache.Entry<Long, User> entry, User newVal) {
        return super.onBeforePut(entry, newVal);
    }
}
