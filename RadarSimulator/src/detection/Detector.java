package detection;

import java.util.ArrayList;
import java.util.List;

/*
 * [Detector] - Determines whether a target is present using threshold 
 * 				detection and stores index values in which they are found
 */
public class Detector 
{
	/*
	 * [detectTargets] - Searches through array and looks for outliers 
	 * 					 signifying a target
	 * 
	 * @param [signal]		- Noisy array of values
	 * @param [threshold]	- Min value of a valid signal
	 * 
	 * @return 				- Start index of each detected pulse
	 */
	public int[] detectTargets(double[] signal, double threshold)
	{
		List<Integer> detected = new ArrayList<>();
		
		//Search array
		for(int i = 0; i < signal.length; i++)
		{
			//Found rising edge of pulse
			if(signal[i] > threshold) 
			{
				detected.add(i);
				
				//Skip the rest of the pulse
				while((i + 1 < signal.length) && (signal[i + 1] > threshold))
				{
					i++;
				}
			}
		}
		
		//Convert [list] to [array]
		int[] result = new int[detected.size()];
		for(int i = 0; i < detected.size(); i++)
		{
			result[i] = detected.get(i);
		}
		
		return result;
	}
}
