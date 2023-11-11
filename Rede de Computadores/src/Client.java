import javax.swing.JOptionPane;
import java.io.* ;   // streams
import java.net.* ;  // sockets
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String ipServer = JOptionPane.showInputDialog("IP da Máquina Server");
		int op = -1;
		while(op != 0){
			op = Integer.parseInt(JOptionPane.showInputDialog("1 = Criptografar\n2 = Descriptografar\n0 = Sair"));
			if(op == 0) {
				break;
			}
			if(op <= 2){
				String frase = JOptionPane.showInputDialog("Frase");
				int chave = 0;
				chave = Integer.parseInt(JOptionPane.showInputDialog("Chave (0 - 25)"));
				
				while(chave >= 26) {
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					chave = Integer.parseInt(JOptionPane.showInputDialog("Chave (0 - 25)"));
				}
				Socket clientSocket = new Socket(ipServer, 5734);
				
				DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream());
				
				outbound.writeInt(op);
				outbound.writeInt(chave);
				outbound.writeUTF(frase);

				String fraseAtt = inbound.readUTF();
				
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
				String text = fraseAtt; 
				StringSelection selection = new StringSelection(text); clipboard.setContents(selection, null);
				
				JOptionPane.showMessageDialog(null, "Frase Original: "+frase+"\nFrase: "+fraseAtt+"\n\nFrase Copiada Para a Área de Transfêrencia");
				
				inbound.close () ;
				outbound.close () ;

				clientSocket.close() ;
			}else {
				JOptionPane.showMessageDialog(null, "Opção Inválida");
			}
		}
	}
}
