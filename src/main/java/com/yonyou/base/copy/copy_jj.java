package com.yonyou.base.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class copy_jj {
	public void copy(List<BtdBusfiled> btdBusfiledList){
		/*浅拷贝*/
		List<BtdBusfiled> btdBusfiledDestList=new ArrayList<>(btdBusfiledList);
		
		
		/*深拷贝*/
		List<BtdBusfiled> new_btdBusfiledList = new ArrayList<>();
	    CollectionUtils.addAll(new_btdBusfiledList, new BtdBusfiled[btdBusfiledList.size()]);
	    Collections.copy(new_btdBusfiledList, btdBusfiledList);
	}
	
}
