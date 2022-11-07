package Task;
import java.util.LinkedList;

public class BlockingQueue 
{
	
	private LinkedList<Runnable> queue = new LinkedList<Runnable>();
	
	private int maxSize;
	
	public BlockingQueue(int size) 
	{
		this.maxSize = size;
	}
	
	public synchronized void enqueue(Runnable task) // synchronized to lock an object for shared resource - it automatically acquires the lock for that object and releases it when the thread completes its task
		throws InterruptedException 
		{
			while(queue.size() == maxSize) 			// if the queue gets a task and the queue is full, it waits until a thread dequeues a task and then push it into the queue.
			{ 									
				wait(); 							// puts the threads in idle so they dont check the queue.size each time in infinite loop and consume CPU / processing power
			}
			if(queue.size() == 0) 					// if this queue is empty then wake up the threads that are waiting to enqueue tasks
			{ 
				notifyAll(); 						// wakes the threads to resume work
			}
			queue.addLast(task); 					// adds the task to the tail of the queue - push()
		}
		
	
	
	
	public synchronized Runnable dequeue() 			// locks the queue when a thread is ready to dequeue when another thread trying to enqueue.
		throws InterruptedException
		{
			while(queue.size() == 0) 				// if the queue is empty then the thread waiting to other thread to enqueue a task
			{ 
				wait(); 
			}
			if(queue.size() == maxSize) 			// if the queue is full then wake up threads to dequeue tasks.
			{ 
				notifyAll();
			}
			return queue.removeFirst(); 			// removes the first task in the queue - pop(). -> FIFO
		}
	
	public int getTasksCount() 						// returns the size of the queue / amount of tasks -> to see if the threads are done executing before stopping them.
	{
		return queue.size();
	}
}