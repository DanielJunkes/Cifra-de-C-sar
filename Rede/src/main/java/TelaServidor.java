
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaServidor extends javax.swing.JFrame {
    
    Server server;
    ServerSocket serverSocket;
    boolean isON = false;

    public TelaServidor() throws UnknownHostException {
        initComponents();
        String ip = InetAddress.getLocalHost().getHostAddress();
        lblIP.setText(ip);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLigarDesligar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblIP = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLigarDesligar.setText("Ligar Servidor");
        btnLigarDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLigarDesligarActionPerformed(evt);
            }
        });

        jLabel2.setText("IP do Servidor:");
        jLabel2.setToolTipText("");

        lblIP.setEditable(false);
        lblIP.setText("000.00.0.000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblStatus.setText("Servidor OFF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblStatus)
                .addComponent(btnLigarDesligar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblStatus)
                .addGap(40, 40, 40)
                .addComponent(btnLigarDesligar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLigarDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLigarDesligarActionPerformed
        if (isON) {
            try {
                serverSocket.close();
                server.interrupt();
                
                lblStatus.setText("Servidor OFF");
                lblStatus.setForeground(Color.red);
                
                btnLigarDesligar.setText("Ligar Servidor");
                
                isON = false;
            } catch (IOException ex) {
            } 
        } else {
            try {
                serverSocket = new ServerSocket(5734);
                server = new Server(serverSocket);
                
                server.start();
                
                lblStatus.setText("Servidor ON");
                lblStatus.setForeground(Color.GREEN);
                
                btnLigarDesligar.setText("Desligar Servidor");
                
                isON = true;
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_btnLigarDesligarActionPerformed

    public static void main(String args[]) throws IOException {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaServidor().setVisible(true);
            } catch (UnknownHostException ex) {
                Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLigarDesligar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lblIP;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
