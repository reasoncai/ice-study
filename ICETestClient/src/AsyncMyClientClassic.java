import com.my.demo.book.Callback_OnlineBook_bookTick;
import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;
import com.my.demo.book.OnlineBookPrxHelper;

import Ice.AsyncResult;
import Ice.LocalException;

public class AsyncMyClientClassic {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			//��ʼ��ͨ����
			ic = Ice.Util.initialize(args);
			//����Զ�̷���Ԫ�����ơ�����Э�顢IP���˿ڣ�����һ��Proxy����
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook:default -p 10001");
			//ͨ��checkedCast����ת�ͣ���ȡMyService�ӿڵ�Զ�̣���ͬʱ�����ݴ��������
			//��ȡ����Ԫ�Ƿ�Ϊ����ӿ�
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
			//�첽���÷��񷽷�
			prxy.begin_bookTick(msg, new Callback_OnlineBook_bookTick() {
				
				@Override
				public void exception(LocalException arg0) {
					System.out.println("caught exception "+ arg0);
				}
				
				@Override
				public void response(Message msg) {
					System.out.println(msg.toString());
				}
				
			
			});
			
			System.out.println("fucker");
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
