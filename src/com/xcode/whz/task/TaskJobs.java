package com.xcode.whz.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xcode.whz.bean.ProductInfo;
import com.xcode.whz.service.ProductService;
import com.xcode.whz.util.MailUtil;

@Component("TaskJobs")
public class TaskJobs {
	protected final static Log log = LogFactory.getLog(TaskJobs.class);
	@Autowired
	ProductService productService;


//0 0 12 * * ?	每天12点触发
//0 15 10 ? * *	每天10点15分触发
//0 15 10 * * ?	每天10点15分触发
//0 15 10 * * ? *	每天10点15分触发
//0 15 10 * * ? 2005	2005年每天10点15分触发
//0 * 14 * * ?	每天下午的 2点到2点59分每分触发
//0 0/5 14 * * ?	每天下午的 2点到2点59分(整点开始，每隔5分触发)
//0 0/5 14,18 * * ?  每天下午的2点到2点59分 18点到18点59分(整点开始，每隔5分触发)
//0 0-5 14 * * ?	每天下午的 2点到2点05分每分触发
//0 10,44 14 ? 3 WED	3月分每周三下午的 2点10分和2点44分触发
//0 15 10 ? * MON-FRI	从周一到周五每天上午的10点15分触发
//0 15 10 15 * ?	每月15号上午10点15分触发
//0 15 10 L * ?	每月最后一天的10点15分触发
//0 15 10 ? * 6L	每月最后一周的星期五的10点15分触发
//0 15 10 ? * 6L 2002-2005	从2002年到2005年每月最后一周的星期五的10点15分触发
//0 15 10 ? * 6#3	每月的第三周的星期五开始触发
//0 0 12 1/5 * ?	每月的第一个中午开始每隔5天触发一次
//0 11 11 11 11 ?	每年的11月11号 11点11分触发(光棍节)
//	    @Scheduled(cron= "0 56 21 * * ?")//那个时间一次
	    @Scheduled(fixedDelay = 100000000)// 多少毫秒一次
	    public void job1() {  
	    	log.info("==========start");  
	    	ProductInfo product=new ProductInfo();
			product.setState("1");
//			List productList=productService.selectProduct(product);
//			for (Object object : productList) {
//				ProductInfo product1=(ProductInfo)object;
//				System.out.println(product1.getProductname());
//				System.out.println(product1.getPrice());
//				System.out.println(product1.getExplanation());
//				//MailUtil.sendMail("568634265@qq.com","8888888888888");
//			}
			log.info("==========end");  
	    }  
	    @Scheduled(fixedDelay = 500000000)// 多少毫秒一次
	    public void job2() {  
	    	log.info("==========start3");  
	    }  

}
