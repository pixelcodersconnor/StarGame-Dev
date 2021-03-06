package uk.connorwright.StarGame.window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.audio.Sound;
import uk.connorwright.StarGame.window.commands.God;
import uk.connorwright.StarGame.window.commands.Restart;
import uk.connorwright.StarGame.window.commands.dev.NextState;
import uk.connorwright.StarGame.window.commands.help.DisplayCommands;
import uk.connorwright.StarGame.window.console.TextAreaOutputStream;

public class Window {
	final static JTextField commandField = new JTextField(40);
	private static JTextArea commandOutput = new JTextArea(15, 30);
	private static TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
			commandOutput, "Console ");
	public static String command;
	public static final int HEIGHT = 700;
	public static final int WIDTH = 305;

	public static void makeFrame() {
		JFrame commandWindow = new JFrame("Enter a command...");
		commandWindow.setLayout(new GridLayout(1, 1));
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
				Sound.selectEffect.play();
				command = commandField.getText();
				if (command.equalsIgnoreCase("god")) {
					System.out.println("God mode activated, check in-game!");
					God.godMode();
					clearField();

				}

				if (command.equalsIgnoreCase("restart")) {
					System.out.println("Restarting game...");
					Restart.restartGame();
					clearField();
				}

				if (command.equalsIgnoreCase("devoverride")) {
					System.out.println("Dev Override Activated");
					clearField();
				}

				if (command.equalsIgnoreCase("nextstate")) {
					if (Game.State == STATE.MENU) {
						System.out.println("Switching to game state...");
						NextState.toggleState();
					} else if (Game.State == STATE.GAME) {
						System.out.println("Switching to menu state...");
						NextState.toggleState();
					}

					clearField();

				}

				if (command.equalsIgnoreCase("help")) {
					DisplayCommands.showHelp();

					clearField();
				}


				if(!command.equalsIgnoreCase("nextstate") && !command.equals("god") && !command.equalsIgnoreCase("restart") && !command.equalsIgnoreCase("devoverride") && !command.equalsIgnoreCase("help")) {
					System.out.println("Invalid command! Please type help");
					System.out.println(" for a list of commands");

					clearField();
				}

			}
		});
		panel.add(label);
		panel.add(commandField);
		panel.add(submit);
		System.setOut(new PrintStream(taOutputStream));
		commandOutput.setEditable(false);
		panel.add(new JScrollPane(commandOutput,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		commandWindow.getContentPane().add(panel);
		commandWindow.setVisible(true);
	}

	public static void submit_ActionPerformed(ActionEvent evt) {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				makeFrame();
			}
		});
	}

	private static void clearField() {
		commandField.selectAll();
		commandField.cut();
	}
}