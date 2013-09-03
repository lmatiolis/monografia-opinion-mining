/*Classe: TelaAmostra
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela na qual serão exibidas as amostras
  de uma base de texto classificada.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class TelaAmostra extends javax.swing.JFrame {

    /** Creates new form TelaAmostra */
    public TelaAmostra() {
        initComponents();

        textAreaAmostra.setEditable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelAmostraClassificacao = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaAmostra = new javax.swing.JTextArea();
        labelQtdAmostra = new javax.swing.JLabel();
        tFQtdAmostra = new javax.swing.JTextField();
        btnExibirAmostra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        labelAmostraClassificacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelAmostraClassificacao.setText("Amostra da classificação:");

        textAreaAmostra.setColumns(20);
        textAreaAmostra.setRows(5);
        jScrollPane1.setViewportView(textAreaAmostra);

        labelQtdAmostra.setText("Indique a quantidade desejada: ");

        btnExibirAmostra.setText("Exibir amostra");
        btnExibirAmostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExibirAmostraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(labelAmostraClassificacao))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(labelQtdAmostra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tFQtdAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExibirAmostra)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAmostraClassificacao)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQtdAmostra)
                    .addComponent(tFQtdAmostra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExibirAmostra))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        //Reativa os botoes de visualizacao de amostra de acordo com a tela que esta no momento
        if(TelaPrincipal.telaResultadosAnteriores){
            
            TelaResultadosAnteriores.btnAmostraNegativa.setEnabled(true);
            TelaResultadosAnteriores.btnAmostraPositiva.setEnabled(true);
            
        } else {
            
            TelaResultado.btnAmostraPositiva.setEnabled(true);
            TelaResultado.btnAmostraNegativa.setEnabled(true);
            
        }


        TelaPrincipal.btnAnterior.setEnabled(true);
        TelaPrincipal.menuArquivo.setEnabled(true);
        TelaPrincipal.menuColeta.setEnabled(true);
        TelaPrincipal.menuResultado.setEnabled(true);


    }//GEN-LAST:event_formWindowClosed

    private void btnExibirAmostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExibirAmostraActionPerformed

        boolean digitouInteiro = false;
        int qtdClasseEmQuestao = 0;
        int classeEmQuestao;

        //Se for a tela proveniente do processo inteiro
        if(!TelaPrincipal.telaResultadosAnteriores){

            //Seta a classe de acordo com a TelaResultado
            if(TelaResultado.amostraSelecionada.equals(TelaResultado.labelTextoClassePositiva.getText().split(":")[0])){

                qtdClasseEmQuestao = TelaResultado.graficoModel.getQtdClasse1();
                classeEmQuestao = 1;

            } else {

                qtdClasseEmQuestao = TelaResultado.graficoModel.getQtdClasse0();
                classeEmQuestao = 0;

            }

        } else{

            //Seta a classe de acordo com a TelaResultadosAnteriores
            if(TelaResultadosAnteriores.amostraSelecionada.equals(TelaResultadosAnteriores.labelTextoClassePositiva.getText())){

                qtdClasseEmQuestao = Integer.valueOf(TelaResultadosAnteriores.labelQtdClassePositiva.getText().split(" ")[0]);
                classeEmQuestao = 1;

            } else {

                qtdClasseEmQuestao = Integer.valueOf(TelaResultadosAnteriores.labelQtdClasseNegativa.getText().split(" ")[0]);
                classeEmQuestao = 0;

            }

        }

        //Verifica se digitou um valor inteiro
        try{

            Integer.valueOf(tFQtdAmostra.getText());
            digitouInteiro = true;

        } catch (Exception ex){

            JOptionPane.showMessageDialog(null, "Por favor, digite um valor inteiro.", "Erro na operação!", JOptionPane.WARNING_MESSAGE);
        }

        if(digitouInteiro){

            //Verifica se a quantidade requisitada não excede a disponível
            if(Integer.valueOf(tFQtdAmostra.getText()) > qtdClasseEmQuestao ){

                JOptionPane.showMessageDialog(null, "Valor acima do disponível. Digite um numero menor que " + qtdClasseEmQuestao + ".", "Erro na operação!", JOptionPane.WARNING_MESSAGE);

            } else{

                //Retorna a amostra de acordo com a classe em questão.
                if(classeEmQuestao == 1){

                    textAreaAmostra.setText(TelaPrincipal.baseDeTextoController.retornaAmostraClassificada(TelaPrincipal.baseDeTextoController.baseDeTextoModel, Integer.valueOf(tFQtdAmostra.getText()), 1));

                } else {

                    textAreaAmostra.setText(TelaPrincipal.baseDeTextoController.retornaAmostraClassificada(TelaPrincipal.baseDeTextoController.baseDeTextoModel, Integer.valueOf(tFQtdAmostra.getText()), 0));

                }

            }

        }


    }//GEN-LAST:event_btnExibirAmostraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExibirAmostra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JLabel labelAmostraClassificacao;
    private javax.swing.JLabel labelQtdAmostra;
    private javax.swing.JTextField tFQtdAmostra;
    private javax.swing.JTextArea textAreaAmostra;
    // End of variables declaration//GEN-END:variables

}
