
public class Jogo {

	Jogador jogador1, jogador2;
	Jogador jogador;
	boolean turno = false;
	
	Jogo(Jogador jogador1) { // , Jogador jogador2 Quando usar NETGAME
		this.jogador1 = jogador1;
		jogador1.setX(33);
		escolherJogador();
	}
	
	public void escolherJogador() {
		jogador = getJogador1();
	}

	public void iniciarJogo() {
		Cenario.cenario(this);
	}

	public Jogador getJogador() {
		return jogador;
	}
	
	public Jogador getJogador1() {
		return jogador1;
	}

	public void atualizarPontos() {
		Cenario.atualizarPontos(this);
	}
	public void atualizarCenario() {
		Cenario.atualizarCenario(this);
	}
}
