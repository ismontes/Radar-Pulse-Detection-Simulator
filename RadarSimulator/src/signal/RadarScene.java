package signal;

/*
 * [RadarScene]	- This is supposed to represent the signal before we have added
 * 				  any noise so we have a clear understanding of where targets 
 * 				  were originally located
 */
public class RadarScene 
{

	private double[] signal;		//Raw signal BEFORE noise
	private int[] trueTargetLoc;	//Actual location of targets inside signal
	
	/*
	 * [Constructor]
	 * 
	 * @param [signal]			-Signal before noise added
	 * @param [trueTargetLoc]	-Index Origins of target signals 
	 */
	public RadarScene(double[] signal, int[] trueTargetLoc)
	{
		this.signal = signal;
		this.trueTargetLoc = trueTargetLoc;
	}
	
	/******************************[Getters]***********************************/
	
	
	public double[] getSignal()
	{
		return signal;
	}
	
	public int[] getTrueTargetLoc()
	{
		return trueTargetLoc;
	}
}
