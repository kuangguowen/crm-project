package com.kgw.commom.async;

import com.kgw.commom.utils.SpringContainerUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/26 21:40
 */
public class AsyncManager {
    /**
     * 单例模式
     */
    private static ThreadPoolTaskExecutor poolTaskExecutor;

    private static AsyncManager asyncManager;

    /**
     * 私有化构造方法
     */
    private AsyncManager() {
        poolTaskExecutor = SpringContainerUtils.getBean(ThreadPoolTaskExecutor.class);
    }

    /**
     * 定义一个方法 获得AsyncFactory类
     *
     * @return
     */
    public static AsyncManager getInstance() {
        if (asyncManager == null) {
            asyncManager = new AsyncManager();
        }
        return asyncManager;
    }

    /**
     *执行方法
     */
    public  void executor(Runnable runnable) {
        poolTaskExecutor.execute(runnable);
    }

    /**
     * 停止线程池
     */
    public void shutDown(){
        poolTaskExecutor.shutdown();
    }

}
