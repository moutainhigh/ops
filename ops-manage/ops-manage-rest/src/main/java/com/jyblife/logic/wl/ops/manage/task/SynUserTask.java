package com.jyblife.logic.wl.ops.manage.task;

/**
 * 定时同步用户中心数据
 * 
 * 目前只有一个定时任务所以暂时先放这里
 * 
 * 暂时可以不需要 先注释调
 */
//@Component
//@EnableScheduling
public class SynUserTask {

	//@Autowired
	//private UserCenterService synService;
	
	/**
	 * 每天凌晨1点执行一次
	 */
	//@Scheduled(cron = "0 0 1 * * ?")
	public void synTask() {
		//synService.synUser();
	}
	
}
