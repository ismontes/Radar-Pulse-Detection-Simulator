package RadarSignalSimulation;

import detection.DetectionResult;

public class Test_RadarSimulator 
{
	public static void main(String[] args) 
	{
		/*
		 * Average Test
		 */
		RadarSimulator test1 = new RadarSimulator();
		DetectionResult result1 = test1.runTrial(500, 15, 1.0, 2.5, 1);
		System.out.println("Test 1: " + result1 + "\n");
	
		/*
		 * High Noise
		 */
		RadarSimulator test2 = new RadarSimulator();
		DetectionResult result2 = test2.runTrial(500, 15, 4.0, 2.5, 1);
		System.out.println("Test 2: " + result2 + "\n");
		
		/*
		 * Easy Detection
		 */
		RadarSimulator test3 = new RadarSimulator();
		DetectionResult result3 = test3.runTrial(1000, 5, 0.5, 2.5, 2);
		System.out.println("Test 3: " + result3 + "\n");
		
		/*
		 * High threshold
		 */
		RadarSimulator test4 = new RadarSimulator();
		DetectionResult result4 = test4.runTrial(500, 15, 1.0, 5.0, 1);
		System.out.println("Test 4: " + result4 + "\n");
		
		/*
		 * Exact matching required
		 */
		RadarSimulator test5 = new RadarSimulator();
		DetectionResult result5 = test5.runTrial(500, 15, 1.0, 2.5, 0);
		System.out.println("Test 5: " + result5 + "\n");
	}

}