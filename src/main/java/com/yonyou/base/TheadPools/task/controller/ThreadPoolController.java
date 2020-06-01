package com.yonyou.base.TheadPools.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class ThreadPoolController {

	@Autowired
    private	ThreadPoolTaskExecutor executor;
	
	@RequestMapping("/loopQueryTicket.do")
	public void loopQueryTicket(){
		QueryTicket queryTicket=new QueryTicket("zhagnsan", 3, 5);
		executor.execute(queryTicket);
	}
}
