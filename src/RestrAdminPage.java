import java.util.ArrayList;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import java.util.StringTokenizer;

public class RestrAdminPage {

	protected Shell shell;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void restrRegist() {
		
		Text rName;
		Text address;
		Text minPrice;
		Text maxPrice;
		
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Restaurant Register");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 23, 414, 40);
		lblNewLabel.setText("½Ä´çÀ» µî·ÏÇÕ´Ï´Ù");
		
		rName = new Text(shell, SWT.BORDER);
		rName.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		rName.setText("\uC2DD\uB2F9 \uC774\uB984");
		rName.setBounds(90, 65, 250, 21);
		
		address = new Text(shell, SWT.BORDER);
		address.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		address.setText("\uC2DD\uB2F9 \uC8FC\uC18C");
		address.setBounds(90, 90, 250, 21);
		
		minPrice = new Text(shell, SWT.BORDER);
		minPrice.setText("\uBA54\uB274 \uCD5C\uC800\uAC00");
		minPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		minPrice.setBounds(90, 115, 250, 21);
		
		maxPrice = new Text(shell, SWT.BORDER);
		maxPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		maxPrice.setText("\uBA54\uB274 \uCD5C\uACE0\uAC00");
		maxPrice.setBounds(90, 140, 250, 21);
		
		Button accessible = new Button(shell, SWT.CHECK);
		accessible.setBounds(90, 170, 150, 16);
		accessible.setText("\uC8FC\uCD9C\uC785\uAD6C \uC7A5\uC560\uC778 \uC811\uADFC\uB85C");
		
		Button parking = new Button(shell, SWT.CHECK);
		parking.setBounds(90, 190, 150, 16);
		parking.setText("\uC7A5\uC560\uC778 \uC804\uC6A9 \uC8FC\uCC28\uC7A5");
		
		Button isFlat = new Button(shell, SWT.CHECK);
		isFlat.setBounds(90, 210, 150, 16);
		isFlat.setText("\uC8FC\uCD9C\uC785\uAD6C \uB192\uC774 \uCC28 \uC81C\uAC70 ");
		
		Button elevator = new Button(shell, SWT.CHECK);
		elevator.setBounds(90, 230, 150, 16);
		elevator.setText("\uC7A5\uC560\uC778\uC6A9 \uC5D8\uB9AC\uBCA0\uC774\uD130");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String yn1, yn2, yn3, yn4;
				if(accessible.getSelection()==true)
					yn1="Y";
				else
					yn1="N";
				if(parking.getSelection()==true)
					yn2="Y";
				else
					yn2="N";
				if(isFlat.getSelection()==true)
					yn3="Y";
				else
					yn3="N";
				if(elevator.getSelection()==true)
					yn4="Y";
				else
					yn4="N";
				new ModifRestaurant().InsertValues(rName.getText(), address.getText(), yn1, yn2, yn3, yn4, Integer.parseInt(minPrice.getText()), Integer.parseInt(maxPrice.getText()));
				shell.close();
			}
		});
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
		btnNewButton.setBounds(316, 221, 108, 25);
		btnNewButton.setText("\uB4F1\uB85D");
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void restrModify() {
		
		Text rName;
		Text address;
		Text minPrice;
		Text maxPrice;
		
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Restaurant Modify");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 23, 414, 40);
		lblNewLabel.setText("½Ä´çÀ» ¼öÁ¤ÇÕ´Ï´Ù");
		
		rName = new Text(shell, SWT.BORDER);
		rName.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		rName.setText("\uC2DD\uB2F9 \uC774\uB984");
		rName.setBounds(90, 65, 250, 21);
		
		address = new Text(shell, SWT.BORDER);
		address.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		address.setText("\uC2DD\uB2F9 \uC8FC\uC18C");
		address.setBounds(90, 90, 250, 21);
		
		minPrice = new Text(shell, SWT.BORDER);
		minPrice.setText("\uBA54\uB274 \uCD5C\uC800\uAC00");
		minPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		minPrice.setBounds(90, 115, 250, 21);
		
		maxPrice = new Text(shell, SWT.BORDER);
		maxPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		maxPrice.setText("\uBA54\uB274 \uCD5C\uACE0\uAC00");
		maxPrice.setBounds(90, 140, 250, 21);
		
		Button accessible = new Button(shell, SWT.CHECK);
		accessible.setBounds(90, 170, 150, 16);
		accessible.setText("\uC8FC\uCD9C\uC785\uAD6C \uC7A5\uC560\uC778 \uC811\uADFC\uB85C");
		
		Button parking = new Button(shell, SWT.CHECK);
		parking.setBounds(90, 190, 150, 16);
		parking.setText("\uC7A5\uC560\uC778 \uC804\uC6A9 \uC8FC\uCC28\uC7A5");
		
		Button isFlat = new Button(shell, SWT.CHECK);
		isFlat.setBounds(90, 210, 150, 16);
		isFlat.setText("\uC8FC\uCD9C\uC785\uAD6C \uB192\uC774 \uCC28 \uC81C\uAC70 ");
		
		Button elevator = new Button(shell, SWT.CHECK);
		elevator.setBounds(90, 230, 150, 16);
		elevator.setText("\uC7A5\uC560\uC778\uC6A9 \uC5D8\uB9AC\uBCA0\uC774\uD130");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String yn1, yn2, yn3, yn4;
				if(accessible.getSelection()==true)
					yn1="Y";
				else
					yn1="N";
				if(parking.getSelection()==true)
					yn2="Y";
				else
					yn2="N";
				if(isFlat.getSelection()==true)
					yn3="Y";
				else
					yn3="N";
				if(elevator.getSelection()==true)
					yn4="Y";
				else
					yn4="N";
				new ModifRestaurant().UpdateValues(rName.getText(), address.getText(), yn1, yn2, yn3, yn4, Integer.parseInt(minPrice.getText()), Integer.parseInt(maxPrice.getText()));
				shell.close();
			}
		});
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnNewButton.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 10, SWT.BOLD));
		btnNewButton.setBounds(316, 221, 108, 25);
		btnNewButton.setText("\uB4F1\uB85D");
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void restrDelete() {
		
		Table table;
		
		
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Restaurant Delete");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("¸¼Àº °íµñ", 20, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 23, 414, 40);
		lblNewLabel.setText("½Ä´çÀ» »èÁ¦ÇÕ´Ï´Ù");
		
		ArrayList<String> list=new ArrayList<>();
		new ModifRestaurant().GetValues(list);
		
		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(shell, SWT.BORDER | SWT.FULL_SELECTION);
		for(String s : list)
			checkboxTableViewer.add(s);
		
		table = ((CheckboxTableViewer)checkboxTableViewer).getTable();
		table.setBounds(50, 70, 350, 150);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			StringTokenizer tok;
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object[] result=checkboxTableViewer.getCheckedElements();
				list.clear();
				for(Object o : result) {
					tok=new StringTokenizer((String)o, ",");
					list.add(tok.nextToken());
				}
				new ModifRestaurant().DeleteValues(list);
				shell.close();
			}
		});
		btnNewButton.setBounds(350, 230, 76, 25);
		btnNewButton.setText("È®ÀÎ");
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
