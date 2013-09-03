/*Classe: TelaRemocoes
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela na qual deve-se especificar
  quais tipos de palavras devem ser removidas.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Leandro
 */
public class TelaRemocoes extends javax.swing.JPanel {

    /** Creates new form TelaRemocoes */
    public TelaRemocoes() {
        initComponents();

        barraProgRemocoes.setVisible(false);

        labelDicionario.setVisible(false);
        barraProgDicionario.setVisible(false);

        barraProgDicionario.setStringPainted(true);
        barraProgDicionario.setMaximum(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoSelecioneRemocoes = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        radBtnStopwords = new javax.swing.JRadioButton();
        radBtnPontuacao = new javax.swing.JRadioButton();
        radBtnLinksHttp = new javax.swing.JRadioButton();
        radBtnTwitterWords = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnIniciarRemocao = new javax.swing.JButton();
        barraProgRemocoes = new javax.swing.JProgressBar();
        btnPularEtapa = new javax.swing.JButton();
        barraProgDicionario = new javax.swing.JProgressBar();
        labelDicionario = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(733, 428));

        textoSelecioneRemocoes.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoSelecioneRemocoes.setText("Selecione as remoções desejadas na sua coleção de textos:");

        radBtnStopwords.setText("Stopwords");

        radBtnPontuacao.setSelected(true);
        radBtnPontuacao.setText("Pontuacao");

        radBtnLinksHttp.setSelected(true);
        radBtnLinksHttp.setText("Links Http");

        radBtnTwitterWords.setSelected(true);
        radBtnTwitterWords.setText("Twitter (palavras específicas)");

        btnIniciarRemocao.setText("Iniciar Remoção");
        btnIniciarRemocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarRemocaoActionPerformed(evt);
            }
        });

        btnPularEtapa.setText("Pular Etapa");
        btnPularEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPularEtapaActionPerformed(evt);
            }
        });

        labelDicionario.setText("Gerando dicionário:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(textoSelecioneRemocoes))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radBtnPontuacao)
                    .addComponent(radBtnStopwords)
                    .addComponent(radBtnLinksHttp)
                    .addComponent(radBtnTwitterWords))
                .addGap(257, 257, 257))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDicionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barraProgDicionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnIniciarRemocao, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(btnPularEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(barraProgRemocoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(290, 290, 290))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoSelecioneRemocoes)
                .addGap(55, 55, 55)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(radBtnStopwords)
                .addGap(18, 18, 18)
                .addComponent(radBtnPontuacao)
                .addGap(18, 18, 18)
                .addComponent(radBtnLinksHttp)
                .addGap(18, 18, 18)
                .addComponent(radBtnTwitterWords)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIniciarRemocao)
                .addGap(18, 18, 18)
                .addComponent(btnPularEtapa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barraProgRemocoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(barraProgDicionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDicionario))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarRemocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarRemocaoActionPerformed

        //Verifica se alguma opção de remoção foi selecionada
        if(radBtnLinksHttp.isSelected() || radBtnPontuacao.isSelected() || radBtnStopwords.isSelected() || radBtnTwitterWords.isSelected()){

            barraProgRemocoes.setMaximum(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
            barraProgRemocoes.setVisible(true);
            barraProgRemocoes.setStringPainted(true);

            radBtnLinksHttp.setEnabled(false);
            radBtnPontuacao.setEnabled(false);
            radBtnStopwords.setEnabled(false);
            radBtnTwitterWords.setEnabled(false);

            btnPularEtapa.setEnabled(false);
            btnIniciarRemocao.setEnabled(false);

            //Cria um SwingWorker para não travar a tela e preencher a barra de progresso
            new SwingWorker <Object, Object>() {

                @Override
                protected Object doInBackground() throws Exception {

                    TelaPrincipal.baseDeTextoController.realizaPreProcessamentoIntermediario(true, radBtnLinksHttp.isSelected(), radBtnTwitterWords.isSelected(),
                                                                                             radBtnStopwords.isSelected(), radBtnPontuacao.isSelected(),
                                                                                             TelaPrincipal.baseDeTextoController.baseDeTextoModel);

                    TelaPrincipal.btnProximo.setEnabled(true);

                    return null;
                }


            }.execute();

            
            
        } else {
            
            JOptionPane.showMessageDialog(null, "Por favor, selecione pelo menos uma opção.", "Erro na operação!", JOptionPane.WARNING_MESSAGE);
            
        }


    }//GEN-LAST:event_btnIniciarRemocaoActionPerformed

    private void btnPularEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPularEtapaActionPerformed

        //Coloca todas as opções como não-selecionadas e as deixa indisponível para click
        radBtnLinksHttp.setEnabled(false);
        radBtnLinksHttp.setSelected(false);
        radBtnPontuacao.setEnabled(false);
        radBtnPontuacao.setSelected(false);
        radBtnStopwords.setEnabled(false);
        radBtnStopwords.setSelected(false);
        radBtnTwitterWords.setEnabled(false);
        radBtnTwitterWords.setSelected(false);

        btnIniciarRemocao.setEnabled(false);
        btnPularEtapa.setEnabled(false);

        //Chama o metodo realizaPreProcessamento apenas para gerar o dicionario
        TelaPrincipal.baseDeTextoController.realizaPreProcessamentoIntermediario(true, radBtnLinksHttp.isSelected(), radBtnTwitterWords.isSelected(),
                                                                                 radBtnStopwords.isSelected(), radBtnPontuacao.isSelected(),
                                                                                 TelaPrincipal.baseDeTextoController.baseDeTextoModel);

        TelaPrincipal.btnProximo.setEnabled(true);

}//GEN-LAST:event_btnPularEtapaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar barraProgDicionario;
    public static javax.swing.JProgressBar barraProgRemocoes;
    private javax.swing.JButton btnIniciarRemocao;
    private javax.swing.JButton btnPularEtapa;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JLabel labelDicionario;
    private javax.swing.JRadioButton radBtnLinksHttp;
    private javax.swing.JRadioButton radBtnPontuacao;
    private javax.swing.JRadioButton radBtnStopwords;
    private javax.swing.JRadioButton radBtnTwitterWords;
    private javax.swing.JLabel textoSelecioneRemocoes;
    // End of variables declaration//GEN-END:variables

}