package sort.fast;
public class FastSort {
	
	//String[] toSortList = null;
	//С����
	public static void sort_wrong(Integer[] toSortList, int start, int end){
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
	
	public static void sort(Integer[] toSortList, int startPos, int endPos){
		
		if(startPos == endPos){
			return;
		}
		
		boolean fromEndToStart = true;
		int target = startPos;
		int targetVal = toSortList[target];
		int start = startPos;
		int end = endPos;
		
		while(true){
			if(start == end){
				if(target == startPos){
					//sort(toSortList, startPos, target);
					sort(toSortList, startPos+1, endPos);
				} else if (target == endPos){
					sort(toSortList, startPos, endPos-1);
					//sort(toSortList, target, endPos);
				} else {
					sort(toSortList, startPos, target-1);
					sort(toSortList, target+1, endPos);
				}
				break;
			}
			if(fromEndToStart){
				//System.out.println("endPosition:" + end);
				if(toSortList[end] < targetVal){
					toSortList[target] = toSortList[end];
					toSortList[end] = targetVal;
					target = end;
					fromEndToStart = false;
				} else {
					end--;
				}
			} else {
				if(toSortList[start] > targetVal){
					toSortList[target] = toSortList[start];
					toSortList[start] = targetVal;
					target = start;
					fromEndToStart = true;
				} else {
					start++;
				}
			}
		}
	}
}
