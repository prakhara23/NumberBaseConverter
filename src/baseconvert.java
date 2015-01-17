import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class baseconvert {

	protected Shell shlBaseConverter;
	private Text txtinput;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			baseconvert window = new baseconvert();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBaseConverter.open();
		shlBaseConverter.layout();
		while (!shlBaseConverter.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBaseConverter = new Shell();
		shlBaseConverter.setSize(317, 192);
		shlBaseConverter.setText("Base Converter");
		
		Label lblInput = new Label(shlBaseConverter, SWT.NONE);
		lblInput.setBounds(20, 31, 46, 15);
		lblInput.setText("Number:");
		
		txtinput = new Text(shlBaseConverter, SWT.BORDER);
		txtinput.setBounds(71, 28, 203, 21);
		
		Label lblBases = new Label(shlBaseConverter, SWT.NONE);
		lblBases.setBounds(31, 74, 40, 15);
		lblBases.setText("Bases:");
			
		final Combo combofrom = new Combo(shlBaseConverter, SWT.READ_ONLY);
		combofrom.setEnabled(false);
		combofrom.setBounds(71, 71, 91, 23);
		
		final Combo comboto = new Combo(shlBaseConverter, SWT.READ_ONLY);
		comboto.setEnabled(false);
		comboto.setBounds(183, 71, 91, 23);
		
		Label lblTo = new Label(shlBaseConverter, SWT.NONE);
		lblTo.setBounds(168, 74, 11, 19);
		lblTo.setText("to");
		
		Label lblOutput = new Label(shlBaseConverter, SWT.NONE);
		lblOutput.setBounds(25, 100, 46, 15);
		lblOutput.setText("Answer:");
		
		final Label lbloutput = new Label(shlBaseConverter, SWT.NONE);
		lbloutput.setBounds(71, 100, 202, 15);
		
		final Label lblerror = new Label(shlBaseConverter, SWT.NONE);
		lblerror.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblerror.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblerror.setBounds(71, 52, 203, 15);
		
		final Button btnConvert = new Button(shlBaseConverter, SWT.NONE);
		btnConvert.setBounds(87, 122, 75, 25);
		btnConvert.setText("Convert");
		btnConvert.setEnabled(false); 
		
		txtinput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				
				String input_str = txtinput.getText().trim();
							
				comboto.removeAll();
				comboto.setEnabled(false); 
				lblerror.setText("");
				lbloutput.setText("");
				
				if (input_str.equals("")) {
										
					combofrom.removeAll();
					combofrom.setEnabled(false); 
																
				}else if (input_str.matches("[0-1]*")) {
						
					String [] bases1 = {"Binary", "Octal", "Decimal", "Hexadecimal"};
					combofrom.setItems(bases1);
					combofrom.setEnabled(true);
															
				} else if (input_str.matches("[0-7]*")) {
						
					String [] bases1 = {"Octal", "Decimal", "Hexadecimal"};
					combofrom.setItems(bases1);
					combofrom.setEnabled(true);
															
				} else if (input_str.matches("[0-9]*")) {
						
					String [] bases1 = {"Decimal", "Hexadecimal"};
					combofrom.setItems(bases1);
					combofrom.setEnabled(true);
															
				} else if (input_str.matches("[0-9a-fA-F]*") ) {
						
					String [] bases1 = {"Hexadecimal"};
					combofrom.setItems(bases1);
					combofrom.setEnabled(true);
																
				} else {
						
					combofrom.removeAll();
					combofrom.setEnabled(false);
					btnConvert.setEnabled(false);
					lblerror.setText("Invalid Input! Characters (0-9 and A-F/a-f) only.");
				}
				
				
			}
				
		});
		
		combofrom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String selected = combofrom.getText();
				String input_str = txtinput.getText();
				boolean valid = true; 
				int basenum = 0; 
				btnConvert.setEnabled(false);
										
				if (selected.equals("Binary")) {
							
							String [] bases1 = {"Octal", "Decimal", "Hexadecimal"};
							comboto.setItems(bases1);
							comboto.setEnabled(true);
							basenum = 2;
							valid = validation1(input_str , basenum); 
														
				} else if (selected.equals("Octal")) {
							
							String [] bases1 = {"Binary", "Decimal", "Hexadecimal"};
							comboto.setItems(bases1);
							comboto.setEnabled(true);
							basenum = 8;
							valid = validation1(input_str , basenum); 
													
				} else if (selected.equals("Decimal")) {
			
							String [] bases1 = {"Binary", "Octal", "Hexadecimal"};
							comboto.setItems(bases1);
							comboto.setEnabled(true);
							basenum = 10;
							valid = validation1(input_str , basenum); 
							
				} else if (selected.equals("Hexadecimal")) {
							
							String [] bases1 = {"Binary", "Octal", "Decimal"};
							comboto.setItems(bases1);
							comboto.setEnabled(true);
							basenum = 16;
							valid = validation1(input_str , basenum); 
				} 
				
				if (valid == false) {
					
					lblerror.setText("Number too large!"); 
					combofrom.removeAll();
					combofrom.setEnabled(false);
					comboto.removeAll();
					comboto.setEnabled(false);
					btnConvert.setEnabled(false);
				} else {
					
					combofrom.setEnabled(true);
				}
							
		 }
		});
		
		comboto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String error = comboto.getText();
			
				if (error.equals("Binary") || error.equals("Octal") || error.equals("Decimal") || error.equals("Hexadecimal")) {
					
					btnConvert.setEnabled(true);
					
				} else {
					
					btnConvert.setEnabled(false);
				}
		 }
		});
		
		btnConvert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String input_str = txtinput.getText().trim();
				String output = null;
				int input_num = 0;
				String from_base = combofrom.getText(); 
				String to_base = comboto.getText(); 
											
				switch (from_base) {
				   	case "Binary": 		input_num = Integer.parseInt(input_str, 2);
				   						break;
				    case "Octal": 		input_num = Integer.parseInt(input_str, 8);
				 						break;
				    case "Decimal":		input_num = Integer.parseInt(input_str, 10);
				 						break;
				    case "Hexadecimal":	input_num = Integer.parseInt(input_str, 16);
							 						break; 
				}
							
				switch (to_base) {
				   	case "Binary": 		output = Integer.toBinaryString(input_num);
				   						break;
				    case "Octal": 		output = Integer.toOctalString(input_num);
				       					break;
				    case "Decimal":		output = Integer.toString(input_num);
				       					break;
				    case "Hexadecimal":	output = Integer.toHexString(input_num);
				  						break; 
				}
				lbloutput.setText(output);		
				
			}
		});
		
			
		Button btnReset = new Button(shlBaseConverter, SWT.NONE);
		btnReset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				txtinput.setText("");
				lbloutput.setText("");
				combofrom.setEnabled(false);;
				comboto.setEnabled(false);
				btnConvert.setEnabled(false);
				
			}
		});
		btnReset.setBounds(183, 122, 75, 25);
		btnReset.setText("Reset");
		
		Label lblcriteria = new Label(shlBaseConverter, SWT.WRAP);
		lblcriteria.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblcriteria.setBounds(20, 10, 278, 15);
		lblcriteria.setText("Enter Binary, Octal, Decimal or Hexadecimal values.");
		
		
	}
	
	private static boolean validation1 (String input_str, int basenum) {
		
		try {
			
			Integer.parseInt(input_str, basenum);
						
		} catch (Exception e) {
				
				return false;
		}
		return true;
			
	}
}	




