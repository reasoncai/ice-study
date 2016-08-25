import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;
import com.my.demo.book.OnlineBookPrxHelper;

import Ice.AsyncResult;

public class AsyncMyClient {
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
			AsyncResult result = prxy.begin_bookTick(msg);
			while(true){
				System.out.println("is call request send: "+ result.isSent());
				//�������
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

