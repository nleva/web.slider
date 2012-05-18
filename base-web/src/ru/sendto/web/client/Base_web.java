package ru.sendto.web.client;

import java.util.HashMap;
import java.util.Map;

import ru.sendto.web.client.view.MainView;
import ru.sendto.web.client.widget.Slide;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;
import com.xedge.jquery.client.js.JSHelper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Base_web implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	Slide sl = new Slide();
	
	public void onModuleLoad() {
		RootPanel.get().clear();
		sl.setId("slide1");
		sl.setPagePosition(100, 100);
		RootPanel.get().add(new MainView());
		//sl.setAnimationDuration(1);
		Button b =new Button("Hello");
		sl.add(b);
		sl.addDomHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log("mouse ovser");
				//mouse
				//sl.setPagePosition(0, 0);
				//sl.setScale(0.5f);
				Map<String,String> props = new HashMap<String, String>();
				props.put("left", "+=100");
				
				JQuery.select(sl.getElement()).animate(props, 100);
				
				
			}
		
		}, ClickEvent.getType());

		b.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log("mouse ovser");
				
			}
		});
	}
}
