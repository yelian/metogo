package sort.fast;
public class FastSort {
	
	//String[] toSortList = null;
	//Ð¡µ½´ó
	public static void sort(int[] toSortList, int start, int end){
		int div = toSortList[start];
		int target = start;
		int startPosition = start;
		int endPosition = end;
		boolean isEndToFront = true;
		if(start == end){
			return;
		}
		while(true){
			if(startPosition == endPosition){
				FastSort.sort(toSortList, start, target);
				FastSort.sort(toSortList, target, end);
				break;
			}
			if(isEndToFront){
				if(toSortList[endPosition] < div){
					toSortList[target] = toSortList[endPosition];
					toSortList[endPosition] = div;
					target = endPosition;
					//endPosition--;
					isEndToFront = false;

					//for good test
					for(int x=0; x<toSortList.length; x++){
						System.out.print(toSortList[x] + " ,");
					}
					System.out.println();
				}
				endPosition--;
			} else {
				if(toSortList[startPosition] > div){
					toSortList[target] = toSortList[startPosition];
					toSortList[startPosition] = div;
					target = startPosition;
					//startPosition++;
					isEndToFront = true;

					//for good test
					for(int x=0; x<toSortList.length; x++){
						System.out.print(toSortList[x] + " ,");
					}
					System.out.println();
				}
				startPosition++;
			}
		}
	}
}
