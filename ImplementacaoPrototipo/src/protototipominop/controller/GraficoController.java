/*Classe: GraficoController
  Pacote: protototipominop.controller
  Funcionalidade: Essa classe é responsável pelas operações
  relativas aos graficos gerados como resultado das classificações.
  Autor: Leandro Matioli Santos
*/


package protototipominop.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import protototipominop.dao.GraficoDao;
import protototipominop.model.GraficoModel;
import protototipominop.view.TelaDescreveClassificacao;
import protototipominop.view.TelaPrincipal;

/**
 *
 * @author Leandro
 */
public class GraficoController {

    //Metodo para montar uma instancia da classe GraficoModel
    public GraficoModel montaGraficoModel(String arquivoSaida, String arquivoOriginal, String tipoClassificacao, String assunto){

        //Data atual
        Date date = new Date();

        BaseDeTextoController baseDeTextoController = new BaseDeTextoController();

        //Vetor para guardar a quantidade de mensagens classificadas como pertencentes a cada classe
        int resultados[] = baseDeTextoController.contaResultado(arquivoSaida);
        
        GraficoModel graficoModel = new GraficoModel();

        graficoModel.setArquivo(arquivoOriginal);

        //Caso o assunto não seja passado por parametro, admiti-se o nome do arquivo como assunto
        if(assunto == null){
            graficoModel.setAssunto(baseDeTextoController.retornaNomeArquivo(arquivoSaida).split("-")[0]);
        } else {
            graficoModel.setAssunto(assunto);
        }

        graficoModel.setData(date);
        graficoModel.setQtdClasse0(resultados[0]);
        graficoModel.setQtdClasse1(resultados[1]);

        //Caso o tipo de classificacao nao seja passado por parametro, admiti-se que seja um modelo existente
        if(tipoClassificacao == null){
            graficoModel.setTipoClassificacao("Modelo Existente");
        } else {
            graficoModel.setTipoClassificacao(tipoClassificacao);
        }
        
        return graficoModel;
        
    }

    //Metodo para gerar o grafico a partir de um GraficoModel
    public JFreeChart geraGrafico(GraficoModel graficoModel){

        //Define os dados do grafico
        DefaultPieDataset dados = new DefaultPieDataset();
        int class0 = graficoModel.getQtdClasse0();
        int class1 = graficoModel.getQtdClasse1();

        System.out.println(class0);
        System.out.println(class1);

        //Se nao for o processo inteiro
        if(!TelaPrincipal.ehProcessoInteiro){

            //Caso seja uma classificacao a partir de um modelo existente, coloca-se o nome de cada classe como Positiva e Negativa
            if(graficoModel.getTipoClassificacao().equals("Modelo Existente")){

                dados.setValue("Classe Positiva", graficoModel.getQtdClasse1());
                dados.setValue("Classe Negativa", graficoModel.getQtdClasse0());

            //Caso contrário, recupera-se os respectivos nomes
            } else {

                dados.setValue(graficoModel.getTipoClassificacao().split("/")[0], graficoModel.getQtdClasse1());
                dados.setValue(graficoModel.getTipoClassificacao().split("/")[1], graficoModel.getQtdClasse0());

            }

            

        //Caso contrario, recupera-se os respectivos nomes
        } else {

            String[] classes = TelaDescreveClassificacao.classificacaoEscolhida.split("/");

            dados.setValue(classes[0], graficoModel.getQtdClasse1());
        
            dados.setValue(classes[1], graficoModel.getQtdClasse0());
            
        }

        StringBuilder titulo = new StringBuilder("Classificação - ");
        titulo.append(graficoModel.getTipoClassificacao());
        titulo.append(" - ");
        titulo.append(graficoModel.getAssunto());

        //Cria um grafico JFree
        JFreeChart grafico = ChartFactory.createPieChart3D(
                                titulo.toString(), // titulo
                                dados, // conteudo dos dados
                                true, // legenda
                                true, // tooltips
                                false // URLs
                             );

        //Define direcionamento e grau de opacidade do grafico
        PiePlot3D plot = (PiePlot3D) grafico.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        //Mostra porcentagem nas labels
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));

        return grafico;

    }


    //Metodo para inserir um graficoModel no banco chamando o metodo
    //inserir de GraficoDao
    public void inserirBanco (GraficoModel graficoModel){

        GraficoDao graficoDao = new GraficoDao();

        graficoDao.inserir(graficoModel);

    }

    //Metodo para retornar um ArrayList de graficoModel do banco chamando o metodo
    //getGraficos de GraficoDao
    public ArrayList<GraficoModel> getGraficos() throws SQLException{

        GraficoDao graficoDao = new GraficoDao();

        return graficoDao.getGraficos();
        
    }

    //Metodo para retornar o caminho do arquivo de um grafico do banco chamando o metodo
    //getGraficos de GraficoDao
    public String getCaminhoArquivo(String arquivo) throws SQLException{

        GraficoDao graficoDao = new GraficoDao();

        return graficoDao.getCaminhoArquivo(arquivo);

    }

    

    






}
