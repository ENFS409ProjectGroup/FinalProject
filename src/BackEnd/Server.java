package BackEnd;


import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class deals with the transfer of data between the client, database, and the system.
 * It receives information and decides what action to take.
 * 
 * @author Tevin Schmidt
 *
 */
public class Server{
	private Driver driver;
	
	private int nThreads;
	private Manager [] threads;
	private LinkedBlockingQueue<Task> queue;

	
	public Server(int numThreads){
		driver = new Driver();
		
		/*this.nThreads = numThreads;
		queue = new LinkedBlockingQueue<Task>();
		threads = new Manager[nThreads];
		
		for(int i = 0; i < nThreads; i++){
			threads[i] = new Manager();
			threads[i].start();
		}*/
	}
	
	public void run(){
		driver.initialize();
		
	}
	
	public void serialize(){
		
	}
	
	public void deserialize(){
		
	}
	
	public LinkedBlockingQueue<Task> getQueue(){
		return this.queue;
	}
	
	public Driver getDriver(){
		return this.driver;
	}
	
	public static void main(String []args){
		Server theServer = new Server(10);
		theServer.run();
	}
}
