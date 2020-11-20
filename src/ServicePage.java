import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.util.ArrayList;

public class ServicePage {

	protected Shell shell;

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
		shell.setText("Service Page");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 16, SWT.NORMAL));
		lblNewLabel.setBounds(65, 61, 300, 40);
		lblNewLabel.setText("¾È³çÇÏ¼¼¿ä, "+StartPage.username+"´Ô!");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//½Ä´ç Á¶È¸ÇÏ±â ¹öÆ°//OLAP»ç¿ë
				new ModifRestaurant().CreateRestrInq();//Á¶È¸ÇÏ±â ¹öÅÏ Å¬¸¯½Ã table »ý¼º
				Text restrName;
				Text restrAdd;
				Text minPrice;
				Text maxPrice;
				
				Display display = Display.getDefault();
				Shell inquiryShell;
				inquiryShell=new Shell();
				inquiryShell.setSize(450, 300);
				inquiryShell.setText("Inquire Restaurant");
				
				restrName = new Text(inquiryShell, SWT.BORDER);
				restrName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				restrName.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 9, SWT.NORMAL));
				restrName.setText("ÀÌ¸§");
				restrName.setBounds(52, 55, 70, 25);
				restrAdd = new Text(inquiryShell, SWT.BORDER);
				restrAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				restrAdd.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 9, SWT.NORMAL));
				restrAdd.setText("ÁÖ¼Ò");
				restrAdd.setBounds(130, 55, 70, 25);
				
				Spinner spinner = new Spinner(inquiryShell, SWT.BORDER);
				spinner.setMaximum(5);
				spinner.setBounds(220, 55, 60, 25);
				
				Button wChairYN = new Button(inquiryShell, SWT.CHECK);
				wChairYN.setBounds(305, 55, 94, 25);
				wChairYN.setText("\uD720\uCCB4\uC5B4 \uC774\uC6A9");
				
				Button setPrice = new Button(inquiryShell, SWT.CHECK);
				setPrice.setBounds(52, 88, 84, 25);
				setPrice.setText("\uAC00\uACA9\uB300 \uC124\uC815");
				
				minPrice = new Text(inquiryShell, SWT.BORDER);
				minPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				minPrice.setText("\uCD5C\uC800 \uAC00\uACA9");
				minPrice.setBounds(140, 86, 75, 25);
				
				maxPrice = new Text(inquiryShell, SWT.BORDER);
				maxPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				maxPrice.setText("\uCD5C\uACE0 \uAC00\uACA9");
				maxPrice.setBounds(230, 86, 75, 25);
				
				Button btnNewButton = new Button(inquiryShell, SWT.NONE);
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {//Àû¿ë¹öÆ°
											
						ArrayList<String> restrs=new ArrayList<>();

						String name=null;
						String address=null;
						
						name=restrName.getText();
						address=restrAdd.getText();
						
						new ModifRestaurant().InquireValues(restrs, name, address, wChairYN.getSelection(), setPrice.getSelection(), minPrice.getText(), maxPrice.getText(), spinner.getSelection());
				
						Display display = Display.getDefault();
						Shell nshell = new Shell();
						nshell.setSize(450, 300);
						nshell.setText("Inquire Restaurant");
						String[] result=new String[restrs.size()];
						
						for(int i=0; i<result.length; i++)
							result[i]=restrs.get(i);
						List list = new List(nshell, SWT.BORDER | SWT.V_SCROLL);
						list.setItems(result);		
						list.setBounds(20, 20, 400, 220);
						nshell.open();
						nshell.layout();
						while (!nshell.isDisposed()) {
							if (!display.readAndDispatch()) {
								display.sleep();
							}
						}
					}
				});
				btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
				btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				btnNewButton.setBounds(310, 86, 76, 25);
				btnNewButton.setText("Àû¿ë");
				
				Button endButton = new Button(inquiryShell, SWT.NONE);
				endButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {//Àû¿ë¹öÆ°
						new ModifRestaurant().DropRestrInq();
						inquiryShell.close();
					}
				});
				endButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
				endButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				endButton.setBounds(310, 200, 76, 25);
				endButton.setText("Á¾·á");
				
				inquiryShell.open();
				inquiryShell.layout();
				while (!inquiryShell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		btnNewButton.setBounds(90, 124, 250, 35);
		btnNewButton.setText("\uC2DD\uB2F9 \uC870\uD68C\uD558\uAE30");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//º°Á¡µî·ÏÇÏ±â
				Text rName;
				Display display = Display.getDefault();
				Shell reviewShell;
				reviewShell=new Shell();
				reviewShell.setSize(450, 300);
				reviewShell.setText("Register Review");
				reviewShell.open();
				
				Label lblNewLabel = new Label(reviewShell, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
				lblNewLabel.setAlignment(SWT.CENTER);
				lblNewLabel.setBounds(10, 42, 414, 47);
				lblNewLabel.setText("\uBCC4\uC810\uC744 \uB4F1\uB85D\uD569\uB2C8\uB2E4");
				
				rName = new Text(reviewShell, SWT.BORDER);
				rName.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
				rName.setText("\uC2DD\uB2F9 \uC774\uB984");
				rName.setBounds(106, 100, 220, 30);
				
				Spinner score = new Spinner(reviewShell, SWT.BORDER);
				score.setMaximum(5);
				score.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
				score.setBounds(106, 151, 220, 30);
				
				Button btnNewButton = new Button(reviewShell, SWT.NONE);
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {//º°Á¡µî·ÏÈ®ÀÎ
						new ModifReview().InsertValues(rName.getText(), score.getText());
						reviewShell.close();
					}
				});
				btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
				btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
				btnNewButton.setBounds(106, 200, 220, 30);
				btnNewButton.setText("\uB4F1\uB85D");
				
				reviewShell.layout();
				while (!reviewShell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 12, SWT.NORMAL));
		btnNewButton_1.setBounds(90, 172, 250, 35);
		btnNewButton_1.setText("\uBCC4\uC810 \uB4F1\uB85D\uD558\uAE30");
	}

}
