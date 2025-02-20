package a2203245140_tareas_unidad01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

	public class watch extends JFrame {
	JLabel runningTimeLabel = new JLabel(); JTextField
	runningTimeTextField = new JTextField(); JLabel totalTimeLabel = new
	JLabel(); JTextField totalTimeTextField = new JTextField(); JButton
	startStopButton = new JButton(); JButton resetButton = new JButton();
	JButton exitButton = new JButton(); Timer displayTimer;
	long startTime;
	long stoppedTime;
	long stopTime;
	public static void main(String args[]) {
	// create frame
	new watch().show(); }
		public watch() {
	// frame constructor
	setTitle("a2203245140 Stopwatch");
	setResizable(false);
	addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent evt) {
	exitForm(evt); 
	}
	});
	getContentPane().setLayout(new GridBagLayout());
	GridBagConstraints gridConstraints; 
	runningTimeLabel.setText("Running Time:");
	runningTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	gridConstraints = new GridBagConstraints(); 
	gridConstraints.gridx = 0; 
	gridConstraints.gridy = 0; 
	gridConstraints.insets = new Insets(10,25, 0, 0); 
	getContentPane().add(runningTimeLabel,gridConstraints); 
	runningTimeTextField.setPreferredSize(new Dimension(150, 50)); 
	runningTimeTextField.setEditable(false);
	runningTimeTextField.setBackground(Color. WHITE); 
	runningTimeTextField.setForeground(Color.BLUE); 
	runningTimeTextField.setText("00:00:00");
	runningTimeTextField.setHorizontalAlignment(SwingConstants.CENTER); 
	runningTimeTextField.setFont(new Font("Arial",Font.BOLD,24));
	gridConstraints = new GridBagConstraints(); 
	gridConstraints.gridx =0; 
	gridConstraints.gridy = 1; 
	gridConstraints.gridwidth = 2;;
	gridConstraints.insets = new Insets(0, 10, 0, 10);
	getContentPane().add(runningTimeTextField,gridConstraints);
	totalTimeLabel.setText("Total Time:"); 
	totalTimeLabel.setFont(new Font("Arial", Font. PLAIN, 14)); 
	gridConstraints = new GridBagConstraints(); gridConstraints.gridx = 0;
	gridConstraints.gridy = 2; gridConstraints.insets = new Insets(10, 10,0, 10); getContentPane().add(totalTimeLabel, gridConstraints); 
	totalTimeTextField.setPreferredSize(new Dimension(150, 50)); 
	totalTimeTextField.setEditable(false); totalTimeTextField.setBackground(Color.WHITE); 
	totalTimeTextField.setForeground(Color.RED); totalTimeTextField.setText("00:00:00");
	totalTimeTextField.setHorizontalAlignment(SwingConstants.CENTER); 
	totalTimeTextField.setFont(new Font("Arial", Font.BOLD, 24));
	gridConstraints = new GridBagConstraints(); 
	gridConstraints.gridx = 0; gridConstraints.gridy = 3; gridConstraints.gridwidth = 2;
	gridConstraints.insets = new Insets(0, 10, 15, 10);
	getContentPane().add(totalTimeTextField, gridConstraints);
	startStopButton.setText("Start"); 
	gridConstraints = new GridBagConstraints(); gridConstraints.gridx = 0;
	gridConstraints.gridy = 4; 
	getContentPane().add(startStopButton,gridConstraints); 
	startStopButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {

		startStopButtonActionPerformed(e); }

	});
	resetButton.setText("Reset"); resetButton.setEnabled(false);
	gridConstraints = new GridBagConstraints(); 
	gridConstraints.gridx = 1; gridConstraints.gridy = 4; 
	gridConstraints.insets = new Insets(0, 0,0, 25); getContentPane().add(resetButton, gridConstraints);
	resetButton.addActionListener(new ActionListener() {
		
	public void actionPerformed(ActionEvent e) {	
		resetButtonActionPerformed(e); }
	});

	exitButton.setText("Exit"); gridConstraints = new GridBagConstraints(); 
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 5; 
	gridConstraints.insets = new Insets(10, 0,10, 25); getContentPane().add(exitButton, gridConstraints);

	exitButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	exitButtonActionPerformed(e); }
	});

	displayTimer = new Timer(1000, new ActionListener() {
		
	public void actionPerformed(ActionEvent e) {
	displayTimerActionPerformed(e);}
	});
	pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)((screenSize.width - getWidth())), (int) ((screenSize.height - getHeight())), 307,346); }

	private void exitForm(WindowEvent evt) {
	System.exit(0);

	}

	private void startStopButtonActionPerformed(ActionEvent e){
	if (startStopButton.getText().equals("Start")) {
	// initial to running state 
		startTime = System.currentTimeMillis();
	stoppedTime = 0;
	startStopButton.setText("Stop"); 
	exitButton.setEnabled(false);
	displayTimer.start();}
	else if (startStopButton.getText().equals("Stop")) {
	// running to stopped state 
		stopTime =
	System.currentTimeMillis(); startStopButton.setText("Restart"); 
	resetButton.setEnabled(true); exitButton.setEnabled(true); displayTimer.stop();}
	else if (startStopButton.getText().equals("Restart")) {
	// stopped to running state 
		stoppedTime += System.currentTimeMillis() - stopTime;
	startStopButton.setText("Stop"); resetButton.setEnabled(false);
	exitButton.setEnabled(false); displayTimer.start();
	}
	}
	private void resetButtonActionPerformed(ActionEvent e){
	// return to initial state 
		runningTimeTextField.setText("00:00:00"); 
		totalTimeTextField.setText("00:00:00");
		startStopButton.setText("Start"); resetButton.setEnabled(false); }
		private void exitButtonActionPerformed(ActionEvent e) {
		System.exit(0);
		}
		private void displayTimerActionPerformed(ActionEvent e) {
		long currentTime;
		// Determine running and total times 
		currentTime = System.currentTimeMillis(); 
		// Display times 
		runningTimeTextField.setText(HMS(currentTime-startTime-
		stoppedTime)); totalTimeTextField.setText(HMS(currentTime - startTime));
		}
		
		private String HMS(long tms) {
		int h;
		int m;
		int s;
		double t;
		t = tms / 1000.0;
		// Break time down into hours, minutes, and seconds 
		h = (int) (t / 3600);
		m = (int) ((t - h * 3600) / 60); s = (int) (t - h * 3600 - m * 60); // Format time as string
		return(new DecimalFormat("00").format(h) + ":" + new
		DecimalFormat("00").format(m) + ":" + new
		DecimalFormat("00").format(s));
		}
}