//package com.api.scheduler;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SchedulerTest {
//
//	private static final Logger log=LoggerFactory.getLogger(SchedulerTest.class);
//	private static final DateTimeFormatter  dateFormat=DateTimeFormatter.ofPattern("HH:MM:SS");
//	
//	@Autowired
//	private SchedulerService  schedulerService;
//	
//	@Scheduled(fixedRate =1,timeUnit = TimeUnit.SECONDS)
//	public void scheduledMethod()
//	{
//	
//		log.info("Fixed Rate Task :: Execution Time = {}", dateFormat.format(LocalDateTime.now()));
//	        
//		schedulerService.SchedulerServiceMethod();
//		
//	}
//}
