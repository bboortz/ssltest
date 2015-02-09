package eu.boortz.ssltest.webapp;

import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import eu.boortz.ssltest.testclient.Log;
import eu.boortz.ssltest.testclient.clients.settings.CustomSettings;
import eu.boortz.ssltest.testclient.factory.SSLContextFactory;
import eu.boortz.ssltest.testclient.tester.ITester;
import eu.boortz.ssltest.testclient.tester.TestSSLCustomCiphersAndCustomTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLCustomCiphersAndDefaultTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaultCipherAndCustomTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaultCipherAndDefaultTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaults;
import eu.boortz.ssltest.testclient.tester.TestSSLSecure;
import eu.boortz.ssltest.testclient.tester.TestSSLWeak;

/* 
 * UI class is the starting point for your app. You may deploy it with VaadinServlet
 * or VaadinPortlet by giving your UI class name a parameter. When you browse to your
 * app a web page showing your UI is automatically generated. Or you may choose to 
 * embed your UI to an existing web page. 
 */
@Title("SSL Test")
@Theme("valo")
public class SSLTestUI extends UI {

	private static final long serialVersionUID = 2172681589163533254L;
	
	private static final String DEFAULT_URI = "https://localhost:9443";

	/* User interface components for main site. */
	private TabSheet mainTabSheet = new TabSheet();
	
	private VerticalLayout layoutMainTab1 = new VerticalLayout();
	private VerticalLayout layoutMainTab2 = new VerticalLayout();
	
	private FormLayout outputLayoutMainTab1 = new FormLayout();
	
	private FormLayout inputLayoutMainTab2 = new FormLayout();
	
	private TextField defaultProtocolsTextFieldOutputLayoutMainTab1 = new TextField("DEFAULT PROTOCOLS");
	private TextArea defaultCiphersTextAreaOutputLayoutMainTab1 = new TextArea("DEFAULT CIHPERS");
	private CheckBox wantClientAuthTextFieldOutputLayoutMainTab1 = new CheckBox("Want Client Auth", false);
	private CheckBox needClientAuthTextFieldOutputLayoutMainTab1 = new CheckBox("Need Client Auth", false);
	private TextField customProtocolsTextFieldOutputLayoutMainTab1 = new TextField("CUSTOM PROTOCOLS");
	private TextArea customCiphersTextAreaOutputLayoutMainTab1 = new TextArea("CUSTOM CIHPERS");
	
	private TextField uriTextFieldInputLayoutMainTab2 = new TextField("URI");
	private OptionGroup optionGroupInputLayoutMainTab2 = new OptionGroup("SSL TEST OPTION");
	private Button sendButtonInputLayoutMainTab2 = new Button("Check URI");
	
	
	/* UI for host window */
    private FormLayout outputLayoutHostWindow = new FormLayout();
    
    private TextField uriTextFieldOutputLayoutHostWindow = new TextField("URI");
    private CheckBox reachableCheckBoxOutputLayoutHostWindow = new CheckBox("REACHABLE", false);
    private TextField statusTextFieldOutputLayoutHostWindow = new TextField("HTTP STATUS");
    private TextField sslProtocolsTextFieldOutputLayoutHostWindow = new TextField("SSL PROTOCOLS");
    private TextArea sslCiphersTextAreaOutputLayoutHostWindow = new TextArea("SSL CIPHERS");
    private TextField sslStrengthTextFieldOutputLayoutHostWindow = new TextField("SSL STRENGTH");
	
	
	private ArrayList<SSLTestOptionObj> sslTestOptionObjList = new ArrayList<SSLTestOptionObj>();

	{
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Defaults", new TestSSLDefaults()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Secure", new TestSSLSecure()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Weak", new TestSSLWeak()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Default Cipher and Default TrustChain", new TestSSLDefaultCipherAndDefaultTrustChain()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Default Cipher and Custom TrustChain", 	new TestSSLDefaultCipherAndCustomTrustChain()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Custom Cipher and Default TrustChain", 	new TestSSLCustomCiphersAndDefaultTrustChain()) );
		sslTestOptionObjList.add( new SSLTestOptionObj("SSL Custom Cipher and Custom TrustChain", 	new TestSSLCustomCiphersAndCustomTrustChain()) );
		
		int i = 1;
		for (SSLTestOptionObj obj : sslTestOptionObjList) {
			optionGroupInputLayoutMainTab2.addItem(i);
			optionGroupInputLayoutMainTab2.setItemCaption(i, obj.getName());
			i++;
		}
		
	}


	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	protected void init(VaadinRequest request) {
		initLayout();
		initTab1Data();
		initTab2Data();
		initButtons();
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {

		// main Tabsheet
		setContent(mainTabSheet);
		mainTabSheet.setHeight(100.0f, Unit.PERCENTAGE);
		mainTabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		mainTabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

		
		// main tabs
		layoutMainTab1.setMargin(true);
		layoutMainTab2.setMargin(true);

		mainTabSheet.addTab(layoutMainTab1, "Client Info");
		mainTabSheet.addTab(layoutMainTab2, "Server Info");

		// main tab 1
		layoutMainTab1.addComponent(outputLayoutMainTab1);
		
		defaultProtocolsTextFieldOutputLayoutMainTab1.setWidth("100%");
		defaultCiphersTextAreaOutputLayoutMainTab1.setWidth("100%");
		customProtocolsTextFieldOutputLayoutMainTab1.setWidth("100%");
		customCiphersTextAreaOutputLayoutMainTab1.setWidth("100%");
		
		wantClientAuthTextFieldOutputLayoutMainTab1.setEnabled(false);
		needClientAuthTextFieldOutputLayoutMainTab1.setEnabled(false);
		
		
		outputLayoutMainTab1.addComponent(defaultProtocolsTextFieldOutputLayoutMainTab1);
		outputLayoutMainTab1.addComponent(defaultCiphersTextAreaOutputLayoutMainTab1);
		outputLayoutMainTab1.addComponent(wantClientAuthTextFieldOutputLayoutMainTab1);
		outputLayoutMainTab1.addComponent(needClientAuthTextFieldOutputLayoutMainTab1);
		outputLayoutMainTab1.addComponent(customProtocolsTextFieldOutputLayoutMainTab1);
		outputLayoutMainTab1.addComponent(customCiphersTextAreaOutputLayoutMainTab1);
		
		// main tab 2
		layoutMainTab2.addComponent(inputLayoutMainTab2);
		
		uriTextFieldInputLayoutMainTab2.setWidth("100%");
		
		inputLayoutMainTab2.addComponent(uriTextFieldInputLayoutMainTab2);
		inputLayoutMainTab2.addComponent(optionGroupInputLayoutMainTab2);
		inputLayoutMainTab2.addComponent(sendButtonInputLayoutMainTab2);
	}


	private void initTab1Data() {
		String resultArr[] = null;
		StringBuilder sb = null;
		SSLContext sslContext = SSLContextFactory.newInstance();
		
		// retrieve client default protocols
		resultArr = sslContext.getDefaultSSLParameters().getProtocols();
		defaultProtocolsTextFieldOutputLayoutMainTab1.setValue( Arrays.toString(resultArr) );

		
		// retrieve client default ciphers
		resultArr = sslContext.getDefaultSSLParameters().getCipherSuites();
		sb = new StringBuilder();
		for (String item : resultArr) {
			sb.append(item + "\n");
		}
		defaultCiphersTextAreaOutputLayoutMainTab1.setValue( sb.toString() );
		
		
		// retrieve client auth options
		boolean want = sslContext.getDefaultSSLParameters().getWantClientAuth();
        boolean need = sslContext.getDefaultSSLParameters().getNeedClientAuth();
		wantClientAuthTextFieldOutputLayoutMainTab1.setValue(want);
		needClientAuthTextFieldOutputLayoutMainTab1.setValue(need);
		
		
		// retrieve client custom protocols
		resultArr = CustomSettings.SSL_PROTOCOLS;
		customProtocolsTextFieldOutputLayoutMainTab1.setValue( Arrays.toString(resultArr) );
		
		
		// retrieve client custom ciphers
		resultArr = CustomSettings.SSL_CIPHERS;
		sb = new StringBuilder();
		for (String item : resultArr) {
			sb.append(item + "\n");
		}
		customCiphersTextAreaOutputLayoutMainTab1.setValue( sb.toString() );
				
		
		

	}
	
	private void initTab2Data() {
		uriTextFieldInputLayoutMainTab2.setValue(DEFAULT_URI);
		
		optionGroupInputLayoutMainTab2.select(1);
	}

	private void initButtons() {
		
		sendButtonInputLayoutMainTab2.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3453483238129348595L;

			public void buttonClick(ClickEvent event) {
				ITester tester = new TestSSLDefaults();
				String uri = uriTextFieldInputLayoutMainTab2.getValue();
				
				if (uri == null || uri.length() == 0 || ! uri.startsWith("https")) {
					return;
				}
				
				
				showWindow();
				uriTextFieldOutputLayoutHostWindow.setValue(uri);
				
				
				// retrieve ssl test option
				int optionNr = Integer.valueOf( (Integer) optionGroupInputLayoutMainTab2.getValue() );
				optionNr--;
				tester = sslTestOptionObjList.get(optionNr).getCls();
				
				
				Log.logInfo("check uri: " + uri);
				Log.logInfo("ssl test option: " + sslTestOptionObjList.get(optionNr).getName());
				
			
				// pretest
				try {
					tester.testURI(uri);
				} catch (Exception e1) {
					statusTextFieldOutputLayoutHostWindow.setValue(e1.getMessage());
					reachableCheckBoxOutputLayoutHostWindow.setValue(false);
					Log.logSevere(e1.getMessage());
					return;
				}
				reachableCheckBoxOutputLayoutHostWindow.setValue(true);
				
				
				
				try {	
					String[] result = null;
					
					// get ssl protocols
					result = tester.getSupportedSSLProtocols(uri);;
					String sslProtocols = Arrays.toString(result);
					sslProtocolsTextFieldOutputLayoutHostWindow.setValue(sslProtocols);
					
					// get ssl ciphers
					result = tester.getSupportedSSLCiphers(uri);;
					StringBuilder sb = new StringBuilder();
					for (String protocol : result) {
						sb.append(protocol + "\n");
					}
					String sslCiphers = sb.toString();
					sslCiphersTextAreaOutputLayoutHostWindow.setValue(sslCiphers);
					
				} catch (Exception e2) {
					Log.logSevere(e2.getMessage());
					e2.printStackTrace();
				}
			}
		});


	}
	
	private void showWindow() {
		Window hostWindow = new Window("Host Window");
	     
	     hostWindow.setWidth("60%");
	     hostWindow.setContent(outputLayoutHostWindow);
	     
	     uriTextFieldOutputLayoutHostWindow.setWidth("100%");
	     statusTextFieldOutputLayoutHostWindow.setWidth("100%");
	     sslProtocolsTextFieldOutputLayoutHostWindow.setWidth("100%");
	     sslCiphersTextAreaOutputLayoutHostWindow.setWidth("100%");
	     sslStrengthTextFieldOutputLayoutHostWindow.setWidth("100%");
	     
	     uriTextFieldOutputLayoutHostWindow.setValue("");
	     reachableCheckBoxOutputLayoutHostWindow.setValue(false);
	     reachableCheckBoxOutputLayoutHostWindow.setEnabled(false);
	     statusTextFieldOutputLayoutHostWindow.setValue("");
	     sslProtocolsTextFieldOutputLayoutHostWindow.setValue("");
	     sslCiphersTextAreaOutputLayoutHostWindow.setValue("");
	     sslStrengthTextFieldOutputLayoutHostWindow.setValue("");
	     
	     outputLayoutHostWindow.addComponent(uriTextFieldOutputLayoutHostWindow);
	     outputLayoutHostWindow.addComponent(reachableCheckBoxOutputLayoutHostWindow);
	     outputLayoutHostWindow.addComponent(statusTextFieldOutputLayoutHostWindow);
	     outputLayoutHostWindow.addComponent(sslProtocolsTextFieldOutputLayoutHostWindow);
	     outputLayoutHostWindow.addComponent(sslCiphersTextAreaOutputLayoutHostWindow);
	     outputLayoutHostWindow.addComponent(sslStrengthTextFieldOutputLayoutHostWindow);
	     
	     UI.getCurrent().addWindow(hostWindow);
	}

}
