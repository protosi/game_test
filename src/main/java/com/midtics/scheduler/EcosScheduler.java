package com.midtics.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.midtics.component.EcosStatJobComponent;
import com.midtics.util.BOKUploader;

@Component
public class EcosScheduler  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	BOKUploader bokUploader;
	
	@Autowired
	EcosStatJobComponent statJob;
	
	@Scheduled(cron = "0 0 0 * * *")
    public void logJob() 
	{
		Thread t = new Thread(bokUploader);
		t.start();
    }
	
	@Scheduled(cron = "0 0 0 * * *")
    public void statJob() 
	{	
		Thread t1 = new Thread(statJob);
		t1.start();
    }
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		logJob();
		
		statJob();
		
	}

}
