import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaFim {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void startWindow(final String nome) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFim window = new TelaFim(nome);
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
	public TelaFim(String nome) {
		initialize(nome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nome) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblOk = new JLabel("Fim");
		lblOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
				String[] args = null;
				// Precisa Terminar o Cenario
				MenuInicial.main(args);;
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
		lblOk.setFont(new Font("LM Roman Caps 10", Font.BOLD, 20));
		lblOk.setHorizontalAlignment(SwingConstants.CENTER);
		lblOk.setBounds(170, 227, 84, 33);
		frame.getContentPane().add(lblOk);
		
		JLabel lblVencedor = new JLabel("Vencedor: ");
		lblVencedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVencedor.setFont(new Font("LM Roman Caps 10", Font.BOLD, 16));
		lblVencedor.setBounds(12, 56, 128, 33);
		frame.getContentPane().add(lblVencedor);
		
		JLabel lblPerdedor = new JLabel("Perdedor: ");
		lblPerdedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdedor.setFont(new Font("LM Roman Caps 10", Font.BOLD, 16));
		lblPerdedor.setBounds(12, 143, 128, 33);
		frame.getContentPane().add(lblPerdedor);
		
		JLabel lblNomePerdedor = new JLabel(nome);
		lblNomePerdedor.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomePerdedor.setFont(new Font("LM Roman Caps 10", Font.BOLD, 16));
		lblNomePerdedor.setBounds(140, 143, 300, 33);
		frame.getContentPane().add(lblNomePerdedor);
	}

}
