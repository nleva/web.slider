package ru.sendto.web.client.utils;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.GXT;

public enum ClientVersionEnum {
	WEBKIT("-webkit-"),
	MOZ("-moz-"),
	MS("-ms-"),
	O("-o-"),
	UNSUPPORTED("");
	
	String cssText;
	
	ClientVersionEnum(String cssText){
		this.cssText=cssText;
	}
	
	static public ClientVersionEnum getVersion(){
		GWT.log(GWT.getVersion()+"\n"+GXT.getVersion());
		
		if(GXT.isWebKit())
			return WEBKIT;
		if(GXT.isOpera())
			return O;
		if(GXT.isIE())
			return MS;
		
		return UNSUPPORTED;
		
	}
	@Override
	public String toString() {
		return cssText;
	}
}
