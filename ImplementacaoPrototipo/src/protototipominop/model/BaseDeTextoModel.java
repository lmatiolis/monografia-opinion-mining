/*Classe: BaseDeTextoModel
  Pacote: protototipominop.model
  Funcionalidade: Essa classe representa os atributos de uma Base de Textos.
  É responsável pelos métodos get e set de cada atributo.
  Autor: Leandro Matioli Santos
*/

package protototipominop.model;

import java.util.ArrayList;

/**
 *
 * @author Leandro
 */
public class BaseDeTextoModel {

    private String nome;
    private String caminhoAbsoluto;
    private ArrayList<String> dicionario;
    private ArrayList<ArrayList> listaInvertidaDocumentos;
    private int qtdTextos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoAbsoluto() {
        return caminhoAbsoluto;
    }

    public void setCaminhoAbsoluto(String caminhoAbsoluto) {
        this.caminhoAbsoluto = caminhoAbsoluto;
    }

    public int getQtdTextos() {
        return qtdTextos;
    }

    public void setQtdTextos(int qtdTextos) {
        this.qtdTextos = qtdTextos;
    }

    public ArrayList<String> getDicionario() {

        if(dicionario == null){
            dicionario = new ArrayList<String>();
        }
        
        return dicionario;

    }

    public void setDicionario(ArrayList<String> dicionario) {
        this.dicionario = dicionario;
    }

    public ArrayList<ArrayList> getListaInvertidaDocumentos() {

        if(listaInvertidaDocumentos == null){
            listaInvertidaDocumentos = new ArrayList<ArrayList>();
        }

        return listaInvertidaDocumentos;
    }

    public void setListaInvertidaDocumentos(ArrayList<ArrayList> listaInvertidaDocumentos) {
        this.listaInvertidaDocumentos = listaInvertidaDocumentos;
    }



}
