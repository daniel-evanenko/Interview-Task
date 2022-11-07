package Task;
public class TaskExecutor implements Runnable  
{
	private BlockingQueue queue;
	
	public TaskExecutor(BlockingQueue queue) 
	{
		this.queue = queue;
	}	
	
	@Override
	public void run()  //runs when thread.start() is called 
	{
		try 
		{
			while(true) // dequeue from the queue and execute the task
			{
				String threadName = Thread.currentThread().getName();
				Runnable task = queue.dequeue(); // Try to dequeue a task from the queue, if it dequeues -> run the task, else -> thread is waiting a task to enqueue to the queue.
				System.out.println("Task started by :"+threadName);
				task.run();
				System.out.println("Task finished by :"+threadName);
			}
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
