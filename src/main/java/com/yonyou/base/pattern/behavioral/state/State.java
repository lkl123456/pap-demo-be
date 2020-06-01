package com.yonyou.base.pattern.behavioral.state;

public interface State {
	
    public void switchOn(Switcher switcher);//开
    
    public void switchOff(Switcher switcher);//关
}
