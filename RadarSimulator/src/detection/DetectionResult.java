package detection;

/*
 * [DetectionResult] - Stores the evaluation outcomes
 */
public class DetectionResult 
{
	//Statistics
	private int truePos;
	private int falsePos;
	private int falseNeg;
	
	/*
	 * [Constructor]
	 * 
	 * @param [tp]
	 * @param [fp]
	 * @param [fn]
	 */
	public DetectionResult(int tp, int fp, int fn)
	{
		truePos = tp;
		falsePos = fp;
		falseNeg = fn;
	}
	
	
	/******************************[Getters]***********************************/
	public int getTruePos() {return truePos;}
	
	public int getFalsePos() {return falsePos;}
	
	public int getFalseNeg() {return falseNeg;}
	
	public double getDetectionRate()
	{
		if(truePos + falseNeg == 0) return 0;
		return (double) truePos / (truePos + falseNeg);
	}
	
	public double getFalseAlarmRate()
	{
		if(truePos + falsePos == 0) return 0;
		return(double) falsePos / (truePos + falsePos);
	}
}
