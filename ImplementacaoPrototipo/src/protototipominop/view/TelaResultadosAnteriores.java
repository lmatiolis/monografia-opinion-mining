/*Classe: TelaResultadosAnteriores
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela na qual pode-se visualizar resultados
  de classificações anteriores.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import protototipominop.controller.GraficoController;
import protototipominop.model.GraficoModel;

/**
 *
 * @author Leandro
 */
public class TelaResultadosAnteriores extends javax.swing.JPanel {

    public static GraficoModel graficoModel = new GraficoModel();
    public static String amostraSelecionada;
    ArrayList<GraficoModel> resultados = new ArrayList<GraficoModel>();
    GraficoController graficoController = new GraficoController();

    /** Creates new form TelaResultado */
    public TelaResultadosAnteriores() {
        initComponents();

        labelQtdClasseNegativa.setEnabled(false);
        labelQtdClassePositiva.setEnabled(false);
        labelTextoClasseNegativa.setEnabled(false);
        labelTextoClassePositiva.setEnabled(false);

        btnAmostraNegativa.setEnabled(false);
        btnAmostraPositiva.setEnabled(false);

        cbResultados.removeAllItems();

        try {

            resultados = graficoController.getGraficos();

            //Percorre os resultados e os adiciona à combobox
            for(int i = 0; i < resultados.size(); i++){

                cbResultados.addItem(i + " - " +
                                     resultados.get(i).getAssunto() + " - " +
                                     resultados.get(i).getTipoClassificacao() + " - " +
                                     resultados.get(i).getData());

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } 

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        painelGrafico = new javax.swing.JPanel();
        labelTextoClassePositiva = new javax.swing.JLabel();
        labelTextoClasseNegativa = new javax.swing.JLabel();
        labelQtdClassePositiva = new javax.swing.JLabel();
        labelQtdClasseNegativa = new javax.swing.JLabel();
        btnAmostraNegativa = new javax.swing.JButton();
        btnAmostraPositiva = new javax.swing.JButton();
        labelResultadosAnteriores = new javax.swing.JLabel();
        cbResultados = new javax.swing.JComboBox();
        btnOK = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(733, 428));

        painelGrafico.setLayout(new java.awt.GridLayout(1, 0));

        labelTextoClassePositiva.setText("Classe Positiva: ");

        labelTextoClasseNegativa.setText("Classe Negativa: ");

        labelQtdClassePositiva.setText("       ");

        labelQtdClasseNegativa.setText("   ");

        btnAmostraNegativa.setText("Amostra");
        btnAmostraNegativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmostraNegativaActionPerformed(evt);
            }
        });

        btnAmostraPositiva.setText("Amostra");
        btnAmostraPositiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmostraPositivaActionPerformed(evt);
            }
        });

        labelResultadosAnteriores.setText("Selecione o resultado que deseja rever: ");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(painelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelResultadosAnteriores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTextoClassePositiva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelQtdClassePositiva, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAmostraPositiva)
                        .addGap(51, 51, 51)
                        .addComponent(labelTextoClasseNegativa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelQtdClasseNegativa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAmostraNegativa)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelResultadosAnteriores)
                    .addComponent(cbResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQtdClasseNegativa)
                    .addComponent(labelTextoClasseNegativa)
                    .addComponent(labelTextoClassePositiva)
                    .addComponent(labelQtdClassePositiva)
                    .addComponent(btnAmostraNegativa)
                    .addComponent(btnAmostraPositiva))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAmostraPositivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmostraPositivaActionPerformed

        btnAmostraNegativa.setEnabled(false);
        btnAmostraPositiva.setEnabled(false);

        TelaPrincipal.btnAnterior.setEnabled(false);
        TelaPrincipal.menuArquivo.setEnabled(false);
        TelaPrincipal.menuColeta.setEnabled(false);
        TelaPrincipal.menuResultado.setEnabled(false);

        TelaAmostra telaAmostra = new TelaAmostra();

        amostraSelecionada = labelTextoClassePositiva.getText();

        telaAmostra.setTitle("Classe - " + amostraSelecionada);
        telaAmostra.labelAmostraClassificacao.setText("Amostra da classificação (" + amostraSelecionada + "):");
        
        telaAmostra.setVisible(true);



    }//GEN-LAST:event_btnAmostraPositivaActionPerformed

    private void btnAmostraNegativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmostraNegativaActionPerformed

        btnAmostraNegativa.setEnabled(false);
        btnAmostraPositiva.setEnabled(false);

        TelaPrincipal.btnAnterior.setEnabled(false);
        TelaPrincipal.menuArquivo.setEnabled(false);
        TelaPrincipal.menuColeta.setEnabled(false);
        TelaPrincipal.menuResultado.setEnabled(false);

        TelaAmostra telaAmostra = new TelaAmostra();

        amostraSelecionada = labelTextoClasseNegativa.getText();

        telaAmostra.setTitle("Classe - " + amostraSelecionada);
        telaAmostra.labelAmostraClassificacao.setText("Amostra da classificação (" + amostraSelecionada + "):");
        
        telaAmostra.setVisible(true);

    }//GEN-LAST:event_btnAmostraNegativaActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed

        painelGrafico.removeAll();

        String selecionado = cbResultados.getSelectedItem().toString();

        System.out.println(selecionado);

        int indiceGrafico = Integer.valueOf(selecionado.split(" - ")[0]);

        JFreeChart grafico = graficoController.geraGrafico(resultados.get(indiceGrafico));

        if(resultados.get(indiceGrafico).getTipoClassificacao().equals("Modelo Existente")){

            labelTextoClassePositiva.setText("Classe Positiva:");
            labelTextoClasseNegativa.setText("Claase Negativa:");

        } else {
            labelTextoClassePositiva.setText(resultados.get(indiceGrafico).getTipoClassificacao().split("/")[0] + ":");
            labelTextoClasseNegativa.setText(resultados.get(indiceGrafico).getTipoClassificacao().split("/")[1] + ":");
        }
        
        labelTextoClasseNegativa.setEnabled(true);
        labelTextoClassePositiva.setEnabled(true);

        System.out.println("Problema here");

        labelQtdClasseNegativa.setText(resultados.get(indiceGrafico).getQtdClasse0() + " documento(s)");
        labelQtdClassePositiva.setText(resultados.get(indiceGrafico).getQtdClasse1() + " documento(s)");
        labelQtdClasseNegativa.setEnabled(true);
        labelQtdClassePositiva.setEnabled(true);

        btnAmostraNegativa.setEnabled(true);
        btnAmostraPositiva.setEnabled(true);

        Component comp = new ChartPanel(grafico);

        painelGrafico.add(comp);
        painelGrafico.setVisible(true);

        painelGrafico.repaint();
        painelGrafico.validate();

        try {
            
            TelaPrincipal.baseDeTextoController.baseDeTextoModel = TelaPrincipal.baseDeTextoController.inserirBaseDeTextoUnicoArquivo(resultados.get(indiceGrafico).getArquivo());

        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("O erro é aqui!");
        }


    }//GEN-LAST:event_btnOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAmostraNegativa;
    public static javax.swing.JButton btnAmostraPositiva;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cbResultados;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JLabel labelQtdClasseNegativa;
    public static javax.swing.JLabel labelQtdClassePositiva;
    private javax.swing.JLabel labelResultadosAnteriores;
    public static javax.swing.JLabel labelTextoClasseNegativa;
    public static javax.swing.JLabel labelTextoClassePositiva;
    private javax.swing.JPanel painelGrafico;
    // End of variables declaration//GEN-END:variables

}