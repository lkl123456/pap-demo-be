package com.yonyou.base.support.excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 导出基础类
* @Description:  
* @author: lkl 
* @date: 2019年5月14日 上午9:55:11
 */
public class BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	private String code;
	private String content;
	private Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public BaseVo(String id, String name, String code,String content, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.content = content;
		this.date = date;
	}
	public BaseVo() {
		super();
	}
		
}
