
import java.io.*;   // streams
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Server extends Thread {

    ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                String a[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                    "t", "u", "v", "w", "x", "y", "z"};

                ArrayList<String> alfabeto = new ArrayList<String>();
                for (int i = 0; i < a.length; i++) {
                    alfabeto.add(a[i]);
                }

                while (true) {
                    Socket connectionSocket = serverSocket.accept();

                    DataInputStream inbound = new DataInputStream(connectionSocket.getInputStream());
                    DataOutputStream outbound = new DataOutputStream(connectionSocket.getOutputStream());
                    
                    if (connectionSocket.isConnected()) {

                        int op = inbound.readInt();
                        int chave = inbound.readInt();
                        String frase = inbound.readUTF();
                        String fraseAtt = "";

                        for (int i = 0; i < frase.length(); i++) {
                            String letra = "";
                            char aux = frase.charAt(i);
                            if (aux == ' ') {
                                fraseAtt += aux;
                            } else {
                                letra += aux;
                                int indexLetra = alfabeto.indexOf(letra);
                                int position = 0;
                                if (op == 1) {
                                    position = indexLetra + chave;
                                    if (position > 26) {
                                        position -= 26;
                                    }
                                }
                                if (op == 2) {
                                    position = indexLetra - chave;
                                    if (position < 0) {
                                        position += 26;
                                    }
                                }
                                fraseAtt += alfabeto.get(position);
                            }
                        }
                        outbound.writeUTF(fraseAtt);
                    }
                    Thread.currentThread().sleep(1000);
                }
            }
        } catch (EOFException e) {
            
        } catch (IOException ex) {
           
        } catch (InterruptedException ex) {
            
        }
    }
}
