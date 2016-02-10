package A2B;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class AscciToBinary extends JFrame implements KeyListener {

	private JLabel lblAscci, lblBinary, lblText;
	protected JTextArea txtAscci, txtBinary, txtA2B, txtB2A;

	private JScrollPane scrollA2B, scrollAscci, scrollBinary;

	protected static int to;
	protected static String out;
	protected static String cmdStr;
	protected static String cmdStrs;
	protected static String tessst;
	protected static String sum = "";

	public AscciToBinary() {
		Container c = this.getContentPane();
		c.setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Icon icon = new ImageIcon(getClass().getResource("os.GIF"));
		lblText = new JLabel(icon);

		lblText.setBounds(0, 0, screenSize.width, 100);

		lblAscci = new JLabel("Enter Word code");
		lblAscci.setForeground(Color.WHITE);
		lblAscci.setBounds(5, 80, 160, 60);

		txtAscci = new JTextArea();
		txtAscci.setBackground(Color.BLACK);
		txtAscci.setForeground(Color.WHITE);
		txtAscci.setCaretColor(Color.WHITE);
		txtAscci.addKeyListener(this);
		scrollAscci = new JScrollPane(txtAscci);
		scrollAscci.setBounds(5, 120, 620, 100);

		txtA2B = new JTextArea();
		txtA2B.setBackground(Color.BLACK);
		txtA2B.setForeground(Color.WHITE);
		txtA2B.setCaretColor(Color.black);
		txtA2B.setLineWrap(true);
		scrollA2B = new JScrollPane(txtA2B);
		
		scrollA2B.setBounds(5, 225, screenSize.width, 560);

		lblBinary = new JLabel("Enter Binary code");
		lblBinary.setForeground(Color.WHITE);
		lblBinary.setBounds(630, 80, 160, 60);

		txtBinary = new JTextArea();
		txtBinary.setBackground(Color.BLACK);
		txtBinary.setForeground(Color.WHITE);
		txtBinary.setCaretColor(Color.WHITE);
		txtBinary.addKeyListener(this);
		scrollBinary = new JScrollPane(txtBinary);
		scrollBinary.setBounds(630, 120, 650, 100);

		setBounds(0, 0, screenSize.width, screenSize.height);

		add(lblAscci);
		getContentPane().add(scrollAscci);
		add(lblBinary);
		getContentPane().add(scrollBinary);
		getContentPane().add(scrollA2B);
		add(lblText);
		c.setBackground(Color.BLACK);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static String a2b(String cmdStr) {

		int k = Integer.parseInt(tessst, 2);

		out = new Character((char) k).toString();
		return out;
	}

	public static void main(String[] args) {
		new AscciToBinary();
	}

	@Override
	public void keyPressed(KeyEvent ek) {

		if (ek.getKeyCode() == KeyEvent.VK_ENTER && ek.getSource() == txtAscci) {
			try {
				String input = txtAscci.getText();
				txtA2B.append("\n");
				byte[] bytes = input.getBytes();
				StringBuilder binary = new StringBuilder();
				for (byte b : bytes) {
					int val = b;
					for (int i = 0; i < 8; i++) {
						binary.append((val & 128) == 0 ? 0 : 1);
						val <<= 1;
					}
					binary.append("  ");
				}

				txtAscci.setText("");
				txtA2B.append(input + " -> " + binary );
			} catch (Exception e2) {
			}
		}

		if (ek.getKeyCode() == KeyEvent.VK_ENTER && ek.getSource() == txtBinary) {
			try {
				cmdStr = txtBinary.getText();
				txtA2B.append("\n");
				cmdStrs = cmdStr.replaceAll("\\s", "");

				for (int i = 0; i < cmdStrs.length(); i += 8) {
					to = (i + 8);
					tessst = cmdStrs.substring(i, to);
					
					sum += a2b(cmdStrs);
				}
				txtBinary.setText(null);
				txtBinary.setCaretPosition(0);
				txtA2B.append(cmdStr + " -> " +sum );
				sum = "";
			} catch (Exception e1) {

			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}