package RadarSignalSimulation;

import detection.Detector;
import noise.NoiseModel;
import signal.SignalGenerator;

/*
 * [Radar Simulator] - This class is meant to simulate the different instances 
 * 					   of signals that could be generated.
 */
public class RadarSimulator 
{
	//Instance Variables
	private SignalGenerator	generator;
	private NoiseModel 		noiseModel;
	private Detector 		detector;
	
	//Constructor
	public RadarSimulator()
	{
		generator 	= new SignalGenerator();
		noiseModel 	= new NoiseModel();
		detector 	= new Detector();
	}
	
	/*
	 * Runs the method of creating a signal, adding noise and searching for a 
	 * target
	 * 
	 * @param [signalLength]	- Size of array signal
	 * @param [numTargets]		- Amount of targets
	 * @param [stdDev]			- Variance being given
	 * @param [threshold]		- Min amount to be considered a signal
	 * 	
	 * @return   				- Amount of targets found
	 */
	public int runTrial(int signalLength, 
							int numTargets, 
							double stdDev,
							double threshold)
	{
		double[] signal = generator.createSignal(signalLength, numTargets);
		double[] noisySignal = noiseModel.addNoise(signal, stdDev);
		int found = detector.countTargets(noisySignal, threshold);
		
		/*
		 * Since there can be a signal that was NOT initially a target, we
		 * return the smallest value between {found} and {numTargets} to avoid a
		 * value ABOVE {numTargets}
		 */
		return Math.min(found, numTargets);
	}
	
	/*
	 * Runs [runTrial] {n} amount of times to find the success rate of returning
	 * the correct amount of targets
	 * 
	 * @param [trials]			- Amount of trials specified
	 * @param [signalLength]	- Size of array signal
	 * @param [numTargets]		- Amount of targets
	 * @param [stdDev]			- Variance being given
	 * @param [threshold]		- Min amount to be considered a signal
	 * 	
	 * @return   				- Percentage of targets found
	 * 
	 */
	public double detectionRate(int trials,
								int signalLength,
								int numTargets,
								double stdDev,
								double threshold)
	{
		double percentageFound = 0.0;
		
		for(int i = 0; i < trials; i++)
		{
			int targetsFound = runTrial(signalLength,	numTargets, 
										stdDev, 	  	threshold);
			
			percentageFound += ((double)targetsFound / numTargets);
		}
		return percentageFound / trials;
	}
}