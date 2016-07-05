package com.cai;

import com.cai.service.MyserviceImpl;


public class MyServerStarter {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			//��ʼ��Communicator����args���Դ�һЩ��ʼ���Ĳ����������ӳ�ʱ����ʼ���ͻ������ӳص�������
			ic = Ice.Util.initialize(args);
			//������ΪMyServiceAdapter��ObjectAdapter,ʹ��ȱʡ��ͨ��Э��TCP/IP�˿�Ϊ10000������
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("MyServiceAdapter", "default -p 8888");
			//ʵ����һ��MyService�������(Servant)
			MyserviceImpl servant = new MyserviceImpl();
			//��Servant���ӵ�ObjecrtAdapter�У�����Servant������IDΪMyService��Ice Object
			adapter.add(servant, Ice.Util.stringToIdentity("MyService"));
			//����ObjecrtAdapter
			adapter.activate();
			//�÷������˳�֮ǰ��һֱ����������ļ���
			System.out.println("server started");
			ic.waitForShutdown();
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
