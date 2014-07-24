package sort.fast;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[]{21, 42, 4, 23, 12, 90, 2};
		FastSort.sort(a, 0, a.length-1);
		for(int x:a){
			System.out.println(x);
		}
	}
}
