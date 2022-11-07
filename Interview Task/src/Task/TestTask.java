package Task;
public class TestTask implements Runnable
{
	private int number;
	public TestTask(int number)
	{
		this.number = number;
	}
	
	@Override
	public void run() // runs when task.run() called in TaskExecutor.
	{ 
		System.out.println("Start executing of task number :"+number);
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("End executing task number :"+number);
	}
}
