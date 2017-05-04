package com.midtics.scheduler;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.midtics.util.BOKUploader;

@Component
public class EcosScheduler  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	BOKUploader bokUploader;
	
	@Scheduled(cron = "0 0 0 * * *")
    public void reportCurrentTime() {
		Thread t = new Thread(bokUploader);
		t.start();
		
    }
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		reportCurrentTime();
		try {
			bokUploader.insertEcosStatList();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}