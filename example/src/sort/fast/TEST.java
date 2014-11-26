package sort.fast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] a = new int[]{44, 42, 4, 23, 12, 90, 2};
		//int[] a = new int[]{1, 42, 4};
		long startTime = System.currentTimeMillis();
		Integer[] a=getData();
		FastSort.sort(a, 0, a.length-1);
		System.out.println("排序时间："+ (System.currentTimeMillis() - startTime));
		for(int x:a){
			System.out.println(x);
		}
	}
	
	public static Integer[] getData(){
		List<Integer> ls = new ArrayList<Integer>();
		for(int x=0; x<1000000; x++){
			ls.add((int)(Math.random() * 1000000));
		}
		return ls.toArray(new Integer[ls.size()]);
	}
}
