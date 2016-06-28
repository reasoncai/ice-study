package com.cai.service;

import com.my.demo.book.Message;
import com.my.demo.book._OnlineBookDisp;

import Ice.Communicator;
import Ice.Current;
import IceBox.Service;

public class OnlineBookI2 extends _OnlineBookDisp implements Service{

	@Override
	public Message bookTick(Message msg, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start(String arg0, Communicator arg1, String[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
