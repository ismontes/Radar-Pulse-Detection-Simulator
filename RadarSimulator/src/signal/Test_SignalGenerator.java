package signal;

public class Test_SignalGenerator 
{

	public static void main(String[] args) 
	{
		testSignalLength();
		testNoTargets();
		testSingleTarget();
		testMultipleTargets();
		
		System.out.println("All tests passed :D");
		
	}

	public static void testSignalLength()
	{
		SignalGenerator gen = new SignalGenerator();
		double[] signal = gen.createSignal(500, 3);
		
		if(signal.length != 500) 	{System.out.println("Fail: Wrong Length");}
		else 						{System.out.println("Pass: Right Length");}
	}
	
	public static void testNoTargets()
	{
		SignalGenerator gen = new SignalGenerator();
		
		try
		{
			@SuppressWarnings("unused")
			double[] signal = gen.createSignal(500, 0);
		} 
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage().equals("numTargets must be > 0"));
		}
		
	}

	public static void testSingleTarget()
	{
		SignalGenerator gen = new SignalGenerator();
		double[] signal = gen.createSignal(500, 3);
		
		@SuppressWarnings("unused")
		boolean targetFound = false;
		for(double val : signal)
		{
			if(val > 0) {targetFound = true; break;}
		}
		
		if(targetFound)
		{
			System.out.println("Pass: Target exists");
		}
		else
		{
			System.out.println("Fail: Target wasn't found");
		}
	}
	
	public static void testMultipleTargets()
	{
		SignalGenerator gen = new SignalGenerator();
		double[] s1 = gen.createSignal(500, 1);
		double[] s5 = gen.createSignal(500, 5);
		
		double e1 = 0.0;
		double e5 = 0.0;
		
		for(double val : s1) {e1 += val * val;}
		for(double val : s5) {e5 += val * val;}
		
		if(e5 <= e1) 
		{System.out.println("Fail: Energy didn't increase with more outputs");}
		else
		{System.out.println("Pass: Energy scales with number of targets");}
	}
	
}