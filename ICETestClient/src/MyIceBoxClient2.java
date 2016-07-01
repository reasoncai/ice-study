import com.my.demo.message.SMSServicePrx;
import com.my.demo.message.SMSServicePrxHelper;

public class MyIceBoxClient2 {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			//��ʼ��ͨ����
			ic = Ice.Util.initialize(args);
			//����Զ�̷���Ԫ�����ơ�����Э�顢IP���˿ڣ�����һ��Proxy����
			Ice.ObjectPrx base = ic.stringToProxy("SMSService:default -p 10002");
			//ͨ��checkedCast����ת�ͣ���ȡMyService�ӿڵ�Զ�̣���ͬʱ�����ݴ��������
			SMSServicePrx prxy = SMSServicePrxHelper.checkedCast(base);
			//��ȡ����Ԫ�Ƿ�Ϊ����ӿ�
			//OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			if(prxy == null){
				throw new Error("Invalid Proxy");
			}
			//���÷��񷽷�
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
