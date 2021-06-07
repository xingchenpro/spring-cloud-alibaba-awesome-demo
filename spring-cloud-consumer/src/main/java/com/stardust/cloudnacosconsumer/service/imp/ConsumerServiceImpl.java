package com.stardust.cloudnacosconsumer.service.imp;

import com.stardust.cloudnacosconsumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author :hly
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2021/6/7
 * @desc :
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;


    public String consumer() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("producer-service");
        String url = serviceInstance.getUri() + "/index";
        return restTemplate.getForObject(url, String.class);

    }
}
