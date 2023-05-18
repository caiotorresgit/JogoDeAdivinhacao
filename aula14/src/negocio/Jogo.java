package negocio;

import apresentacao.*;
import java.util.Random;
import java.util.InputMismatchException;

public class Jogo {

	private Jogador jogador;
	private Tela tela;
	private Terminal terminal;
	private int numeroEscolhido;
	private boolean jogando;

	public Jogo() {
		tela = new Tela();
		terminal = new Terminal();
		jogador = new Jogador();
		numeroEscolhido = 50;
		jogando = true;
	}

	private void gerarNumeroAleatorio() {
		Random random = new Random();
		numeroEscolhido = random.nextInt(100) + 1;
	}

	public void inciarJogoTerminal() {
		try {
			gerarNumeroAleatorio();

			jogador.setNome(terminal.entradaNome());
			terminal.mensagem("Seja bem vindo: " + jogador.getNome());
		} catch (InputMismatchException e) {
			System.out.println("Erro: Entrada inválida.");
			System.exit(1);
		}
	}

	public void inciarJogoGUI() {
		try {
			gerarNumeroAleatorio();

			jogador.setNome(tela.entradaDados("Qual é o seu nome?"));
			tela.mensagem("Seja bem vindo: " + jogador.getNome());
			jogadas();
		} catch (InputMismatchException e) {
			tela.mensagem("Erro: Entrada inválida. O jogo será encerrado.");
			System.exit(1);
		}
	}

	public int solicitarNumero() {
		String numero = tela.entradaDados("Informe um número:");
		return Integer.parseInt(numero);
	}

	public void jogadas() {
		do {
			verificarAcerto();
		} while (jogando);

		fimDoJogo();
	}

	private void fimDoJogo() {
		String numeros = "";
		for (Integer numero : jogador.getListaNumeros()) {
			numeros += " - " + numero;
		}
		tela.mensagem("Números apostados: " + numeros);
	}

	public boolean verificarMenor(int numero) {
		if (numero < numeroEscolhido)
			return true;

		return false;
	}

	public void verificarAcerto() {
		int numero = Integer.parseInt(tela.entradaDados("Informe um número:"));
		jogador.addNumero(numero);

		if (numero == numeroEscolhido) {
			tela.mensagem("Parabéns, você acertou! Número de tentativas: " + jogador.getNumeroTentativa());
			jogando = false;
		} else {
			tela.mensagem("Deu ruim, você errou. Tente um número " + (numero < numeroEscolhido ? "maior" : "menor"));
			jogador.setNumeroTentativa();
		}
	}
}
