package com.cai.service;

import com.my.demo.message._SMSServiceDisp;

import Ice.Communicator;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import IceBox.Service;

public class SMSServiceI2 extends _SMSServiceDisp implements Service {
	private ObjectAdapter _adapter;
	private Logger log;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void sendSMS(String msg, Current __current) {
		System.out.println("send msag" + msg);
	}

	@Override
	public void start(String name, Communicator communicator, String[] args) {
		this.log = communicator.getLogger().cloneWithPrefix(name);
		// 创建ObjectAdapter,这里和service同名
		_adapter = communicator.createObjectAdapter(name);
		// 创建servant并激活
		Ice.Object object = this;
		_adapter.add(object, communicator.stringToIdentity(name));
		_adapter.activate();
		log.trace(" control ", " stoped ");
	}

	@Override
	public void stop() {
		log.trace(" control ", " stoped ");
		_adapter.destroy();
	}

}
