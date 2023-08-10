//package com.api.scheduler;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import com.api.entity.Post;
//import com.api.repo.PostRepo;
//
//@Component
//public class SchedulerService {
//
//	private static final Logger log=LoggerFactory.getLogger(SchedulerService.class);
//
//	
//	@Autowired
//	PostRepo  postRepo;
//	
//	@Scheduled(fixedRate =2,timeUnit = TimeUnit.SECONDS)
//	public void SchedulerServiceMethod()
//	{
//		
//      LocalDateTime  startdate=LocalDateTime.now().minusMonths(18);
//      LocalDateTime  enddate=LocalDateTime.now().minusHours(39);
//	
//    
//      log.info("local date and time is= {} ", startdate,enddate);
//      
//	  List<Post>  ll=postRepo.findAllPosts(startdate,enddate);
//      
//    
//		
//	  log.info("All posts are = {} ", ll);
//	
//      
//      
//	}	
//}
