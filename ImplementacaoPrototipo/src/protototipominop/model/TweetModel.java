/*Classe: TweetModel
  Pacote: protototipominop.model
  Funcionalidade: Essa classe representa os atributos de um Tweet.
  É responsável pelos métodos get e set de cada atributo.
  Autor: Leandro Matioli Santos
*/

package protototipominop.model;

import java.util.Date;

/**
 *
 * @author Leandro
 */
public class TweetModel {

    private String tweet;
    private String autor;
    private Date data;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
