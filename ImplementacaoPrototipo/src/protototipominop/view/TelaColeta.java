/*Classe: TelaColeta
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela responsável por realizar a coleta
  de mensagens no Twitter.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TelaColeta extends javax.swing.JPanel {

    /** Creates new form TelaColeta */
    public TelaColeta() {
        initComponents();

        barraProgColeta.setIndeterminate(true);

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
        barraProgColeta = new javax.swing.JProgressBar();
        btnFinalizarColeta = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(733, 428));

        textoConverterJson.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoConverterJson.setText("Coletando mensagens do Twitter...");

        btnFinalizarColeta.setText("Finalizar Coleta");
        btnFinalizarColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarColetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(textoConverterJson))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(btnFinalizarColeta))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(barraProgColeta, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoConverterJson)
                .addGap(74, 74, 74)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(barraProgColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnFinalizarColeta)
                .addContainerGap(109, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarColetaActionPerformed

        TelaPrincipal.baseDeTextoController.finalizaColeta();

        barraProgColeta.setIndeterminate(false);
        barraProgColeta.setEnabled(false);

        btnFinalizarColeta.setEnabled(false);

        JOptionPane.showMessageDialog(null, "Coleta realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_btnFinalizarColetaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgColeta;
    private javax.swing.JButton btnFinalizarColeta;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel textoConverterJson;
    // End of variables declaration//GEN-END:variables

}
