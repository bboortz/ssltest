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

import eu.boortz.ssltest.lib.Log;
import eu.boortz.ssltest.lib.clients.settings.DefaultSettings;
import eu.boortz.ssltest.lib.clients.settings.MediumSettings;
import eu.boortz.ssltest.lib.factory.SSLContextFactory;
import eu.boortz.ssltest.lib.tester.CustomTester;
import eu.boortz.ssltest.lib.tester.ICustomTester;
import eu.boortz.ssltest.lib.tester.ITester;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaultCipherAndDefaultTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaultCipherAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaults;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLMedium;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLSecure;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLSecureCiphersAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLWeak;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLWeakCiphersAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.DefaultSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.MediumSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.SecureSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.WeakSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.DefaultSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.MediumSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.SecureSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.WeakSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.trustchain.DefaultSSLTrustChain;
import eu.boortz.ssltest.lib.tester.settings.trustchain.MediumSSLTrustChain;
import eu.boortz.ssltest.lib.tester.settings.trustchain.SecureSSLTrustChain;
import eu.boortz.ssltest.lib.tester.settings.trustchain.WeakSSLTrustChain;
import eu.boortz.ssltest.webapp.options.SSLCipherOption;
import eu.boortz.ssltest.webapp.options.SSLProtocolOption;
import eu.boortz.ssltest.webapp.options.SSLTestOptionObj;
import eu.boortz.ssltest.webapp.options.SSLTrustChainOption;

/* 
 * SSL Test as a single test application 
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
	private VerticalLayout layoutMainTab3 = new VerticalLayout();
	
	private FormLayout outputLayoutMainTab1 = new FormLayout();
	
	private FormLayout inputLayoutMainTab2 = new FormLayout();
	
	private FormLayout inputLayoutMainTab3 = new FormLayout();
	
	private TextField defaultProtocolsTextFieldOutputLayoutMainTab1 = new TextField("DEFAULT PROTOCOLS");
	private TextArea defaultCiphersTextAreaOutputLayoutMainTab1 = new TextArea("DEFAULT CIHPERS");
	private CheckBox wantClientAuthTextFieldOutputLayoutMainTab1 = new CheckBox("Want Client Auth", false);
	private CheckBox needClientAuthTextFieldOutputLayoutMainTab1 = new CheckBox("Need Client Auth", false);
	private TextField customProtocolsTextFieldOutputLayoutMainTab1 = new TextField("MEDIUM PROTOCOLS");
	private TextArea customCiphersTextAreaOutputLayoutMainTab1 = new TextArea("MEDIUM CIHPERS");
	
	private TextField uriTextFieldInputLayoutMainTab2 = new TextField("URI");
	private OptionGroup optionGroupInputLayoutMainTab2 = new OptionGroup("SSL TEST OPTIONS");
	private Button sendButtonInputLayoutMainTab2 = new Button("Check URI");
	
	private TextField uriTextFieldInputLayoutMainTab3 = new TextField("URI");
	private OptionGroup sslProtocolsOptionGroupInputLayoutMainTab3 = new OptionGroup("SSL PROTOCOLS");
	private OptionGroup sslCiphersOptionGroupInputLayoutMainTab3 = new OptionGroup("SSL CIPHERS");
	private OptionGroup sslTrustChainOptionGroupInputLayoutMainTab3 = new OptionGroup("SSL TRUSTCHAIN");
	private Button sendButtonInputLayoutMainTab3 = new Button("Check URI");
	
	
	/* UI for host window */
    private FormLayout outputLayoutHostWindow = new FormLayout();
    
    private TextField uriTextFieldOutputLayoutHostWindow = new TextField("URI");
    private CheckBox reachableCheckBoxOutputLayoutHostWindow = new CheckBox("REACHABLE", false);
    private TextField statusTextFieldOutputLayoutHostWindow = new TextField("HTTP STATUS");
    private TextField sslProtocolsTextFieldOutputLayoutHostWindow = new TextField("SSL PROTOCOLS");
    private TextArea sslCiphersTextAreaOutputLayoutHostWindow = new TextArea("SSL CIPHERS");
    private TextField sslStrengthTextFieldOutputLayoutHostWindow = new TextField("SSL STRENGTH");
	
	
	private ArrayList<SSLTestOptionObj> sslTestOptionObjListMainTab2 = new ArrayList<SSLTestOptionObj>();
	private ArrayList<SSLProtocolOption> sslProtocolsOptionObjListMainTab3 = new ArrayList<SSLProtocolOption>();
	private ArrayList<SSLCipherOption> sslCiphersOptionObjListMainTab3 = new ArrayList<SSLCipherOption>();
	private ArrayList<SSLTrustChainOption> sslTrustChainOptionObjListMainTab3 = new ArrayList<SSLTrustChainOption>();

	{
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Defaults", new TestSSLDefaults()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Secure", new TestSSLSecure()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Medium", new TestSSLMedium()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Weak", new TestSSLWeak()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Default Cipher and Default TrustChain", new TestSSLDefaultCipherAndDefaultTrustChain()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Default Cipher and Weak TrustChain", 	new TestSSLDefaultCipherAndWeakTrustChain()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Secure Cipher and Weak TrustChain", 	new TestSSLSecureCiphersAndWeakTrustChain()) );
		sslTestOptionObjListMainTab2.add( new SSLTestOptionObj("SSL Weak Cipher and Weak TrustChain", 	new TestSSLWeakCiphersAndWeakTrustChain()) );
		
		sslProtocolsOptionObjListMainTab3.add( new SSLProtocolOption("SSL Defaults", new DefaultSSLProtocols()) );
		sslProtocolsOptionObjListMainTab3.add( new SSLProtocolOption("Secure", new SecureSSLProtocols()) );
		sslProtocolsOptionObjListMainTab3.add( new SSLProtocolOption("Medium", new MediumSSLProtocols()) );
		sslProtocolsOptionObjListMainTab3.add( new SSLProtocolOption("Weak", new WeakSSLProtocols()) );
		
		sslCiphersOptionObjListMainTab3.add( new SSLCipherOption("SSL Defaults", new DefaultSSLCiphers()) );
		sslCiphersOptionObjListMainTab3.add( new SSLCipherOption("Secure", new SecureSSLCiphers()) );
		sslCiphersOptionObjListMainTab3.add( new SSLCipherOption("Medium", new MediumSSLCiphers()) );
		sslCiphersOptionObjListMainTab3.add( new SSLCipherOption("Weak", new WeakSSLCiphers()) );
		
		sslTrustChainOptionObjListMainTab3.add( new SSLTrustChainOption("SSL Defaults", new DefaultSSLTrustChain()) );
		sslTrustChainOptionObjListMainTab3.add( new SSLTrustChainOption("Secure", new SecureSSLTrustChain()) );
		sslTrustChainOptionObjListMainTab3.add( new SSLTrustChainOption("Medium", new MediumSSLTrustChain()) );
		sslTrustChainOptionObjListMainTab3.add( new SSLTrustChainOption("Weak", new WeakSSLTrustChain()) );
		
		int i; 
		i= 1;
		for (SSLTestOptionObj obj : sslTestOptionObjListMainTab2) {
			optionGroupInputLayoutMainTab2.addItem(i);
			optionGroupInputLayoutMainTab2.setItemCaption(i, obj.getName());
			i++;
		}
		
		i = 1;
		for (SSLProtocolOption obj : sslProtocolsOptionObjListMainTab3) {
			sslProtocolsOptionGroupInputLayoutMainTab3.addItem(i);
			sslProtocolsOptionGroupInputLayoutMainTab3.setItemCaption(i, obj.getName());
			i++;
		}
		
		i = 1;
		for (SSLCipherOption obj : sslCiphersOptionObjListMainTab3) {
			sslCiphersOptionGroupInputLayoutMainTab3.addItem(i);
			sslCiphersOptionGroupInputLayoutMainTab3.setItemCaption(i, obj.getName());
			i++;
		}
		
		i = 1;
		for (SSLTrustChainOption obj : sslTrustChainOptionObjListMainTab3) {
			sslTrustChainOptionGroupInputLayoutMainTab3.addItem(i);
			sslTrustChainOptionGroupInputLayoutMainTab3.setItemCaption(i, obj.getName());
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
		initTab3Data();
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
		layoutMainTab3.setMargin(true);

		mainTabSheet.addTab(layoutMainTab1, "CLIENT INFO");
		mainTabSheet.addTab(layoutMainTab2, "CHECK URI - Predefined");
		mainTabSheet.addTab(layoutMainTab3, "CHECK URI - Custom");

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
		
		
		// main tab 3
		layoutMainTab3.addComponent(inputLayoutMainTab3);
		
		uriTextFieldInputLayoutMainTab3.setWidth("100%");
		
		inputLayoutMainTab3.addComponent(uriTextFieldInputLayoutMainTab3);
		inputLayoutMainTab3.addComponent(sslProtocolsOptionGroupInputLayoutMainTab3);
		inputLayoutMainTab3.addComponent(sslCiphersOptionGroupInputLayoutMainTab3);
		inputLayoutMainTab3.addComponent(sslTrustChainOptionGroupInputLayoutMainTab3);
		inputLayoutMainTab3.addComponent(sendButtonInputLayoutMainTab3);
	}


	private void initTab1Data() {
		String resultArr[] = null;
		StringBuilder sb = null;
		SSLContext sslContext = SSLContextFactory.newInstance();
		
		// retrieve client default protocols
		resultArr = DefaultSettings.SSL_PROTOCOLS;
		defaultProtocolsTextFieldOutputLayoutMainTab1.setValue( Arrays.toString(resultArr) );

		
		// retrieve client default ciphers
		resultArr = DefaultSettings.SSL_CIPHERS;
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
		resultArr = MediumSettings.SSL_PROTOCOLS;
		customProtocolsTextFieldOutputLayoutMainTab1.setValue( Arrays.toString(resultArr) );
		
		
		// retrieve client custom ciphers
		resultArr = MediumSettings.SSL_CIPHERS;
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
	
	
	private void initTab3Data() {
		uriTextFieldInputLayoutMainTab3.setValue(DEFAULT_URI);
		
		sslProtocolsOptionGroupInputLayoutMainTab3.select(1);
		sslCiphersOptionGroupInputLayoutMainTab3.select(1);
		sslTrustChainOptionGroupInputLayoutMainTab3.select(1);
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
				tester = sslTestOptionObjListMainTab2.get(optionNr).getCls();
				
				
				Log.logInfo("check uri: " + uri);
				
			
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
		
		
		sendButtonInputLayoutMainTab3.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3453483238129348595L;

			public void buttonClick(ClickEvent event) {
				ICustomTester tester = new CustomTester();
				String uri = uriTextFieldInputLayoutMainTab3.getValue();
				
				if (uri == null || uri.length() == 0 || ! uri.startsWith("https")) {
					return;
				}
				
				
				showWindow();
				uriTextFieldOutputLayoutHostWindow.setValue(uri);
				
				
				int optionNr = 0;
				// retrieve ssl protocol option
				optionNr = Integer.valueOf( (Integer) sslProtocolsOptionGroupInputLayoutMainTab3.getValue() );
				optionNr--;
				tester.setProtocolSettings( sslProtocolsOptionObjListMainTab3.get(optionNr).getCls() );
				
				// retrieve ssl cipher option
				optionNr = Integer.valueOf( (Integer) sslCiphersOptionGroupInputLayoutMainTab3.getValue() );
				optionNr--;
				tester.setCipherSettings( sslCiphersOptionObjListMainTab3.get(optionNr).getCls() );
				
				// retrieve ssl trustchain option
				optionNr = Integer.valueOf( (Integer) sslTrustChainOptionGroupInputLayoutMainTab3.getValue() );
				optionNr--;
				tester.setTrustChainSettings( sslTrustChainOptionObjListMainTab3.get(optionNr).getCls() );
				
				
				Log.logInfo("check uri: " + uri);
				
			
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
