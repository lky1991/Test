package com.lky.Beanstalk;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.trendrr.beanstalk.BeanstalkClient;
import com.trendrr.beanstalk.BeanstalkException;
import com.trendrr.beanstalk.BeanstalkJob;
import com.trendrr.beanstalk.BeanstalkPool;
import com.trendrr.beanstalk.Beanstalktube;

public class Example {

	protected static Log log = LogFactory.getLog(Example.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			clientExample();
		} catch (BeanstalkException e) {
			e.printStackTrace();
		}
	}
	public static void clientExample() throws BeanstalkException {
		
		Beanstalktube bt=new Beanstalktube("10.21.25.183",11300,"example");
		System.out.println(bt.getBuried_job_Count());
		
//		for(int i=50;i>0;--i){
//			log.info("Putting a job");
//			String resString="my priority is"+i;
//			client.put((long)i, 0, 5000, resString.getBytes());
//		}
//		for(int i=0;i<3;++i){
//			BeanstalkJob job = client.reserve(60);
//			log.info("GOt job: " + job);
//			long id=job.getId();
//			byte [] data=job.getData();
//			String res=null;
//			try {
//				res=new String(data,"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			System.out.println("The job id= "+id+" The job data= "+res);
//			client.bury(job, 100);
//		}	
		
//		System.out.println(client.tubeStats("example"));
//		client.close(); //closes the connection
	}
	
	
	public static void pooledExample()  throws BeanstalkException {
		BeanstalkPool pool = new BeanstalkPool("localhost", 8010, 
				30, //poolsize 
			"example" //tube to use
		);
		
		BeanstalkClient client = pool.getClient();
		
		log.info("Putting a job");
		client.put(1l, 0, 5000, "this is some data".getBytes());
		BeanstalkJob job = client.reserve(60);
		log.info("GOt job: " + job);
		client.deleteJob(job);
		client.close();  //returns the connection to the pool
	}
}
