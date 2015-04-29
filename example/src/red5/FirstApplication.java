package red5;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;

public class FirstApplication extends ApplicationAdapter{

	@Override
	public boolean appConnect(IConnection arg0, Object[] arg1) {
		super.appConnect(arg0, arg1);
		System.out.println("connect");
		return true;
	}
	
	@Override
	public boolean appStart(IScope arg0) {
		super.appStart(arg0);
		System.out.println("start");
		return true;
	}
	
	public String change(String str){
		return str.toUpperCase();
	}
}
