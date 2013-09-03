/*Classe: TweetModel
  Pacote: protototipominop.model
  Funcionalidade: Essa classe representa os atributos de um Grafico.
  É responsável pelos métodos get e set de cada atributo.
  Autor: Leandro Matioli Santos
*/

package protototipominop.model;

import java.util.Date;

/**
 *
 * @author Leandro
 */
public class GraficoModel {
    private String arquivo;
    private String assunto;
    private int qtdClasse0;
    private int qtdClasse1;
    private String tipoClassificacao;
    private Date data;

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getQtdClasse0() {
        return qtdClasse0;
    }

    public void setQtdClasse0(int qtdClasse0) {
        this.qtdClasse0 = qtdClasse0;
    }

    public int getQtdClasse1() {
        return qtdClasse1;
    }

    public void setQtdClasse1(int qtdClasse1) {
        this.qtdClasse1 = qtdClasse1;
    }

    public String getTipoClassificacao() {
        return tipoClassificacao;
    }

    public void setTipoClassificacao(String tipoClassificacao) {
        this.tipoClassificacao = tipoClassificacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }



}
