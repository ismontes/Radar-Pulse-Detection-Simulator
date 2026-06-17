package noise;

import signal.SignalGenerator;

public class Test_NoiseModel 
{
	public static void main(String[] args)
	{
		testOutputLength();
		testSignalModified();
		testZeroNoise();
	}
	
	public static void testOutputLength()
	{
		SignalGenerator gen = new SignalGenerator();
		NoiseModel noise = new NoiseModel();
		
		double[] signal = gen.createSignal(500, 3).getSignal();
		double[] noisy = noise.addNoise(signal, 1.0);
		
		if(signal.length == noisy.length)
		{
			System.out.println("Pass: Array lengths are the same");
		}
		else 
		{
			System.out.println("Fail: Array lengths are different");
		}
	}

	public static void testSignalModified()
	{
		SignalGenerator gen = new SignalGenerator();
		NoiseModel noise = new NoiseModel();
		
		double[] signal = gen.createSignal(500, 3).getSignal();
		double[] noisy = noise.addNoise(signal, 1.0);
		
		boolean changed = false;
		
		for(int i = 0; i < signal.length; i++)
		{
			if (signal[i] != noisy[i])
			{
				changed = true;
				break;
			}
		}
		
		if (changed) 	{System.out.println("Pass: Values are different");}
		else 			{System.out.println("Fail: Values are the same");}
	}
	
	public static void testZeroNoise()
	{
		SignalGenerator gen = new SignalGenerator();
		NoiseModel noise = new NoiseModel();
		
		double[] signal = gen.createSignal(500, 3).getSignal();
		double[] noisy = noise.addNoise(signal, 0.0);
		
		boolean same = true;
		
		for(int i = 0; i < signal.length; i++)
		{
			if(signal[i] != noisy[i])
			{
				same = false;
				break;
			}
		}
		
		if(same) {System.out.println("Pass: Zero noise effects nothing");}
		else {System.out.println("Fail: Zoise is effectin signal");}
	}

}