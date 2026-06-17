package RadarSignalSimulation;

public class Test_RadarSimulator 
{

	public static void main(String[] args) 
	{
		RadarSimulator test1 = new RadarSimulator();
		System.out.println("Test 1: " + test1.detectionRate(
										100, 500, 15, 1.0, 2.5, 1));
	
		RadarSimulator test2 = new RadarSimulator();
		System.out.println("Test 2: " + test2.detectionRate(
										100, 500, 3, 4.0, 10.0, 2));
	}

}