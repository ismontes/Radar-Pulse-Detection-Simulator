package detection;

public class Test_Detector 
{
	public static void main(String[] args)
	{
		testNoTargets();
		testingSingleTarget();
		testingMultipleTargets();
	}
	
	public static void testNoTargets()
	{
		Detector detector = new Detector();
		double[] signal = {0.2, 0.5, 1.1, 0.8};
		
		int[] count = detector.detectTargets(signal, 2.5);
		
		if(count.length == 0) {System.out.println("Pass: No targets detected");}
		else {System.out.println("Fail: Nothing should have been counted");}
	}
	
	public static void testingSingleTarget()
	{
	    Detector detector = new Detector();
	    double[] signal = {0, 0, 3.0, 3.1, 3.2, 0, 0};

	    int[] count = detector.detectTargets(signal, 2.5);
	    
	    if(count.length == 1) {System.out.println("Pass: Only one clump");}
	    else {System.out.println("Fail: More than one clump found");}
	}
	
	public static void testingMultipleTargets()
	{
	    Detector detector = new Detector();
	    double[] signal = {0, 3.4, 3.5, 1.0, 3.2, 3.3, 0};

	    int[] count = detector.detectTargets(signal, 2.5);
	    
	    if(count.length == 2) {System.out.println("Pass: Two clumps found");}
	    else {System.out.println("Fail: " + count + " found");}
	}
	
}