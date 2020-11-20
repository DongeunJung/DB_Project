import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StartPage {

	protected Shell shell;
	private Text Username;
	private Text Password;
	public static String username;
	public static int userID;

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Start Page");	
		
		Label lblNewLabel1 = new Label(shell, SWT.CENTER);
		lblNewLabel1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
		lblNewLabel1.setBounds(10, 43, 414, 50);
		lblNewLabel1.setText("\uB85C\uADF8\uC778\uD558\uC138\uC694!");
		
		Label usernameLabel = new Label(shell, SWT.NONE);
		usernameLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
		usernameLabel.setBounds(112, 109, 100, 30);
		usernameLabel.setText("Username");
		
		Label passwordLabel = new Label(shell, SWT.NONE);
		passwordLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
		passwordLabel.setBounds(112, 145, 100, 30);
		passwordLabel.setText("Password");
		
		Username = new Text(shell, SWT.BORDER);
		Username.setBounds(218, 116, 100, 22);
		
		Password = new Text(shell, SWT.BORDER);
		Password.setBounds(218, 152, 100, 22);
		
		Button loginButton = new Button(shell, SWT.NONE);
		loginButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
		loginButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				username=Username.getText();
				try {
					if(new ModifUserInfo().CheckUserInfo(username)) {
						if(Integer.parseInt(Password.getText())==userID) {
							ServicePage window=new ServicePage();
							//shell.close();
							window.open();	
						}
					}
					
				}
				catch(Exception ee) {
					System.out.println(ee.getMessage());
				}
			}
		});
		loginButton.setBounds(336, 210, 76, 25);
		loginButton.setText("\uB85C\uADF8\uC778");
		
		Button signupButton = new Button(shell, SWT.NONE);
		signupButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//È¸¿ø°¡ÀÔÆäÀÌÁö½ÃÀÛ
				try {
					Shell registerShell;
					Display display = Display.getDefault();
					registerShell = new Shell();
					registerShell.setSize(450, 300);
					registerShell.setText("Uesr Registration");
					
					Label usernameLabel = new Label(registerShell, SWT.NONE);
					usernameLabel.setAlignment(SWT.CENTER);
					usernameLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
					usernameLabel.setBounds(10, 44, 414, 49);
					usernameLabel.setText("\uD68C\uC6D0\uAC00\uC785\uD558\uC138\uC694!");
					
					Label lblNewLabel_1 = new Label(registerShell, SWT.NONE);
					lblNewLabel_1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 18, SWT.NORMAL));
					lblNewLabel_1.setBounds(108, 99, 110, 30);
					lblNewLabel_1.setText("Username");
					
					Text Username;
					Username = new Text(registerShell, SWT.BORDER);
					Username.setBounds(225, 106, 100, 23);
					
					Button YORN = new Button(registerShell, SWT.CHECK);
					YORN.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
					YORN.setBounds(108, 145, 180, 20);
					YORN.setText("\uD720\uCCB4\uC5B4\uB97C \uC0AC\uC6A9\uD569\uB2C8\uB2E4.");//ÈÙÃ¼¾î¸¦ »ç¿ëÇÕ´Ï´Ù.
					
					Button btnNewButton = new Button(registerShell, SWT.NONE);
					btnNewButton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if(YORN.getSelection()==true) {
								new ModifUserInfo().InsertValues(Username.getText(), 'Y');
							}
							else {
								new ModifUserInfo().InsertValues(Username.getText(), 'N');
							}
							Shell confirmShell;
							Display display = Display.getDefault();
							confirmShell = new Shell();
							confirmShell.setSize(450, 300);
							confirmShell.setText("Confirm");
							Label lblNewLabel = new Label(confirmShell, SWT.NONE);
							lblNewLabel.setAlignment(SWT.LEFT);
							lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
							lblNewLabel.setBounds(70, 92, 350, 40);
							lblNewLabel.setText("È¸¿ø´ÔÀÇ ºñ¹Ð¹øÈ£´Â "+new ModifUserInfo().getUserID(Username.getText())+"ÀÔ´Ï´Ù.");
							
							Button btnNewButton = new Button(confirmShell, SWT.NONE);
							btnNewButton.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent e) {
									confirmShell.close();
									registerShell.close();
								}
							});
							btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
							btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
							btnNewButton.setBounds(272, 162, 100, 30);
							btnNewButton.setText("\uD655\uC778");
							confirmShell.open();
							confirmShell.layout();
							while (!confirmShell.isDisposed()) {
								if (!display.readAndDispatch()) {
									display.sleep();
								}
							}
						}
					});
					
					btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
					btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
					btnNewButton.setBounds(293, 187, 76, 25);
					btnNewButton.setText("\uC81C\uCD9C");
					
					registerShell.open();
					registerShell.layout();
					while (!registerShell.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
				}
				catch(Exception ee) {
					System.out.println(ee.getMessage());
				}
			}
		});
		signupButton.setBounds(110, 190, 210, 25);
		signupButton.setText("¾ÆÁ÷ È¸¿øÀÌ ¾Æ´Ï½Å°¡¿ä? Click");
		
		Button setButton = new Button(shell, SWT.NONE);
		setButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 8, SWT.NORMAL));
		setButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdminPage window=new AdminPage();
				window.AdminLogin();
			}
		});
		setButton.setBounds(10, 10, 40, 25);
		setButton.setText("¼³Á¤");
	}
}
