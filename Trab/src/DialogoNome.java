import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogoNome {

	private JFrame frame;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void startWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogoNome window = new DialogoNome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DialogoNome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 442, 149);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setForeground(Color.DARK_GRAY);
		txtNome.setBackground(Color.WHITE);
		txtNome.setFont(new Font("L M Roman Caps10", Font.PLAIN, 12));
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setBounds(22, 39, 396, 33);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		final JLabel lblOk = new JLabel("OK");
		lblOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = txtNome.getText();
				Jogador jogador = new Jogador(nome);
				Jogo jogo = new Jogo(jogador);
				MenuInicial.frame.setVisible(false);
				MenuInicial.frame.dispose();
				frame.setVisible(false);
				frame.dispose();
				jogo.iniciarJogo();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOk.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOk.setForeground(Color.BLACK);
			}
		});
		lblOk.setHorizontalAlignment(SwingConstants.LEFT);
		lblOk.setBounds(302, 94, 37, 15);
		frame.getContentPane().add(lblOk);
		
		final JLabel lblCancel = new JLabel("Cancel");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCancel.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCancel.setForeground(Color.BLACK);
			}
		});
		lblCancel.setBounds(351, 94, 70, 15);
		frame.getContentPane().add(lblCancel);
		
		JLabel lblNome = new JLabel(" Nome : ");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("L M Roman Caps10", Font.BOLD, 12));
		lblNome.setBounds(15, 23, 70, 15);
		frame.getContentPane().add(lblNome);
	}
}
