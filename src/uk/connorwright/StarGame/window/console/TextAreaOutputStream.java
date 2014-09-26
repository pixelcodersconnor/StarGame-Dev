package uk.connorwright.StarGame.window.console;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// Create console alternative to be printed on the command GUI

public class TextAreaOutputStream extends OutputStream {
	private final JTextArea textArea;
	private final StringBuilder sb = new StringBuilder();
	private String title;

	public TextAreaOutputStream(final JTextArea textArea, String title) {
		textArea.setEditable(false);
		this.textArea = textArea;
		this.title = title;
		sb.append(title + " > ");
	}

	@Override
	public void flush() {

	}

	@Override
	public void close() {

	}

	@Override
	public void write(int b) throws IOException {

		if (b == '\r')
			return;

		if (b == '\n') {
			final String text = sb.toString() + "\n";
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					textArea.append(text);
				}
			});
			sb.setLength(0);
			sb.append(title + "> ");
			return;
		}

		sb.append((char) b);

	}

}