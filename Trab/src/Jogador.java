import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Jogador {

	String nome;
	int pontos;
	int x;
	int velX;
	int hp;
	JLabel label;
	JProgressBar progressBar;
	boolean vivo;
	
	public Jogador(String nome) {
		this.nome = nome;
		vivo = true;
		hp = 100;
		pontos = 10;
		label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/teste.png")).getImage(); 
		label.setIcon(new ImageIcon(img));
		
		progressBar = new JProgressBar();
		progressBar.setBackground(Color.BLUE);
		progressBar.setForeground(Color.BLUE);
		progressBar.setOrientation(SwingConstants.VERTICAL);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setX(int posX) {
		this.x = posX;
		label.setBounds(x, 480, 10, 20);
		progressBar.setBounds(x-10, 465, 5, hp/4);
	}
	
	public int getX() {
		return x;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public void setHP(int hp) {
		if (hp > 100) {
			this.hp = 100;
		}
		else if  (hp < 0) {
			this.hp = 0;
			vivo = false;
		}
		else {
			this.hp = hp;
		}
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public int getPontos() {
		return pontos;
	}

	public void receberDano() {
		this.hp -= 50;
	}

	
}
