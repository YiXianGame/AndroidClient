package com.yixian.make.Event.ReadyEvent;

import java.util.Vector;

public class ReadyEvent {
    Vector<ReadyDelegate> listeners= new Vector<>();

    public void register(ReadyDelegate delegate){
        synchronized (listeners){
            listeners.add(delegate);
        }
    }
    public void unRegister(ReadyDelegate delegate){
        synchronized (listeners){
            listeners.remove(delegate);
        }
    }
    public void OnEvent(Object sender, Object args){
        synchronized (listeners){
            for (ReadyDelegate delegate:listeners) {
                delegate.MatchSuccessEvent(sender,args);
            }
        }
    }
}
