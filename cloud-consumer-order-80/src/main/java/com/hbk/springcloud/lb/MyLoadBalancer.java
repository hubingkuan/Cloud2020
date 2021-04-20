package com.hbk.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 胡冰宽
 * @date 2021/3/15
 * 简化版 负载均衡
 **/
@Component
public class MyLoadBalancer implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current == Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next));

        System.out.println("***访问次数next:" + next);
        return next;
    }


    //负载均衡算法：rest接口第几次请求数%服务器集群总数量=实际调用服务器位置下标，每次服务都会重启rest接口计数从1记起
    @Override
    public ServiceInstance instances(List<ServiceInstance> list) {
        // 服务器集群数
        int size = list.size();
        // 访问次数
        int visits = getAndIncrement();
        // 得到服务器的下标
        int index = visits % size;
        return list.get(index);
    }
}
