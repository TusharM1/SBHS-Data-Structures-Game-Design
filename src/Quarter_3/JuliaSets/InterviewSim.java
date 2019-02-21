package Quarter_3.JuliaSets;

public class InterviewSim {

	public static void main(String[] args) {
		System.out.println(sMin(new int[]{1,6,3,8,9,2}));
	}

	private static int sMin(int[] arr) {
		int min = arr[0], sMin = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			else if (arr[i] < sMin && arr[i] > min)
				sMin = arr[i];
		}
		return sMin;
	}

}
