package ru.sendto.web.client.widget;

import ru.sendto.web.client.utils.ClientVersionEnum;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;

public class Slide extends HtmlLayoutContainer implements HasClickHandlers{

	/**
	 * @author lev.nadeinsky
	 *
	 */
	
	private int x;
	private int y;
	
	static ClientVersionEnum version = ClientVersionEnum.getVersion();
	/*
	 *
	 */
	private String baseStyle = "x-slide";
	
	public Slide(){
		super("");
		setStyleName(baseStyle);
	}
	
	public void setPosition(String xy) {
		setAnimationDuration(0);
		xy = xy.replaceAll("[^0-9 -]", "");
		xy = xy.trim();
		String args[] = xy.split(" ");
		if(args.length!=2)
			return;
		setX(Integer.parseInt(args[0]));
		setY(Integer.parseInt(args[1]));
		super.setPagePosition(x, y);
	}
	
	public void setAnimationDuration(float sec){
		Button b = new Button();
		getElement().applyStyles(version.toString()+"transition: all "+sec+"s ease-in-out");
		
	}
	
	public void setMinimize(boolean min){
		if(min)
			addStyleName("scale10");
		else
			removeStyleName("scale10");
		
	}
	public void setScale(float scale){
		getElement().applyStyles(version.toString()+"transform: scale("+scale+");");
	}
	
	
	@Override
	public void setWidth(String width) {
		// TODO Auto-generated method stub
		super.setWidth(width);
	}
	@Override
	public void setHeight(String height) {
		// TODO Auto-generated method stub
		super.setHeight(height);
	}
	ClickHandler handler;
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
