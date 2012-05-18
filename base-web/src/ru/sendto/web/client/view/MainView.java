package ru.sendto.web.client.view;

import java.util.HashMap;
import java.util.Map;

import ru.sendto.web.client.utils.FormUtils;
import ru.sendto.web.client.widget.AbsoluteContainer;
import ru.sendto.web.client.widget.Slide;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.ResizeEndEvent;
import com.xedge.jquery.client.JQuery;

public class MainView extends Composite implements HasText {

	private static MailViewUiBinder uiBinder = GWT
			.create(MailViewUiBinder.class);

	interface MailViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public Slide active;

	@UiField
	public Viewport viewport;
	@UiField
	AbsoluteContainer slider;
	@UiField
	Slide welcome;
	@UiField
	Slide contacts;
	@UiField
	Slide about;
	@UiField
	Slide projects;
	@UiField
	Slide services;
	@UiField
	Slide products;

	Map<String, String> props = new HashMap<String, String>();

	public void setActive(Slide s) {
		active = s;
		resizer(null);
	}

	private void setActiveByUrl(){
		if (Window.Location.getHref().contains("#/welcome/"))
			setActive(welcome);
		else if (Window.Location.getHref().contains("#/contacts/"))
			setActive(contacts);
		else if (Window.Location.getHref().contains("#/about/"))
			setActive(about);
		else if (Window.Location.getHref().contains("#/projects/"))
			setActive(projects);
		else if (Window.Location.getHref().contains("#/services/"))
			setActive(services);
		else if (Window.Location.getHref().contains("#/products/"))
			setActive(products);
		else {
			setActive(welcome);
		}
	}
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));

		FormUtils.setSlideWidth(welcome.getElement().getWidth(false));
		FormUtils.setSlideHeight(welcome.getElement().getHeight(false));

		setActiveByUrl();

		Anchor a = new Anchor("go to contacts", "#/contacts/");
		welcome.add(a);
		welcome.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				GWT.log(Window.Location.getPath());
				Window.Location.replace(Window.Location.createUrlBuilder()
						.setPath(Window.Location.getPath().replace("#/.+/", "#/"+getUrlBySlide((Slide)event.getSource())+"/")).buildString());



			}
		}, ClickEvent.getType());
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				GWT.log("history=" + event.toDebugString());
				setActiveByUrl();
			}
		});
	}

	public MainView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public String getText() {
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
	}

	// @UiHandler("viewport")
	void onResize(ResizeEndEvent re) {
		// Integer h = Window.getClientHeight();
		// Integer w = Window.getClientWidth();
		// props.put("left",
		// ((Integer)((h+welcome.getElement().getWidth(false))/2)).toString());
	}

	@UiHandler({"welcome","contacts","about","projects","services","products"})
	void onContactsClicked(ClickEvent ce) {
		Slide s = (Slide) ce.getSource();
		String str = getUrlBySlide(s);
		//Window.Location.
		//setActive(s);
		History.newItem("/"+str+"/");
		//props.put("left", "-=" + 940);
		//props.put("left", "-=" + 940);

		//JQuery.select(slider.getElement()).animate(props, 200);

	}

	private String getUrlBySlide(Slide s){
		if(s.equals(welcome))
			return "welcome";
		else if(s.equals(contacts))
			return "contacts";
		else if(s.equals(about))
			return "about";
		else if(s.equals(projects))
			return "projects";
		else if(s.equals(services))
			return "services";
		else if(s.equals(products))
			return "products";
		else
			return null;
	}
	
	private boolean first = true;

	@UiHandler("viewport")
	public void resizer(ResizeEvent re) {
		if(first){
			first = false;
			return;
		}
		int delta;
		float scale = 1;
		if ((delta = (Window.getClientHeight() - active.getElement().getHeight(
				true))) >= 0) {
			delta /= 2;
			props.put("top", "" + (delta + 10 - active.getY()));
		} else {
			scale = ((float) Window.getClientHeight())
					/ ((float) active.getElement().getHeight(true));
		}
		if ((delta = (Window.getClientWidth() - active.getElement().getWidth(
				true))) >= 0) {
			delta /= 2;
			props.put("left", "" + (delta + 10 - active.getX()));
		} else {
			scale = Math.min(scale, ((float) Window.getClientHeight())
					/ ((float) active.getElement().getHeight(true)));
		}
		// TODO scale;
		GWT.log("left=" + props.get("left") + " top=" + props.get("top"));
		JQuery.select(slider.getElement()).animate(props, first ? 0 : 200);
		props.remove("left");
		props.remove("top");
		first = false;
	}

	@Override
	protected void onLoad() {

		super.onLoad();
	}

}
