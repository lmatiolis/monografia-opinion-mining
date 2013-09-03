/*Classe: TweetController
  Pacote: protototipominop.controller
  Funcionalidade: Essa classe é responsável pelas operações
  relativas aos tweets coletados.
  Autor: Leandro Matioli Santos
*/

package protototipominop.controller;

import protototipominop.model.TweetModel;
import protototipominop.classesAuxiliares.JSONObject;

/**
 *
 * @author Leandro
 */
public class TweetController {

    //TODO
    //Metodo que converte um objeto JSON para um tweet mesmo, contendo autor,
    //a mensagem e a data. Nao foi implementado pois não se considerou necessario
    //para o prototipo até então.
    public TweetModel converteJsonParaTweet(String linha){
        
        TweetModel tweetModel = new TweetModel();

        JSONObject linhaJson = new JSONObject(linha);

        if(linhaJson.has("text")){

            tweetModel.setTweet(linhaJson.get("text").toString());
            tweetModel.setAutor(linhaJson.get("screen_name").toString());
            System.out.println(linhaJson.get("created_at").toString());
            //tweetModel.setData(linhaJson.get("created_at").toString());

        }

       return tweetModel;


    }

    //Metodo para recuperar de um objeto JSON apenas a mensagem postada pelo usuario.
    public String converteJsonParaMensagemApenas(String linha) {

        StringBuilder saida = new StringBuilder("");

        try{

            JSONObject linhaJson = new JSONObject(linha);
            
            if(linhaJson.has("text")){

                //Só copia se o texto não estiver vazio
                if(!linhaJson.get("text").toString().isEmpty()){

                    //Remove quaisquer quebra de linha que estejam no meio do texto
                    if(linhaJson.get("text").toString().contains("\n")){

                        saida.append(linhaJson.get("text").toString().replaceAll("\n", ""));

                    } else {

                        saida.append(linhaJson.get("text").toString());

                    }
                    
                }
                
            }
           
        } catch(Exception ex){
            System.out.println(ex);
            saida.append("LINHABRANCA");
        }

        return saida.toString();     

    }





}
