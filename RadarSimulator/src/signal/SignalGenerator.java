package signal;

/*
 * [SignalGenerator] - Creates a synthetic radar signal with multiple targets.
 * 						Each target is represented as a spike in the signal 
 * 						intensity
 */
public class SignalGenerator 
{
	/*
	 * Creates a 1-d signal array representing time series radar-data
	 * 
	 * @param [n] 			- Number of time samples
	 * @param [numTargets] 	- Number of objects in the scene
	 * 
	 * @return 				- Radar signal with target spikes
	 */
	public double[] createSignal(int n, int numTargets) 
	{
		/**Invalid Inputs**/
		if(numTargets <= 0)
		{
			throw new IllegalArgumentException("numTargets must be > 0");
		}
		if(n <= 0)
		{
			throw new IllegalArgumentException("n must be > 0");
		}
		if(numTargets > n)
		{
			throw new IllegalArgumentException("n must be > numTargets");
		}
		double[] signal = new double[n];

		/*Start of [createSignal]*/
		for(int i = 0; i < numTargets; i++)
		{
			//Random placement of target
			int position = (int)(Math.random() * (n - 10));
			
			//Each target creates a small 'pulse'
			for(int j = position; j < position + 5; j++)
			{
				signal[j] += 3.0 + Math.random();
			}
		}
		
		return signal;
	}

}
