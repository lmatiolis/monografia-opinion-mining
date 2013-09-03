/*Classe: TelaSaidaClassificacao
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela responsável por exibir a saída
  do svmlight quando realiza-se a classificação.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingWorker;


/**
 *
 * @author Leandro
 */
public class TelaSaidaClassificacao extends javax.swing.JPanel {

    /** Creates new form TelaSaidaClassificacao */
    public TelaSaidaClassificacao() {
        initComponents();

        btnConfirmaClassificacao.setEnabled(false);

        textAreaSaidaClassificacao.setEditable(false);

        //Se o arquivo não estiver pre-processado ainda, habilita-se as barras de progresso
        if(!TelaUtilizaModeloExistente.jaPreProcessado){

            barraProgGerandoPreProcessado.setVisible(true);
            barraProgGerandoPreProcessado.setStringPainted(true);
            
        } else {
            
            barraProgGerandoPreProcessado.setVisible(false);
            labelGerandoPreProcessado.setVisible(false);
            
        }
     

        //Define a quantidade maxima da barra de progresso de acordo com o processo sendo executado
        //Pode ser o processo inteiro ou a utilizacao de um modelo existente
        if(TelaPrincipal.ehProcessoInteiro){

            barraProgGerandoPreProcessado.setMaximum(TelaQuantidadeTextos.qtdTotalTextos - (TelaQuantidadeTextos.qtdSolicitadaTeste + TelaQuantidadeTextos.qtdSolicitadaTreino));

        } else{

            barraProgGerandoPreProcessado.setMaximum(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());

        }

        //Inicializa a barra com o valor 0
        barraProgGerandoPreProcessado.setValue(0);

        //Cria um SwingWorker para não travar a tela e preencher a barra de progresso
        new SwingWorker <Object, Object>() {

            @Override
            protected Object doInBackground() throws Exception {

                //Se o arquivo não estiver pre-processado ainda, realiza-se o preprocessamento
                if(!TelaUtilizaModeloExistente.jaPreProcessado){

                    Scanner leitor;
                    String linha;

                    //Abre o leitor de acordo com o processo sendo executado
                    if(TelaPrincipal.ehProcessoInteiro){

                        leitor = new Scanner(new File(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto()+"-CLASSIFICACAO"));

                    } else{

                        leitor = new Scanner(new File(TelaUtilizaModeloExistente.arquivoClassifica + "-TOKENSREMOVIDOS"));

                    }

                    try {

                        BufferedWriter escritor;

                        //Abre o escritor de acordo com o processo sendo executado
                        if(TelaPrincipal.ehProcessoInteiro){

                             escritor = new BufferedWriter(new FileWriter(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAO-PREPROCESSADO"));

                        } else {

                            escritor = new BufferedWriter(new FileWriter(TelaUtilizaModeloExistente.arquivoClassifica + "-CLASSIFICACAO-PREPROCESSADO"));

                        }

                        //Realiza o preprocessamento
                        while(leitor.hasNext()){

                            linha = leitor.nextLine();


                            escritor.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(linha, 0));
                            escritor.newLine();

                            StringBuilder saidaBarra = new StringBuilder();
                            saidaBarra.append(barraProgGerandoPreProcessado.getValue() + 1);
                            saidaBarra.append(" de ");
                            saidaBarra.append(barraProgGerandoPreProcessado.getMaximum());
                            saidaBarra.append(" docs");

                            barraProgGerandoPreProcessado.setValue(barraProgGerandoPreProcessado.getValue() + 1);
                            barraProgGerandoPreProcessado.setString(saidaBarra.toString());

                        }

                        escritor.close();
                        leitor.close();

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                }

                String arquivoParaClassificar = "";
                String arquivoModelo = "";
                String saidaSVM = "";

                if(TelaPrincipal.ehProcessoInteiro){

                    arquivoParaClassificar = TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto()+"-CLASSIFICACAO-PREPROCESSADO";
                    arquivoModelo = "./svm/modelosSvm/" + TelaPrincipal.baseDeTextoController.retornaNomeArquivo(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto()) + "-MODELOSVM";
                    saidaSVM = TelaPrincipal.baseDeTextoController.retornaNomeArquivo(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto()) + "-SAIDACLASSIFICACAO";

                } else {

                    //Seta o caminho do arquivo adequadamente. Se ja for pre-processado, pega-se o nome do arquivo como esta
                    //Se ele foi preprocessado agora, adiciona-se a sring padrao do prototipo
                    if(TelaUtilizaModeloExistente.jaPreProcessado){
                        arquivoParaClassificar = TelaUtilizaModeloExistente.arquivoClassifica;
                    } else {
                        arquivoParaClassificar = TelaUtilizaModeloExistente.arquivoClassifica+"-CLASSIFICACAO-PREPROCESSADO";
                    }

                    arquivoModelo = TelaUtilizaModeloExistente.arquivoModelo;
                    saidaSVM = TelaPrincipal.baseDeTextoController.retornaNomeArquivo(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto()) + "-SAIDACLASSIFICACAO";
                }

                //Realiza a classificacao de acordo com os arquivos passados
                TelaPrincipal.baseDeTextoController.realizaClassificacao(arquivoParaClassificar, arquivoModelo, saidaSVM);

                btnConfirmaClassificacao.setEnabled(true);

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

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaSaidaClassificacao = new javax.swing.JTextArea();
        textoSaidaSVMClassificacao = new javax.swing.JLabel();
        barraProgGerandoPreProcessado = new javax.swing.JProgressBar();
        labelGerandoPreProcessado = new javax.swing.JLabel();
        btnConfirmaClassificacao = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(733, 428));

        textAreaSaidaClassificacao.setColumns(20);
        textAreaSaidaClassificacao.setRows(5);
        jScrollPane1.setViewportView(textAreaSaidaClassificacao);

        textoSaidaSVMClassificacao.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoSaidaSVMClassificacao.setText("Saída do SVM (Classificação):");

        labelGerandoPreProcessado.setText("Gerando o arquivo pré-processado para classificar:");

        btnConfirmaClassificacao.setText("Confirma Classificação");
        btnConfirmaClassificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmaClassificacaoMouseClicked(evt);
            }
        });
        btnConfirmaClassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaClassificacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(textoSaidaSVMClassificacao))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGerandoPreProcessado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmaClassificacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barraProgGerandoPreProcessado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57)))
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoSaidaSVMClassificacao)
                .addGap(58, 58, 58)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelGerandoPreProcessado)
                .addGap(27, 27, 27)
                .addComponent(barraProgGerandoPreProcessado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnConfirmaClassificacao)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmaClassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaClassificacaoActionPerformed
        TelaPrincipal.btnProximo.setEnabled(true);

        labelGerandoPreProcessado.setEnabled(false);
        barraProgGerandoPreProcessado.setEnabled(false);

        btnConfirmaClassificacao.setEnabled(false);

        textAreaSaidaClassificacao.setEnabled(false);
        
}//GEN-LAST:event_btnConfirmaClassificacaoActionPerformed

    private void btnConfirmaClassificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmaClassificacaoMouseClicked
        TelaPrincipal.btnProximo.setEnabled(true);
}//GEN-LAST:event_btnConfirmaClassificacaoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgGerandoPreProcessado;
    public static javax.swing.JButton btnConfirmaClassificacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelGerandoPreProcessado;
    public static javax.swing.JTextArea textAreaSaidaClassificacao;
    private javax.swing.JLabel textoSaidaSVMClassificacao;
    // End of variables declaration//GEN-END:variables

}
