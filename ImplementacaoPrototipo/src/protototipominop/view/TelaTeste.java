/*Classe: TelaTeste
  Pacote: protototipominop.view
  Funcionalidade: Essa classe representa a tela na qual serão exibidas as mensagens
  para serem classificadas e que serão utilizadas para teste.
  Autor: Leandro Matioli Santos
*/

package protototipominop.view;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Leandro
 */
public class TelaTeste extends javax.swing.JPanel {

    Scanner leitorTeste;
    Scanner leitorTesteNormal;
    String linhaLida;
    String linhaLidaNormal;
    BufferedWriter escritor;
    int contadorDocs = 1;

    /** Creates new form TelaTeste */
    public TelaTeste() {

        //Trata o caso do usuario ter optado por não realizar teste ao selecionar 0 docs para esta função
        if(TelaQuantidadeTextos.qtdSolicitadaTeste > 0){
            initComponents();
            
            //Nao permite que se digite texto na TextArea
            textAreaDocumento.setEditable(false);

            //Adiciona ao grupo de botoes para permitir a selecao de apenas um por vez
            btnGrupoClasses.add(radBtnClasseNegativa);
            btnGrupoClasses.add(radBtnClassePositiva);

            String[] classes = TelaDescreveClassificacao.classificacaoEscolhida.split("/");

            radBtnClassePositiva.setText(classes[0]);
            radBtnClasseNegativa.setText(classes[1]);

            //Mostra na tela a quantidade total de documentos no arquivo selecionado
            labelTotalDocs.setText("de " + TelaQuantidadeTextos.qtdSolicitadaTeste + " documentos de teste");
            labelIndiceDocEmQuestao.setText(Integer.toString(contadorDocs));

            //Tenta abrir o leitor para o arquivo contendo os documentos de teste
            try {

                leitorTeste = new Scanner(new File(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-TESTE"));
                leitorTesteNormal = new Scanner(new File(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-TESTENORMAL"));

            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }

            //Tenta abrir o escritor do arquivo que ira conter as docs de teste preprocessados
            try {

                escritor = new BufferedWriter(new FileWriter(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getCaminhoAbsoluto() + "-TESTE-PREPROCESSADO"));

            } catch (IOException ex) {
                System.out.println(ex);
            }

            linhaLida = leitorTeste.nextLine();
            linhaLidaNormal = leitorTesteNormal.nextLine();

            //Mostra o documento inicial para classificacao
            textAreaDocumento.setText(linhaLidaNormal);

        } else {

            //TODO

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

        btnGrupoClasses = new javax.swing.ButtonGroup();
        textoSelecioneClasse = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        radBtnClassePositiva = new javax.swing.JRadioButton();
        radBtnClasseNegativa = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDocumento = new javax.swing.JTextArea();
        labelDocSendoAnalisado = new javax.swing.JLabel();
        labelIndiceDocEmQuestao = new javax.swing.JLabel();
        labelTotalDocs = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(733, 428));

        textoSelecioneClasse.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoSelecioneClasse.setText("Selecione a qual classe o documento pertence (Teste):");

        radBtnClassePositiva.setText("Classe Positiva");
        radBtnClassePositiva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radBtnClassePositivaMouseClicked(evt);
            }
        });

        radBtnClasseNegativa.setText("Classe Negativa");
        radBtnClasseNegativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radBtnClasseNegativaMouseClicked(evt);
            }
        });

        textAreaDocumento.setColumns(20);
        textAreaDocumento.setRows(5);
        jScrollPane1.setViewportView(textAreaDocumento);

        labelDocSendoAnalisado.setText("Documento sendo analisado: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(labelDocSendoAnalisado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIndiceDocEmQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalDocs, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(textoSelecioneClasse))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radBtnClasseNegativa)
                            .addComponent(radBtnClassePositiva)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoSelecioneClasse)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDocSendoAnalisado)
                    .addComponent(labelIndiceDocEmQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalDocs, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radBtnClassePositiva)
                .addGap(18, 18, 18)
                .addComponent(radBtnClasseNegativa)
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radBtnClassePositivaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radBtnClassePositivaMouseClicked

        //Limpa a selecao de todos os botoes. Eh chamado para limpar o click que acabou de ser realizado
        btnGrupoClasses.clearSelection();

        //Caso ainda tenha documentos a serem lidos
        if(leitorTeste.hasNext()){

            //Atualiza o contador na tela
            contadorDocs++;
            labelIndiceDocEmQuestao.setText(Integer.toString(contadorDocs));

            //Tenta escrever a linha preProcessada com a classe indicada pelo usuario
            try {

                escritor.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(linhaLida, 1));
                escritor.newLine();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            linhaLida = leitorTeste.nextLine();
            linhaLidaNormal = leitorTesteNormal.nextLine();

            //Mostra o proximo documento na tela para classificacao
            textAreaDocumento.setText(linhaLidaNormal);

        } else {

            //Escreve a ultima linha lida
            try {

                escritor.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(linhaLida, 1));
                escritor.newLine();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            //Coloca as opcoes escurecidas para impedir novas classificacoes e indicar fim de arquivo
            textAreaDocumento.setEnabled(false);
            radBtnClasseNegativa.setEnabled(false);
            radBtnClassePositiva.setEnabled(false);

            labelDocSendoAnalisado.setEnabled(false);
            labelIndiceDocEmQuestao.setEnabled(false);
            labelTotalDocs.setEnabled(false);
            //Fim trecho

            TelaPrincipal.btnProximo.setEnabled(true);

            //Fecha o escritor
            try {

                escritor.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

        }


    }//GEN-LAST:event_radBtnClassePositivaMouseClicked

    private void radBtnClasseNegativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radBtnClasseNegativaMouseClicked

        //Limpa a selecao de todos os botoes. Eh chamado para limpar o click que acabou de ser realizado
        btnGrupoClasses.clearSelection();

        //Caso ainda tenha documentos a serem lidos
        if(leitorTeste.hasNext()){

            //Atualiza o contador na tela
            contadorDocs++;
            labelIndiceDocEmQuestao.setText(Integer.toString(contadorDocs));

            //Tenta escrever a linha preProcessada com a classe indicada pelo usuario
            try {

                escritor.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(linhaLida, -1));
                escritor.newLine();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            linhaLida = leitorTeste.nextLine();
            linhaLidaNormal = leitorTesteNormal.nextLine();

            //Mostra o proximo documento na tela para classificacao
            textAreaDocumento.setText(linhaLidaNormal);

        } else {

            //Escreve a ultima linha lida
            try {

                escritor.write(TelaPrincipal.baseDeTextoController.realizaPreProcessamentoFinalLinhaALinha(linhaLida, -1));
                escritor.newLine();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            //Coloca as opcoes escurecidas para impedir novas classificacoes e indicar fim de arquivo
            textAreaDocumento.setEnabled(false);
            radBtnClasseNegativa.setEnabled(false);
            radBtnClassePositiva.setEnabled(false);

            labelDocSendoAnalisado.setEnabled(false);
            labelIndiceDocEmQuestao.setEnabled(false);
            labelTotalDocs.setEnabled(false);
            //Fim trecho

            TelaPrincipal.btnProximo.setEnabled(true);

            //Fecha o escritor
            try {

                escritor.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

        }

    }//GEN-LAST:event_radBtnClasseNegativaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrupoClasses;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelDocSendoAnalisado;
    private javax.swing.JLabel labelIndiceDocEmQuestao;
    private javax.swing.JLabel labelTotalDocs;
    private javax.swing.JRadioButton radBtnClasseNegativa;
    private javax.swing.JRadioButton radBtnClassePositiva;
    private javax.swing.JTextArea textAreaDocumento;
    private javax.swing.JLabel textoSelecioneClasse;
    // End of variables declaration//GEN-END:variables

}
