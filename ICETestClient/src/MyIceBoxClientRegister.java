import com.my.demo.message.SMSServicePrx;
import com.my.demo.message.SMSServicePrxHelper;

public class MyIceBoxClientRegister {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			String[] initParams = new String[]{"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
			//初始化通信器
			ic = Ice.Util.initialize(initParams);
			//传入远程服务单元的名称、网络协议、IP及端口，构造一个Proxy对象
			Ice.ObjectPrx base = ic.stringToProxy("SMSService@SMSServicesAdapter");
			//通过checkedCast向下转型，获取MyService接口的远程，并同时检测根据传入的名称
			SMSServicePrx prxy = SMSServicePrxHelper.checkedCast(base);
			//获取服务单元是否为代理接口
			//OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			if(prxy == null){
				throw new Error("Invalid Proxy");
			}
			//调用服务方法
			prxy.sendSMS("book2");
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if(ic != null){
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
