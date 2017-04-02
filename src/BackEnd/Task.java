package BackEnd;

/**
 * This class is used as a communicator to the thread pool. The Server 
 * will package a request from the client in a task and send it to the 
 * thread pool to be executed. The execution gets taken care of by the 
 * Manager class.
 * 
 * @author Tevin Schmidt
 *
 */
public class Task implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
