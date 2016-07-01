import com.my.demo.message.SMSServicePrx;
import com.my.demo.message.SMSServicePrxHelper;

public class MyIceBoxClientRegister {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			String[] initParams = new String[]{"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
			//��ʼ��ͨ����
			ic = Ice.Util.initialize(initParams);
			//����Զ�̷���Ԫ�����ơ�����Э�顢IP���˿ڣ�����һ��Proxy����
			Ice.ObjectPrx base = ic.stringToProxy("SMSService@SMSServicesAdapter");
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
