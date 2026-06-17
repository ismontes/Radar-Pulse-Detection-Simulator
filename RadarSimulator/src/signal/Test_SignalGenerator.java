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
		RadarScene scene = gen.createSignal(500, 3);
		double[] signal = scene.getSignal();
		
		if(signal.length != 500) 	{System.out.println("Fail: Wrong Length");}
		else 						{System.out.println("Pass: Right Length");}
	}
	
	public static void testNoTargets()
	{
		SignalGenerator gen = new SignalGenerator();
		
		try
		{
			@SuppressWarnings("unused")
			RadarScene scene = gen.createSignal(500, -8);
		} 
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage().equals("Inputs must be > 0"));
		}
		
	}

	public static void testSingleTarget()
	{
		SignalGenerator gen = new SignalGenerator();
		RadarScene signal = gen.createSignal(500, 3);
		
		@SuppressWarnings("unused")
		boolean targetFound = false;
		for(double val : signal.getSignal())
		{
			if(val > 0) {targetFound = true; break;}
		}
		
		if(targetFound)
		{
			System.out.println("Pass: Single target exists");
		}
		else
		{
			System.out.println("Fail: Single target wasn't found");
		}
	}
	
	public static void testMultipleTargets()
	{
		SignalGenerator gen = new SignalGenerator();
		double[] s1 = gen.createSignal(500, 1).getSignal();
		double[] s5 = gen.createSignal(500, 5).getSignal();
		
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