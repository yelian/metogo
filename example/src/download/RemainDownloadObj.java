package download;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RemainDownloadObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1998443232946710780L;
	private String target_file = null;
	private String local_file = null;
	private List<EveryThreadToDownloadObj> list = new ArrayList<EveryThreadToDownloadObj>();
	
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


	public List<EveryThreadToDownloadObj> getList() {
		return list;
	}


	public void setList(List<EveryThreadToDownloadObj> list) {
		this.list = list;
	}


	class EveryThreadToDownloadObj implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int currentPosition = 0;
		private int endPosition = 0;
		private int currentThreadPosition = 0;
		public int getCurrentPosition() {
			return currentPosition;
		}
		public void setCurrentPosition(int currentPosition) {
			this.currentPosition = currentPosition;
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
			return "\tstart position: " + this.currentPosition + "\n\tend position: "+this.endPosition+"\n\t thread position: "+this.currentThreadPosition;
		}
	}
	
	public String toString(){
		StringBuilder msg = new StringBuilder();
		msg.append("target file: "+this.target_file);
		msg.append("\nlocal file: "+this.local_file);
		for(EveryThreadToDownloadObj o:this.list){
			msg.append(o.toString());
		}
		return msg.toString();
	}
}
