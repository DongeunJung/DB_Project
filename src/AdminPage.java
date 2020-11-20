import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AdminPage {

	public static final int adminPassword=170319;
	protected Shell shell;
	private Button btnNewButton_2;
	private Button btnNewButton_3;
	private Button btnNewButton_4;
	
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
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Administrator Page");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
		lblNewLabel_2.setBounds(90, 45, 250, 40);
		lblNewLabel_2.setText("\uC548\uB155\uD558\uC138\uC694, \uAD00\uB9AC\uC790\uB2D8!");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new RestrAdminPage().restrRegist();
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		btnNewButton_1.setBounds(90, 90, 250, 35);
		btnNewButton_1.setText("\uC2DD\uB2F9 \uB4F1\uB85D\uD558\uAE30");
		
		btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new RestrAdminPage().restrModify();
			}
		});
		btnNewButton_2.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		btnNewButton_2.setBounds(90, 140, 250, 35);
		btnNewButton_2.setText("\uC2DD\uB2F9 \uC218\uC815\uD558\uAE30");
		
		btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new RestrAdminPage().restrDelete();
			}
		});
		btnNewButton_3.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		btnNewButton_3.setBounds(90, 190, 250, 35);
		btnNewButton_3.setText("\uC2DD\uB2F9 \uC0AD\uC81C\uD558\uAE30");
		
		btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnNewButton_4.setBounds(374, 226, 50, 25);
		btnNewButton_4.setText("\uC885\uB8CC");

	}
	public void AdminLogin() {
		Shell loginShell;
		Text password;
		Display display = Display.getDefault();
		loginShell = new Shell();
		loginShell.setSize(450, 300);
		loginShell.setText("Administrator Login");
		
		Label lblNewLabel = new Label(loginShell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
		lblNewLabel.setBounds(100, 80, 250, 40);
		lblNewLabel.setText("°ü¸®ÀÚ ÆäÀÌÁöÀÔ´Ï´Ù.");
		
		password = new Text(loginShell, SWT.BORDER);
		password.setBounds(210, 130, 100, 22);
		
		Label lblNewLabel_1 = new Label(loginShell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(130, 130, 70, 30);
		lblNewLabel_1.setText("Password");
		
		Button btnNewButton = new Button(loginShell, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(Integer.parseInt(password.getText())==adminPassword) {
					loginShell.close();
					new AdminPage().open();//°ü¸®ÀÚÆäÀÌÁö¿ÀÇÂ
				}
			}
		});
		btnNewButton.setBounds(325, 200, 76, 25);
		btnNewButton.setText("·Î±×ÀÎ");
		
		loginShell.open();
		loginShell.layout();
		while (!loginShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
