package download;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RemainDownloadObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1998443232946710780L;
	private String target_file = null;
	private String local_file = null;
	private Map<Integer, EveryThreadToDownloadObj> map = new HashMap<Integer,EveryThreadToDownloadObj>();
	
	public Map<Integer, EveryThreadToDownloadObj> getMap() {
		return map;
	}


	public void setMap(Map<Integer, EveryThreadToDownloadObj> map) {
		this.map = map;
	}


	public String getTarget_file() {
		return target_file;
	}


	public void setTarget_file(String target_file) {
		this.target_file = target_file;
	}


	public String getLocal_file() {
		return local_file;
	}


	public void setLocal_file(String local_file) {
		this.local_file = local_file;
	}


	class EveryThreadToDownloadObj implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int startPosition = 0;
		private int endPosition = 0;
		private int currentThreadPosition = 0;
		private int totalDownloadSize = 0;
		public int getStartPosition() {
			return startPosition;
		}
		public void setStartPosition(int startPosition) {
			this.startPosition = startPosition;
		}
		public int getEndPosition() {
			return endPosition;
		}
		public void setEndPosition(int endPosition) {
			this.endPosition = endPosition;
		}
		public int getCurrentThreadPosition() {
			return currentThreadPosition;
		}
		public void setCurrentThreadPosition(int currentThreadPosition) {
			this.currentThreadPosition = currentThreadPosition;
		}
		
		public String toString(){
			return "\tstart position: " + this.startPosition + 
					"\n\tend position: "+this.endPosition+
					"\n\t thread position: "+this.currentThreadPosition+
					"\n\t thread total download size: "+this.totalDownloadSize;
		}
		public int getTotalDownloadSize() {
			return totalDownloadSize;
		}
		public void setTotalDownloadSize(int totalDownloadSize) {
			this.totalDownloadSize = totalDownloadSize;
		}
	}
	
	public String toString(){
		StringBuilder msg = new StringBuilder();
		msg.append("target file: "+this.target_file);
		msg.append("\nlocal file: "+this.local_file);
		for(int o:this.map.keySet()){
			msg.append("\n\t thread " + o + ": " + this.map.get(o).toString());
		}
		return msg.toString();
	}
}
