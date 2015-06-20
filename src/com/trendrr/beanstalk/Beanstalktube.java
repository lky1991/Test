package com.trendrr.beanstalk;

import java.util.HashMap;
import java.util.Map.Entry;

public class Beanstalktube {
	private BeanstalkClient client;
	private String tubeName;
	private Integer ready_job_Count;
	private Integer delayed_job_Count;
	private Integer reserved_job_Count;
	private Integer buried_job_Count;
	private Integer delete_job_Count;
	private Integer jobs_Count;
	private HashMap<String, Integer> tubeStates;
	
	
	public HashMap<String, Integer> getTubeStates() {
		return tubeStates;
	}

	public void setTubeStates(HashMap<String, Integer> tubeStates) {
		this.tubeStates = tubeStates;
	}
	
	public Integer getReady_job_Count() {
		return ready_job_Count;
	}

	public void setReady_job_Count(Integer ready_job_Count) {
		this.ready_job_Count = ready_job_Count;
	}

	public Integer getDelayed_job_Count() {
		return delayed_job_Count;
	}

	public void setDelayed_job_Count(Integer delayed_job_Count) {
		this.delayed_job_Count = delayed_job_Count;
	}

	public Integer getReserved_job_Count() {
		return reserved_job_Count;
	}

	public void setReserved_job_Count(Integer reserved_job_Count) {
		this.reserved_job_Count = reserved_job_Count;
	}

	public Integer getBuried_job_Count() {
		return buried_job_Count;
	}

	public void setBuried_job_Count(Integer buried_job_Count) {
		this.buried_job_Count = buried_job_Count;
	}

	public Integer getDelete_job_Count() {
		return delete_job_Count;
	}

	public void setDelete_job_Count(Integer delete_job_Count) {
		this.delete_job_Count = delete_job_Count;
	}

	public Integer getJobs_Count() {
		return jobs_Count;
	}

	public void setJobs_Count(Integer jobs_Count) {
		this.jobs_Count = jobs_Count;
	}

	public Beanstalktube(String ip,int port,String tubeName){
		this.tubeName=tubeName;
		tubeStates=new HashMap<>();
		client= new BeanstalkClient(ip,port,tubeName);
		State();//统计该tube中每个状态下job的数目
		client.close();
		
	}
	
	private void State(){
		String result=null;
		try {
			result=client.tubeStats(this.tubeName);
			String []res=result.split("\n");
			for(int i=2;i<res.length;++i){
				String []ans=res[i].split(":");
				Integer number=Integer.valueOf(ans[1].trim());
				tubeStates.put(ans[0], number);
			}
		} catch (BeanstalkException e) {
			e.printStackTrace();
		}
		for (Entry<String, Integer> entry : tubeStates.entrySet()) {
			if(entry.getKey().equals("current-jobs-ready")){
				this.setReady_job_Count(entry.getValue());
			}else if(entry.getKey().equals("current-jobs-reserved")){
				this.setReserved_job_Count(entry.getValue());
			}else if(entry.getKey().equals("current-jobs-delayed")){
				this.setDelayed_job_Count(entry.getValue());
			}else if(entry.getKey().equals("current-jobs-buried")){
				this.setBuried_job_Count(entry.getValue());
			}else if(entry.getKey().equals("total-jobs")){
				this.setJobs_Count(entry.getValue());
			}else if(entry.getKey().equals("cmd-delete")){
				this.setDelete_job_Count(entry.getValue());
			}
		}
	}
}
