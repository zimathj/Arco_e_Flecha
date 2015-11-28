import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Cenario {
	
	private static JFrame frame;
	static Cenario window;
	private Muro muro = new Muro();
	static JLabel labelPontos;
	static Jogo jogo;
	int mouseX, mouseY;
	boolean walkable = true;
	int strCounter;
	int contador = 0;
	int time = 0;
	int xMouse, yMouse;
	int forca = 0;
	double angulo, oposto, hip, sine;
	JLabel labelAngulo;
	JLabel labelForca;
	JLabel lblDistncia;

	
	public static void cenario(final Jogo jogo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Cenario(jogo);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void close() {
		window.frame.setVisible(false);
		window.frame.dispose();
	}

	public Cenario(Jogo jogo) {
		Cenario.jogo = jogo;
		initialize();
	}

	private void initialize() {
		
		final JLabel str = new JLabel("");
		str.setFont(new Font("L M Roman Caps10", Font.BOLD, 12));
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(200, 100, 1100, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(-27, 500, 1500, 103);
		frame.getContentPane().add(panel);
		
		labelAngulo = new JLabel("");
		labelAngulo.setFont(new Font("L M Roman Caps10", Font.BOLD, 12));
		frame.getContentPane().add(labelAngulo);

		labelForca = new JLabel("");
		labelForca.setFont(new Font("L M Roman Caps10", Font.BOLD, 12));
		labelForca.setVisible(false);
		frame.getContentPane().add(labelForca);
		
		
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
				angulo = getAngulo();
				labelForca.setVisible(false);
				lblDistncia.setVisible(false);
			}

		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				forca = 0;
				if (jogo.getJogador1().getPontos() >= 5) {
					walkable = false;
					time = (int) System.currentTimeMillis();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (jogo.getJogador1().getPontos() >= 5) {
					jogo.getJogador1().setPontos(jogo.getJogador1().getPontos() - 5); // Se apertar A continua tirando pontos
					atualizarPontos(jogo);
					forca = (((int) System.currentTimeMillis() - time)/20)+1;
					if (forca > 100) {
						forca = 100;
						System.out.println("100");
						calcularDistancia();
						labelForca.setBounds(xMouse, yMouse, 100, 50);
						labelForca.setText("Força: " + Integer.toString(forca));
						labelForca.setVisible(true);
					}
					else {
						System.out.println(forca);
						calcularDistancia();
						labelForca.setBounds(xMouse, yMouse, 100, 50);
						labelForca.setText("Força: " + Integer.toString(forca));
						labelForca.setVisible(true);
					}
				}
				walkable = true;
			}
		});
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_RIGHT) {
					if (walkable == true && jogo.getJogador().getPontos() > 0 && jogo.getJogador1().getX() < muro.getX() - 10) {
						right();
						getAngulo();
					}
				}
				if (code == KeyEvent.VK_LEFT) {
					// FALTA CONDICAO DO JOGADOR DA DIREITA NAO PODER SAIR DA TELA
					if (walkable == true && jogo.getJogador().getPontos() > 0 && jogo.getJogador1().getX() > 0) {
						left();		
						getAngulo();
					}
				}
				if (code == KeyEvent.VK_S) {
					Loja.loja(jogo);
				}
				if (code == KeyEvent.VK_D) {
					// Teste da barra retirar dps
					jogo.getJogador1().setHP(jogo.getJogador1().getHP() - 25);;
				}

			}
		});
		
		
		muro.setX(510);
		frame.getContentPane().add(muro.getLabel());
		
		frame.getContentPane().add(jogo.getJogador1().getLabel());
		frame.getContentPane().add(jogo.getJogador1().getProgressBar());
		
		JLabel label = new JLabel("");
		label.setFont(new Font("L M Roman Caps10", Font.BOLD, 16));
		label.setBounds(30, 12, 297, 36);
		label.setText(jogo.getJogador1().getNome());
		frame.getContentPane().add(label);
		
		
		labelPontos = new JLabel("");
		labelPontos.setFont(new Font("L M Roman Caps10", Font.PLAIN, 12));
		labelPontos.setBounds(30, 40, 297, 36);
		labelPontos.setText("Pontos : " + Integer.toString(jogo.getJogador1().getPontos()));
		frame.getContentPane().add(labelPontos);
		
		lblDistncia = new JLabel("Distância: ");
		lblDistncia.setVisible(false);
		lblDistncia.setBounds(336, 416, 120, 15);
		frame.getContentPane().add(lblDistncia);
				
	}
	
	public static void atualizarPontos(Jogo jogo) {
		labelPontos.setText("Pontos : " + Integer.toString(jogo.getJogador1().getPontos()));
	}

	public static void atualizarCenario(Jogo jogo) {
		frame.getContentPane().remove((jogo.getJogador1().getLabel()));
		frame.getContentPane().remove(jogo.getJogador1().getProgressBar());
		frame.getContentPane().add(jogo.getJogador1().getLabel());
		frame.getContentPane().add(jogo.getJogador1().getProgressBar());
	}	
	
	public void right() {
		int newX = jogo.getJogador1().getX() + 5; // Numero controla qtd movimento
		contador += 1;
		if (contador == 10) {
			jogo.getJogador().setPontos(jogo.getJogador().getPontos() - 1);
			atualizarPontos(jogo);
			contador = 0;
		}
		jogo.getJogador1().setX(newX);
		jogo.atualizarCenario();	
	}
	
	public void left() {
		int newX = jogo.getJogador1().getX() - 5; // Numero controla qtd movimento
		contador += 1;
		if (contador == 10) {
			jogo.getJogador().setPontos(jogo.getJogador().getPontos() - 1);
			atualizarPontos(jogo);
			contador = 0;
		}
		jogo.getJogador1().setX(newX);
		jogo.atualizarCenario();	
	}
	
	public double getAngulo() {
		/*
		double oposto = 1;
		double hip = Math.sqrt(
	            Math.pow(2 - 1, 2) +
	            Math.pow(2 - 1, 2) );
		System.out.println(oposto);
		System.out.println(hip);
		double sine = oposto/hip;
		System.out.println(sine);
		System.out.println(Math.ceil(Math.toDegrees(Math.asin(sine))));
		labelAngulo.setBounds(xMouse, yMouse, 50, 50);
		labelAngulo.setText(Double.toString(sine));
		//System.out.println(Math.toDegrees(Math.asin(sine)));
		return Math.asin(sine);
		*/
		oposto = 515 - yMouse;
		hip = Math.sqrt(
	            Math.pow(xMouse - jogo.getJogador1().getX() - 10, 2) +
	            Math.pow(yMouse - 515, 2) );
		sine = oposto/hip;
		labelAngulo.setBounds(xMouse, yMouse - 60, 50, 50);
		//System.out.println(sine);
		labelAngulo.setText(Double.toString(Math.ceil(Math.toDegrees(Math.asin(sine)))) + "°");
		//System.out.println(Double.toString(Math.ceil(Math.toDegrees(Math.asin(sine)))));
		return Math.ceil(Math.toDegrees(Math.asin(sine)));
		
	}
	
	public int calcularDistancia() {
		System.out.println("Teste");
		// Componentes independentes
		int vox = (int) Math.ceil(forca * Math.cos(Math.toRadians(angulo)));
		System.out.println(vox);
		System.out.println(Math.cos(Math.toRadians(angulo)));
		int voy = (int) Math.ceil(forca * Math.sin(Math.toRadians(angulo)));
		System.out.println(voy);
		System.out.println(Math.sin(Math.toRadians(angulo)));
		double posX = 0.0,posY = 10000000.0;
		double t = 0.0;
		while(posY > 500) {
			posX = vox * t;
			posY = 515 + voy*t - 0.5*(9.8)*t*t;
			System.out.println("Y: " + posY);
			t+=0.005;
		}
		System.out.println("X: " + posX);
		System.out.println("Y: " + posY);
		System.out.println("T: " + t);
		/*
		// Tempo para chegar no chão
		int time = (int) Math.ceil(Math.sqrt(((500 - 515 - voy)/(0.5*(-9.8)))));
		System.out.println(time);
		double dist = vox * time; 
		System.out.println(dist);
		double posX, posY;
		for (double t = 0; t < time; t+=0.005) {
			// Supostamente encontra a posição x e y da flecha em certo tempo (acho que tem que botar o 0.5 tbm pra ser igual a dist)
			posX = voy * t;
			//System.out.println("VYI: " + vyi);
			//System.out.println("T : " + t);
			posY = ((voy * t) - (0.5 * -9.8 * t * t));
			System.out.println("X: " + Math.ceil(posX));
			//System.out.println("Y: " + Math.ceil(posY));
			//System.out.println(cont);
		}
		*/
		// Melhorar esta implementaçã que mostra onde a flecha cai
		lblDistncia.setText("Distância: " + Math.ceil(posX));
		lblDistncia.setBounds((int) (jogo.getJogador1().getX() + posX), 416, 120, 45);
		lblDistncia.setVisible(true);
		return 0;
	}
}
