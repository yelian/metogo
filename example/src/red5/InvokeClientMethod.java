package red5;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.service.IServiceCapableConnection;

public class InvokeClientMethod extends ApplicationAdapter {

	@Override
	public boolean appConnect(IConnection arg0, Object[] arg1) {
		callClient(arg0);
		return true;
	}

	private void callClient(IConnection conn) {
		if (conn instanceof IServiceCapableConnection) {
			IServiceCapableConnection sc = (IServiceCapableConnection) conn;
			sc.invoke("clientMethod", new Object[] { "One", 1 }, new IPendingServiceCallback() {
				
				@Override
				public void resultReceived(IPendingServiceCall arg0) {
					System.out.println(arg0.getServiceName() + ":" + arg0.getServiceMethodName() + ":" + arg0.isSuccess());
				}
			});
		}
	}
}
