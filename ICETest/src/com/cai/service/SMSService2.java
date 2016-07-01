package com.cai.service;

import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;
import com.my.demo.book.OnlineBookPrxHelper;
import com.my.demo.message._SMSServiceDisp;

import Ice.Communicator;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import Ice.ObjectPrx;
import IceBox.Service;

public class SMSService2 extends _SMSServiceDisp implements Service{
	private ObjectAdapter _adapter;
	private Logger log;

	@Override
	public void sendSMS(String msg, Current __current) {
		if(msg.startsWith("book")){
			//ObjectPrx base = _adapter.getCommunicator().stringToProxy("OnlineBook:default -p 10001");
			ObjectPrx base = _adapter.getCommunicator().stringToProxy("OnlineBook@OnlineBookAdapter");
			OnlineBookPrx prxy = OnlineBookPrxHelper.checkedCast(base);
			Message msg2 = new Message();
			msg2.name = "cai";
			msg2.price = 33;
			msg2.type = 3;
			msg2.valid = true;
			msg2.content = "hellow";
			//调用服务方法
			Message bookTick = prxy.bookTick(msg2);
			System.out.println(bookTick.toString());
		}
	}

	@Override
	public void start(String name, Communicator communicator, String[] arg) {
		this.log = communicator.getLogger().cloneWithPrefix(name);
		// 创建ObjectAdapter,这里和service同名
		_adapter = communicator.createObjectAdapter(name);
		// 创建servant并激活
		Ice.Object object = this;
		_adapter.add(object, communicator.stringToIdentity(name));
		_adapter.activate();
		log.trace(" control ", " started ");
	}

	@Override
	public void stop() {
		log.trace(" control ", " stoped ");
		_adapter.destroy();
		
	}

}
