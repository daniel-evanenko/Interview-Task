package Task;
import java.util.ArrayList;

public class ThreadPool
{
	private BlockingQueue queue; // task queue
	private ArrayList<Thread> threadList; // thread list
	
	public ThreadPool(int queueSize, int numThreads) 
	{
		queue = new BlockingQueue(queueSize); // creates queue
		threadList = new ArrayList<Thread>(numThreads);
		String threadName;
		TaskExecutor taskExecutor;
		for(int i=0; i < numThreads; i++)
		{
			threadName = "Thread num-"+i; // giving each thread a "name"
			taskExecutor = new TaskExecutor(queue); // creating taskExecutar instance with queue of tasks
			Thread thread = new Thread(taskExecutor,threadName); // creating a thread and giving him a task executor and his name.
			thread.start(); // start the thread that executing tasks
			threadList.add(thread); // store the thread in a list
		}
	}
	
	public void submitTask(Runnable task) throws InterruptedException // gets a task the enqueues it to the queue.
	{
		queue.enqueue(task);
	}
	
	@SuppressWarnings("deprecation")
	public void stop() // stops all threads that after the task are done.
	{
		for(int i=0; i<threadList.size(); i++)
		{
			threadList.get(i).stop();
		}
	}
	
	public void waitUntilAllTasksFinished()
	{
        while(queue.getTasksCount() > 0) // checks if there are more task in the queue - if yes -> the main thread waits 2 seconds for the threads to finish working.
        { 						
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}