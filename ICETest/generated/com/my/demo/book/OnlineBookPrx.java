// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.2
//
// <auto-generated>
//
// Generated from file `service.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.my.demo.book;

public interface OnlineBookPrx extends Ice.ObjectPrx
{
    public Message bookTick(Message msg);

    public Message bookTick(Message msg, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_bookTick(Message msg);

    public Ice.AsyncResult begin_bookTick(Message msg, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_bookTick(Message msg, Ice.Callback __cb);

    public Ice.AsyncResult begin_bookTick(Message msg, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_bookTick(Message msg, Callback_OnlineBook_bookTick __cb);

    public Ice.AsyncResult begin_bookTick(Message msg, java.util.Map<String, String> __ctx, Callback_OnlineBook_bookTick __cb);

    public Ice.AsyncResult begin_bookTick(Message msg, 
                                          IceInternal.Functional_GenericCallback1<Message> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_bookTick(Message msg, 
                                          IceInternal.Functional_GenericCallback1<Message> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_bookTick(Message msg, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<Message> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_bookTick(Message msg, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<Message> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Message end_bookTick(Ice.AsyncResult __result);
}
