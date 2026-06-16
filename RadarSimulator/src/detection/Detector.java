package detection;

/*
 * [Detector] - Determines whether a target is present using threshold 
 * 				detection
 */
public class Detector 
{
	/*
	 * Searches through array and looks for outliers signifiying a target
	 * 
	 * @param [signal]		- Noisy array of values
	 * @param [threshold]	- Target value of a valid signal
	 * 
	 * @return 				- Boolean value if target was found
	 */
	public int countTargets(double[] signal, double threshold)
	{
		int count = 0;
		
		//Checks for clumps of Targets
		for(int i = 0; i < signal.length; i++)
		{
			if(signal[i] > threshold) 
			{
				count++;
				
				while((i + 1 < signal.length) && (signal[i + 1] > threshold))
				{
					i++;
				}
			}
		}
		
		return count;
	}
}
