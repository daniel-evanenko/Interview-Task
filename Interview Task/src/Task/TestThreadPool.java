package Task;

public class TestThreadPool
{

	public static void main(String[] args) throws InterruptedException
	{
		ThreadPool threadPool = new ThreadPool(3,4); // create queue, then create amount of threads that given, initialize tasks, create threads with there task and name, the thread call start that calling run in TaskExecutor.
		
		for(int tasknumber = 1; tasknumber<=15; tasknumber++)
		{
			TestTask task = new TestTask(tasknumber); // create task instances with task number
			threadPool.submitTask(task); // submit the task
		}
		
		threadPool.waitUntilAllTasksFinished(); // after submitting all the task, wait until the task are finished
		threadPool.stop(); // stops the threads.
	}

}
