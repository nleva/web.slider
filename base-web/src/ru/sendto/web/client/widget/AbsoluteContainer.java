package ru.sendto.web.client.widget;

import com.google.gwt.user.client.DOM;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;

public class AbsoluteContainer extends ResizeContainer {
	public AbsoluteContainer() {
		setElement(DOM.createDiv());
		getElement().applyStyles("position: absolute;");
	}

	@Override
	protected void doLayout() {
		//applyLayout(widget, width, height)
		
	}
}
