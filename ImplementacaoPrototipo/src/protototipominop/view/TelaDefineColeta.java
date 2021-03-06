/*Classe: TelaDefineColeta
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela na qual define-se os parâmetros para
  a realização da coleta de mensagens no Twitter.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TelaDefineColeta extends javax.swing.JPanel {

    /** Creates new form TelaDefineColeta */
    public TelaDefineColeta() {
        initComponents();

        tfArquivoTracking.setEditable(false);
        tfPalavrasChave.setToolTipText("Não utilize espaços e separe as palavras por vírgula.");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoConverterJson = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        labelNomeUsuario = new javax.swing.JLabel();
        tfNomeUsuario = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        pfSenha = new javax.swing.JPasswordField();
        labelArquivoPalavrasChave = new javax.swing.JLabel();
        btnAbrirArquivo = new javax.swing.JButton();
        tfArquivoTracking = new javax.swing.JTextField();
        labelArquivoSaida = new javax.swing.JLabel();
        tfArquivoSaida = new javax.swing.JTextField();
        btnConfirmaDados = new javax.swing.JButton();
        labelOu = new javax.swing.JLabel();
        labelPalavrasChav = new javax.swing.JLabel();
        tfPalavrasChave = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(733, 428));

        textoConverterJson.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoConverterJson.setText("Defina os parametros para coleta no Twitter:");

        labelNomeUsuario.setText("Nome de usuario: ");

        tfNomeUsuario.setText("testeMinera");

        labelSenha.setText("Senha: ");

        pfSenha.setText("teste123");

        labelArquivoPalavrasChave.setText("Arquivo com palavras-chave (tracking):");

        btnAbrirArquivo.setText("Abrir");
        btnAbrirArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirArquivoActionPerformed(evt);
            }
        });

        labelArquivoSaida.setText("Nome do arquivo de saída: ");

        btnConfirmaDados.setText("Confirma Dados");
        btnConfirmaDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaDadosActionPerformed(evt);
            }
        });

        labelOu.setText("ou");

        labelPalavrasChav.setText("Defina aqui as palavras-chave desejadas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(textoConverterJson))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelArquivoSaida)
                            .addComponent(labelSenha)
                            .addComponent(labelNomeUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(pfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(tfArquivoSaida)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelPalavrasChav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPalavrasChave, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(labelOu))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelArquivoPalavrasChave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbrirArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfArquivoTracking, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnConfirmaDados)
                                .addGap(302, 302, 302)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoConverterJson)
                .addGap(53, 53, 53)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeUsuario))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelArquivoSaida)
                    .addComponent(tfArquivoSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPalavrasChav)
                    .addComponent(tfPalavrasChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelOu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelArquivoPalavrasChave)
                    .addComponent(btnAbrirArquivo)
                    .addComponent(tfArquivoTracking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmaDados)
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmaDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaDadosActionPerformed

        if(tfArquivoSaida.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Por favor, preencha o campo especificando o arquivo de saída.", "Erro na operação!", JOptionPane.INFORMATION_MESSAGE);

        } else if(tfNomeUsuario.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Por favor, preencha campo especificando o nome de usuario.", "Erro na operação!", JOptionPane.INFORMATION_MESSAGE);

        }else if(pfSenha.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Por favor, preencha o campo especificando a senha.", "Erro na operação!", JOptionPane.INFORMATION_MESSAGE);

        } else if(tfArquivoTracking.getText().isEmpty() && tfPalavrasChave.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Por favor, especifique as palavras-chave desejada.", "Erro na operação!", JOptionPane.INFORMATION_MESSAGE);

        } else if(!tfArquivoTracking.getText().isEmpty() && !tfPalavrasChave.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Por favor, selecione apenas uma forma de definir as palavras-chave.", "Erro na operação!", JOptionPane.INFORMATION_MESSAGE);

        //Caso todos os campos estejam preenchidos, possibilita a passagem para a outra tela
        } else{

            //Caso as palavras-chave tenham sido especificadas pela interface, cria o arquivo tracking com elas
            if(!tfPalavrasChave.getText().isEmpty()){

                TelaPrincipal.baseDeTextoController.geraArquivoTracking(tfPalavrasChave.getText());
                
            }


            TelaPrincipal.btnProximo.setVisible(true);
            TelaPrincipal.btnProximo.setEnabled(true);

            btnAbrirArquivo.setEnabled(false);

            tfArquivoSaida.setEnabled(false);
            tfArquivoTracking.setEnabled(false);
            tfNomeUsuario.setEnabled(false);
            pfSenha.setEnabled(false);
            tfPalavrasChave.setEnabled(false);

            labelArquivoSaida.setEnabled(false);
            labelNomeUsuario.setEnabled(false);
            labelArquivoPalavrasChave.setEnabled(false);
            labelSenha.setEnabled(false);
            labelPalavrasChav.setEnabled(false);
            labelOu.setEnabled(false);

            btnConfirmaDados.setEnabled(false);

        }




}//GEN-LAST:event_btnConfirmaDadosActionPerformed

    private void btnAbrirArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirArquivoActionPerformed

        tfPalavrasChave.setText("");

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.showOpenDialog(this);

        //Verifica se o nome do arquivo é "tracking"
        if(!fileChooser.getSelectedFile().getName().equalsIgnoreCase("tracking")){

            JOptionPane.showMessageDialog(null, "O arquivo selecionado deve possuir o nome tracking!", "Erro na operação", JOptionPane.INFORMATION_MESSAGE);

        } else {
            tfArquivoTracking.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }



    }//GEN-LAST:event_btnAbrirArquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirArquivo;
    private javax.swing.JButton btnConfirmaDados;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelArquivoPalavrasChave;
    private javax.swing.JLabel labelArquivoSaida;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelOu;
    private javax.swing.JLabel labelPalavrasChav;
    private javax.swing.JLabel labelSenha;
    public static javax.swing.JPasswordField pfSenha;
    private javax.swing.JLabel textoConverterJson;
    public static javax.swing.JTextField tfArquivoSaida;
    public static javax.swing.JTextField tfArquivoTracking;
    public static javax.swing.JTextField tfNomeUsuario;
    private javax.swing.JTextField tfPalavrasChave;
    // End of variables declaration//GEN-END:variables

}
