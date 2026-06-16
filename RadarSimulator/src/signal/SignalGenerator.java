package signal;

/*
 * [SignalGenerator] - Creates a synthetic radar signal with multiple targets.
 * 					   Each target is represented as a spike in the signal 
 * 					   intensity
 */
public class SignalGenerator 
{
	/*
	 * [createSignal] - Creates a 1-d signal array with it's corresponding index 
	 * 					positions
	 * 
	 * @param [n] 			- Number of time samples
	 * @param [numTargets] 	- Number of objects in the scene
	 * 
	 * @return [RadarScene]	- Signal array with TargetIndexed array
	 */
	public RadarScene createSignal(int n, int numTargets) 
	{
		/************************[Invalid Inputs]******************************/
		if(numTargets <= 0 || n <= 0)
		{
			throw new IllegalArgumentException("Inputs must be > 0");
		}
		if(numTargets > n / 5)
		{
			throw new IllegalArgumentException("Too many targets for signal"
												+ "size");
		}

		
		/**********************[createSignal]**********************************/
		double[] signal = new double[n];
		int[] pulseIndx = new int[numTargets];
		int index = 0;
		
		for(int i = 0; i < numTargets; i++)
		{
			//Random placement of target
			int position = (int)(Math.random() * (n - 5));
			
			//Ensure no duplicate positions for pulse
			boolean duplicate = false;
			for(int j = 0; j < index; j++)
			{
				if(pulseIndx[j] == position);
				{
					duplicate = true;
					break;
				}
			}
			
			//Retry the iteration with a new position value
			if(duplicate)
			{
				i--;
				continue;
			}
			
			pulseIndx[index++] = position;
			
			//Each target creates a small 'pulse'
			for(int j = position; (j < position + 5) && (j < n); j++)
			{
				signal[j] += 3.0 + Math.random();
			}
		}
		
		return new RadarScene(signal, pulseIndx);
	}
}
