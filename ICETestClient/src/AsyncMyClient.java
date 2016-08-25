import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;
import com.my.demo.book.OnlineBookPrxHelper;

import Ice.AsyncResult;

public class AsyncMyClient {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			//初始化通信器
			ic = Ice.Util.initialize(args);
			//传入远程服务单元的名称、网络协议、IP及端口，构造一个Proxy对象
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook:default -p 10001");
			//通过checkedCast向下转型，获取MyService接口的远程，并同时检测根据传入的名称
			//获取服务单元是否为代理接口
			OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			if(prxy == null){
				throw new Error("Invalid Proxy");
			}
			Message msg = new Message();
			msg.name = "cai";
			msg.price = 33;
			msg.type = 3;
			msg.valid = true;
			msg.content = "hellow";
			//异步调用服务方法
			AsyncResult result = prxy.begin_bookTick(msg);
			while(true){
				System.out.println("is call request send: "+ result.isSent());
				//调用完成
				if(result.isCompleted()){
					System.out.println("call finished,return msg:"+ prxy.end_bookTick(result).toString());
					break;
					}else{
						System.out.println("wait for finished");
						Thread.sleep(100);
					}
				}
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

