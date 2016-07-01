import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;
import com.my.demo.book.OnlineBookPrxHelper;
import com.my.demo.message.SMSServicePrx;
import com.my.demo.message.SMSServicePrxHelper;

public class MyIceCilentGrid {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			String[] initParams = new String[]{"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
			//初始化通信器
			ic = Ice.Util.initialize(initParams);
			//传入远程服务单元的名称、网络协议、IP及端口，构造一个Proxy对象
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook");
			//通过checkedCast向下转型，获取MyService接口的远程，并同时检测根据传入的名称
			OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			//获取服务单元是否为代理接口
			//OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			if(prxy == null){
				throw new Error("Invalid Proxy");
			}
			Message msg = new Message();
			msg.name = "ddde";
			msg.price = 33;
			msg.type = 3;
			msg.valid = true;
			msg.content = "hellow";
			//调用服务方法
			Message message = prxy.bookTick(msg);
			System.out.println(message.toString());
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
