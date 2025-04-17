package Line;

import java.util.*;

class Job implements Comparable<Job> {
    int duration;
    int requestTime;
    
    
    public Job(int requestTime,int duration) {
        this.duration = duration;
        this.requestTime = requestTime;
    }
    
    @Override
    public int compareTo(Job other) {
        if(this.duration == other.duration) {
            return this.requestTime - other.requestTime;
        }
        return this.duration - other.duration;
    }
}

class DiskController_PriorityQueue {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a,b)->a[0] - b[0]);
        
        PriorityQueue<Job> waitingQueue = new PriorityQueue<>();
        
        int time = 0;
        int count= 0;
        int index= 0;
        int returnTime = 0;
        while(count < jobs.length) {
            
            while(index < jobs.length && time>= jobs[index][0] ){
                waitingQueue.add(new Job(jobs[index][0],jobs[index][1]));
                index++;
            }
            
            
            if(!waitingQueue.isEmpty()) {
                Job currentJob = waitingQueue.poll();
                time += currentJob.duration;
                returnTime += (time - currentJob.requestTime);
                count++;
            } else {
                time++;
            }
                
        }
        int answer =  returnTime / jobs.length;
        
        return answer;
    }
}