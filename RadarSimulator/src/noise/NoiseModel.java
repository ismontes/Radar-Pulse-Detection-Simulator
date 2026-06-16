package noise;

import java.util.Random;

/*
 * [NoiseModel] - 	Simulates real-world sensor noise. We take a clean signal 
 * 					and 'corrupt' it using Guassian Noise
 */
public class NoiseModel 
{
	private Random rand = new Random();
	
	/*
	 * Adds a Gaussian noise to the signal
	 * 
	 * @param [signal]	- Signal array from SignalGenerator
	 * @param [stdDev]	- Strength of noise
	 * 
	 * @return 			- Noisy signal double array
	 */
	public double[] addNoise(double[] signal, double stdDev)
	{
		/*Invalid Inputs*/
		if(stdDev < 0)
		{
			throw new IllegalArgumentException("Standard Deviation must "
					+ "be > 0");
		}
		
		double[] noisy = new double[signal.length];
		
		for(int i = 0; i < noisy.length; i++)
		{
			//Generate Gaussian noise (mean = 0, variance = 1)
			double noise = rand.nextGaussian() * stdDev;
			
			noisy[i] = signal[i] + noise;
		}
		
		return noisy;
	}
}