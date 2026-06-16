package RadarSignalSimulation;

import detection.DetectionResult;
import detection.Detector;
import noise.NoiseModel;
import signal.RadarScene;
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
	 * [runTrial] - Creates pulses, adds noise, detects, and evaluates
	 * 
	 * @param [signalLength]	- Size of array signal
	 * @param [numTargets]		- Amount of targets
	 * @param [stdDev]			- Variance being given
	 * @param [threshold]		- Min amount to be considered a signal
	 * @param [tolerance]		- (+/-) error on detections
	 * 	
	 * @return   				- Statistics
	 */
	public DetectionResult runTrial(int signalLength, 
						int numTargets, 
						double stdDev,
						double threshold, 
						int tolerance)
	{
		//[1] - Generate the pulse array
		RadarScene pulse = generator.createSignal(signalLength, numTargets);
		
		//[2] - Add noise
		double[] noisySignal = noiseModel.addNoise(pulse.getSignal(), stdDev);
		
		//[3] - Detect targets (w/ returned locations)
		int[] detected = detector.detectTargets(noisySignal, threshold);
		
		//[4] - Evaluate detected targets against original targets
		return evaluate(pulse.getTrueTargetLoc(), detected, tolerance);
	}
	
	
	/*
	 * [detectionRate] - Runs {trial} amount of times to find the success rate 
	 * 					 of returning the correct amount of targets
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
								double threshold,
								int tolerance)
	{
		double totalDetectionRate = 0.0;
		
		for(int i = 0; i < trials; i++)
		{
			DetectionResult result = runTrial(signalLength, numTargets, stdDev, 
												threshold, tolerance);
			
			totalDetectionRate += result.getDetectionRate();
		}
		
		return totalDetectionRate / trials;
	}
	
	/*
	 * [evaluate] - Compares the original pulse with the detected pulses using a 
	 * 				tolerance window. A detection is considered correct when it
	 * 				is within +/- of tolerance. 
	 * 
	 * @param [truePulses]		- Array of original pulses
	 * @param [detectedPulses]	- Array where detected pulses were found
	 * @param [tolerance]		- Max allowed index error for correct match
	 * 
	 * @return 					- Results containing TP, FP, FN
	 */
	private DetectionResult evaluate(int[] truePulses, int[] detectedPulses,
										int tolerance) 
	{
		/***********************[Variables]************************************/
		boolean[] matchedTrue = new boolean[truePulses.length];
		boolean[] matchedDet = new boolean[detectedPulses.length];
		
		int tp = 0;
		int fp = 0;
		
		
		/***********************[Matching values]******************************/
		
		/*
		 * For each detected pulse, we search for a true pulse that is within 
		 * the tolerance window
		 */
		
		for(int i = 0; i < detectedPulses.length; i++)
		{
			for(int j = 0; j < truePulses.length; j++) 
			{
				
				/*
				 * [1] - True pulse has not been matched 
				 * [2] - Detected pulse is close enough to true pulse
				 */
				if((!matchedTrue[j]) && 
					(Math.abs(detectedPulses[i] - truePulses[j]) <= tolerance))
				{
					//Mark both as matched
					matchedTrue[j] = true;
					matchedDet[i] = true;
					
					tp++;	//Successful detection
					break;
				}
			}
		}
		
		
		/*
		 * Any detected pulse that wasn't matched is a {false positive}
		 */
		for(int i = 0; i < detectedPulses.length; i++)
		{
			if(!matchedDet[i]) {fp++;}
		}
		
		
		/*
		 * Any true pulse that was never matched is a {false negative}
		 */
		int fn = 0;
		
		for(int i = 0; i < truePulses.length; i++)
		{
			if(!matchedTrue[i]) {fn++;}
		}
		
		return new DetectionResult(tp, fp, fn);
	}
}