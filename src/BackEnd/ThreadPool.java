package BackEnd;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
	private Worker [] threads;
	private LinkedBlockingQueue<Task> queue;
	
	public ThreadPool(int nThreads){
		queue = new LinkedBlockingQueue<Task>();
		threads = new Worker[nThreads];
		
		for(int i = 0; i < nThreads; i++){
			threads[i] = new Worker();
			threads[i].start();
		}
	}
	
	public void execute(Task task){
		synchronized (queue){
			queue.add(task);
			queue.notify();
		}
	}
	
	private class Worker extends Thread {
		public void run(){
			Task toPerform;
			
			while(true){
				synchronized (queue) {
					while(queue.isEmpty()){
						try{
							queue.wait();
						}
						catch(InterruptedException e){
							System.err.println("An error occurred while queue is waiting.");
							System.err.println(e.getMessage());
						}
						
					}
					toPerform = queue.poll();
				}
				
				try{
					toPerform.run();
				}
				catch(RuntimeException e){
					System.err.println("Thread pool is interrupted due to an issue: " + e.getMessage());
				}
			}
			
		}
	}
}
