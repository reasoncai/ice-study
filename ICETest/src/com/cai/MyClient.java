package com.cai;


import com.my.demo.demo.MyservicePrx;
import com.my.demo.demo.MyservicePrxHelper;


public class MyClient {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			//��ʼ��ͨ����
			ic = Ice.Util.initialize(args);
			//����Զ�̷���Ԫ�����ơ�����Э�顢IP���˿ڣ�����һ��Proxy����
			Ice.ObjectPrx base = ic.stringToProxy("MyService:default -h 10.10.12.90 -p 8888");
			//ͨ��checkedCast����ת�ͣ���ȡMyService�ӿڵ�Զ�̣���ͬʱ�����ݴ��������
			//��ȡ����Ԫ�Ƿ�Ϊ����ӿ�
			MyservicePrx prxy = MyservicePrxHelper.checkedCast(base);
			if(prxy == null){
				throw new Error("Invalid Proxy");
			}
			//���÷��񷽷�
			String rt = prxy.hellow();
			System.out.println(rt);
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if(ic != null){
				ic.destroy();
			}
		}
	
	}
}
