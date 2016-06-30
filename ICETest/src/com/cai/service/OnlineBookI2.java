package com.cai.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.my.demo.book.Message;
import com.my.demo.book._OnlineBookDisp;

import Ice.Communicator;
import Ice.Current;
import Ice.ObjectAdapter;
import IceBox.Service;

public class OnlineBookI2 extends _OnlineBookDisp implements Service{
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(OnlineBookI2.class);
	private ObjectAdapter _adapter;
	private static final long serialVersionUID = 1L;

	@Override
	public Message bookTick(Message msg, Current __current) {
		logger.info("bookTick to call." + logger.getClass().getName());
		logger.debug("bookTick called" + msg.content);
		return msg;
		
	}

	@Override
	public void start(String name, Communicator communicator, String[] args) {
		//创建ObjectAdapter,这里和service同名
		_adapter = communicator.createObjectAdapter(name);
		//创建servant并激活
		Ice.Object object = this;
		_adapter.add(object, communicator.stringToIdentity(name));
		_adapter.activate();
		logger.info(name + " started ");
	}

	@Override
	public void stop() {
		logger.info(this._adapter.getName(), " stoped");
		_adapter.destroy();
	}



}
