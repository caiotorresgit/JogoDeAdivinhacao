package apresentacao;

import javax.swing.JOptionPane;

public class Tela {

	public void mensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);	
	}
	
	public String entradaDados(String msg) {
		String nome = JOptionPane.showInputDialog(msg);
		return nome;
	}
}
