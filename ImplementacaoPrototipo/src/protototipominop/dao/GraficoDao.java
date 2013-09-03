/*Classe: GraficoDao
  Pacote: protototipominop.dao
  Funcionalidade: Essa classe é responsável pelas operações
  relativas aos graficos gerados com o banco de dados.
  Autor: Leandro Matioli Santos
*/

package protototipominop.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import protototipominop.model.GraficoModel;

/**
 *
 * @author Leandro
 */
public class GraficoDao {

    SimpleDateFormat simpleDate = new SimpleDateFormat ("yyyy-MM-dd");

    //Método que insere no banco de dados as informações
    //de um resultado (grafico)
    public void inserir (GraficoModel graficomodel){

        Connection conn = ConnectionFactory.connect();
        
        try{
            
            Statement stm = conn.createStatement();

            //Coloca '>' no lugar \ no endereço do arquivo para evitar problemas no banco
            String arquivo = graficomodel.getArquivo().replaceAll("\\\\", ">");
        
            stm.executeUpdate("INSERT INTO GRAFICO (ARQUIVO, ASSUNTO, QTDCLASSE0, QTDCLASSE1, TIPOCLASSIFICACAO, DATACLASSIFICACAO) " +
                              "VALUES ('"+ arquivo + "', '" +
                              graficomodel.getAssunto()+"', " +
                              graficomodel.getQtdClasse0() + ", " +
                              graficomodel.getQtdClasse1() + ", '" +
                              graficomodel.getTipoClassificacao() + "', '" +
                              simpleDate.format(graficomodel.getData())+"');");

            stm.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        ConnectionFactory.disconnect();

    }

    //Método que retorna uma lista de objetos de GraficoMdeol contendo
    //todas os graficos que constam no banco de dados com seus atributos
    //devidamente preenchidos.
    public ArrayList getGraficos() throws SQLException{

        Connection conn = ConnectionFactory.connect();
        Statement stm = conn.createStatement();

        ResultSet rs = stm.executeQuery("SELECT * FROM GRAFICO;");

        ArrayList<GraficoModel> graficoModels = new ArrayList();

        while(rs.next()){

            GraficoModel graficoModel = new GraficoModel();

            graficoModel.setArquivo(rs.getString("ARQUIVO").replaceAll(">", "\\\\"));
            System.out.println(rs.getString("ARQUIVO"));
            System.out.println(graficoModel.getArquivo());
            graficoModel.setAssunto(rs.getString("ASSUNTO"));
            graficoModel.setQtdClasse0(rs.getInt("QTDCLASSE0"));
            graficoModel.setQtdClasse1(rs.getInt("QTDCLASSE1"));
            graficoModel.setTipoClassificacao(rs.getString("TIPOCLASSIFICACAO"));
            graficoModel.setData(rs.getDate("DATACLASSIFICACAO"));

            graficoModels.add(graficoModel);

        }

        return graficoModels;

    }

    //Metodo para retornar o nome do arquivo de um grafico no banco
    public String getCaminhoArquivo(String arquivo) throws SQLException{

        boolean achou = false;
        String caminhoArquivo = null;

        Connection conn = ConnectionFactory.connect();
        Statement stm = conn.createStatement();

        ResultSet rs = stm.executeQuery("SELECT ARQUIVO FROM GRAFICO;");

        while(rs.next() && achou == false){

            if(rs.getString("ARQUIVO").contains(arquivo)){
                achou = true;

                caminhoArquivo = rs.getString("ARQUIVO");
            }

        }

        System.out.println("arquivoDao-" + caminhoArquivo);

        return caminhoArquivo.replaceAll(">", "\\\\");
    }


}
