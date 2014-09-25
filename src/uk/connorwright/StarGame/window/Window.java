package uk.connorwright.StarGame.window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.connorwright.StarGame.window.commands.God;
import uk.connorwright.StarGame.window.commands.Restart;
import uk.connorwright.StarGame.window.commands.StartGame;
import uk.connorwright.StarGame.window.commands.dev.NextState;

public class Window {
	final static JTextField commandField = new JTextField(40);
	public static String command;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 140;

	public static void makeFrame() {
		JFrame commandWindow = new JFrame("Enter a command...");
		commandWindow.setLayout(new GridLayout(2, 6));
		commandWindow.setSize(new Dimension(HEIGHT, WIDTH));
		commandWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		commandWindow.setResizable(false);
		commandWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setSize(HEIGHT, WIDTH);
		JLabel label = new JLabel();
		label.setText("Command: ");
		JButton submit = new JButton();
		submit.setText("Submit");
		submit.setSize(30, 20);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				command = commandField.getText();
				if (command.equals("god")) {
					God.godMode();
					clearField();
				} 

				if (command.equals("restart")) {
					Restart.restartGame();
					clearField();
				}



				if(command.equals("devoverride")) {
					System.out.println("Dev Override Activated");
					clearField();
				}

				if(command.equals("nextstate")) {
					NextState.toggleState();
					clearField();
				}

				if(command.equals("startgame")) {
					StartGame.newGame();
				}
			}
		});
		panel.add(label);
		panel.add(commandField);
		panel.add(submit);
		commandWindow.getContentPane().add(panel);
		commandWindow.setVisible(true);
	}

	public static void submit_ActionPerformed(ActionEvent evt) {
	}

	public static void main(String[] args) {
		makeFrame();
	}

	private static void clearField(){
		commandField.selectAll();
		commandField.cut();
	}
}