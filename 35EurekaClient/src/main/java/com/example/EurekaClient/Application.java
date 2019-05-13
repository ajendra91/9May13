package com.example.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Application {

	public String url="http://AJAY/hello/";

	@Autowired
	public RestTemplate rt;

	@Autowired
	public DiscoveryClient discoveryClient;

	@Autowired
	public LoadBalancerClient loadBalancerClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/demo/{name}")
	public String hello(@PathVariable String name){

		List<ServiceInstance> instances =discoveryClient.getInstances("AJAY");
		String url2= instances.get(0).getUri().toString();
		System.out.println(url2);

		ServiceInstance instance3 =loadBalancerClient.choose("AJAY");
		String url3 = instance3.getUri().toString();
		System.out.println(url3);


		//return rt.getForObject(url+name,String.class);
		//return rt.getForObject(url2+"/hello/"+name,String.class);
		return rt.getForObject(url3+"/hello/"+name,String.class);
	}

	@Bean
	//@LoadBalanced
	public RestTemplate fun(){
		return new RestTemplate();
	}

}
