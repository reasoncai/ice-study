package com.cai.service;

import com.my.demo.demo._MyserviceDisp;

import Ice.Current;

public class MyserviceImpl extends _MyserviceDisp{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8420219643960145425L;

	@Override
	public String hellow(Current __current) {
		return "Hello Worldfsdafdsfdd!";
	}

}
