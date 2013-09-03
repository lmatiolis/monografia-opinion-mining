/*Classe: TelaGeraTfIdf
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela para cálculo do Tf-Idf de uma coleção
  quando o usuario nao deseja realizar treinamento nem classificação futura, apenas
  transformar o documento em representação vetorial.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingWorker;

/**
 *
 * @author Leandro
 */
public class TelaGeraTfIdf extends javax.swing.JPanel {

    /** Creates new form TelaGeraTfIdf */
    public TelaGeraTfIdf() {
        initComponents();

        //Define a quantidade total da barra de progresso
        barraProgTfIdf.setMaximum(TelaQuantidadeTextos.qtdTotalTextos - (TelaQuantidadeTextos.qtdSolicitadaTeste + TelaQuantidadeTextos.qtdSolicitadaTreino));
        barraProgTfIdf.setStringPainted(true);

        //Cria um SwingWorker para não travar a tela e preencher a barra de progresso
        new SwingWorker() {


            //Realiza a conversão do texto para sua representacao em vetor de caracteristicas
            @Override
            protected Object doInBackground() throws Exception {
                
                int contador = 1;
                
                try {

                    Scanner leitor = new Scanner(new File(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAO"));

                    try {

                        BufferedWriter escritorGeral = new BufferedWriter(new FileWriter(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAO-PREPROCESSADO"));

                        while(leitor.hasNext()){

                            escritorGeral.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(leitor.nextLine(), 0));
                            escritorGeral.newLine();
                            
                            barraProgTfIdf.setValue(contador);
                            
                            contador++;

                        }

                        leitor.close();
                        escritorGeral.close();

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                
                return null;
                
            }
        }.execute();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCalculandoTfIdf = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        barraProgTfIdf = new javax.swing.JProgressBar();

        setPreferredSize(new java.awt.Dimension(733, 428));

        tituloCalculandoTfIdf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloCalculandoTfIdf.setText("Calculando apenas o Tf-Idf do arquivo desejado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(tituloCalculandoTfIdf)
                            .addGap(166, 166, 166)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(barraProgTfIdf, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloCalculandoTfIdf)
                .addGap(107, 107, 107)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(barraProgTfIdf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgTfIdf;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel tituloCalculandoTfIdf;
    // End of variables declaration//GEN-END:variables

}