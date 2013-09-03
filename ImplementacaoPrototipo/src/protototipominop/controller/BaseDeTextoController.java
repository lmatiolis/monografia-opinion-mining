/*Classe: BaseDeTextoController
  Pacote: protototipominop.controller
  Funcionalidade: Essa classe é responsável pelas operações
  relativas às bases de texto montadas.
  Autor: Leandro Matioli Santos
*/


package protototipominop.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONException;
import protototipominop.model.BaseDeTextoModel;
import protototipominop.classesAuxiliares.JSONObject;
import protototipominop.view.TelaConverteJson;
import protototipominop.view.TelaDecideValidadeTreino;
import protototipominop.view.TelaIdentificaLingua;
import protototipominop.view.TelaIdioma;
import protototipominop.view.TelaPrincipal;
import protototipominop.view.TelaQuantidadeTextos;
import protototipominop.view.TelaRemocoes;
import protototipominop.view.TelaRemoveMsgIndesejadas;
import protototipominop.view.TelaSaidaClassificacao;
import protototipominop.view.TelaSaidaTreinamento;
import protototipominop.view.TelaUtilizaModeloExistente;


/**
 *
 * @author Leandro
 */
public class BaseDeTextoController {

    //OBS: Mensagem e tweet correspondem a mesma coisa. Nessa implementacao, foi utilizado o Twitter como estudo de caso,
    //dai a utilizacao do termo tweet.

    //Atributos estaticos que definem o caminho dos arquivos de configuracao das StopWords a serem removidas e da pontuacao
    public static final String arquivoListaStopWords = "./stopwords.txt";
    public static final String arquivoListaPontuacao = "./pontuacao.txt";

    public BaseDeTextoModel baseDeTextoModel;

    //Metodo que retorna (monta) uma BaseDeTextoModel a partir de um unico arquivo passado por parametro.
    public BaseDeTextoModel inserirBaseDeTextoUnicoArquivo(String caminhoArquivo) throws IOException{

        BaseDeTextoModel baseDeTextoModel = new BaseDeTextoModel();

        //Contador resposavel por contar a quantidade de linhas (mensagens) do arquivo
        int contadorDeTweets = 0;

        try {
            
            Scanner leitor = new Scanner(new File(caminhoArquivo));

            //Conta quantas mensagens existem no arquivo
            while(leitor.hasNext()){
                leitor.nextLine();
                contadorDeTweets = contadorDeTweets + 1;
            }

            leitor.close();

            //Seta os atributos da baseDeTextoModel
            baseDeTextoModel.setNome(retornaNomeArquivo(caminhoArquivo));
            baseDeTextoModel.setCaminhoAbsoluto(caminhoArquivo);
            baseDeTextoModel.setQtdTextos(contadorDeTweets);

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return baseDeTextoModel;

    }

    //Metodo para retornar o nome de um arquivo recebendo todo o caminho dele como parametro.
    public String retornaNomeArquivo(String caminhoDoArquivo){

        String[] caminhoSplitado;

        //Pega o separador utilizado pelo Sistema Operacional em questao
        String separador = System.getProperty("file.separator");

        //Gera uma lista contendo as pastas e o nome do arquivo
        //OBS: o \\ eh caracter especial para reconhecer caracteres especificos como ., por exemplo
        caminhoSplitado = caminhoDoArquivo.split("\\" + separador);

        return caminhoSplitado[caminhoSplitado.length-1];


    }

    //Metodo para remover as palavras consideradas irrelevantes (StopWords) da mensagem
    //de uma linhaRemocoes de um arquivo especificado em uma baseDeTextoModel
    public String removeStopWords(String linha) throws IOException{

        ArrayList<String> listaStopWords = new ArrayList();
        String tweet;

        //Gera a lista de StopWords
        listaStopWords = montaListaStopWords(arquivoListaStopWords);

        tweet = linha;

        //Percorre a lista de stopwords, apagando cada hora, uma
        for(int i = 0; i < listaStopWords.size(); i++){

            //Substitui a StopWord em questão por espaço em branco
            tweet = tweet.replace(" " + listaStopWords.get(i) + " ", " ");
        
        }

        return tweet;

    }

    //Metodo para montar a lista de StopWords através de um arquivo passado. Esse arquivo
    //deve conter uma palavra por linhaRemocoes
    public ArrayList montaListaStopWords(String arquivo) throws IOException{

        ArrayList<String> listaStopWords = new ArrayList();

        try {

            Scanner leitor = new Scanner(new File(arquivo));

            //Preenche a listaStopWords com as palavras contidas no arquivo
            while (leitor.hasNext()){
                listaStopWords.add(leitor.nextLine());
            }
            
            leitor.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return listaStopWords;

    }

    //Metodo para remover toda a pontuacao desejada de uma linhaRemocoes do tweet. Pontuacao passada por arquivo
    //no qual cada linhaRemocoes contem uma pontuacao a ser removida.
    public String removePontuacao(String linha){

        ArrayList<String> listaPontuacao = new ArrayList();
        String tweet;

        try {

            //Gera a lista de pontuacao
            listaPontuacao = montaListaPontuacao(arquivoListaPontuacao);

        } catch (IOException ex) {
            
            System.out.println(ex);
        }

        tweet = linha;

        //Percorre a lista de pontuacao, apagando cada hora, uma
        for(int i = 0; i < listaPontuacao.size(); i++){

            //Substitui a pontuacao em questão por espaço em branco
            tweet = tweet.replace(listaPontuacao.get(i), " ");
       
        }

        return tweet;



    }

    //Metodo para montar a lista de pontuacao através de um arquivo passado. Esse arquivo
    //deve conter uma pontuacao por linhaRemocoes
    public ArrayList montaListaPontuacao(String arquivo) throws IOException{

        ArrayList<String> listaPontuacao = new ArrayList();

        try {

            Scanner leitor = new Scanner(new File(arquivo));

            //Preenche a listaPontuacao com as palavras contidas no arquivo
            while (leitor.hasNext()){
                listaPontuacao.add(leitor.nextLine());
            }

            leitor.close();

        } catch (FileNotFoundException ex) {

            System.out.println(ex);
        }

        return listaPontuacao;

    }

    //Metodo para remocao de links http que esteja contidos na string passada.
    public String removeLinksHttp(String linha) {
        
        String[] linhaSplitada;
        String linhaFinal = "";

        //Gera um vetor das palavras contidas na string
        linhaSplitada = linha.split(" ");

        //Percorre o vetor e copia as palavras quando estas nao sao links http
        for(int i = 0; i<linhaSplitada.length; i++){
            if(!linhaSplitada[i].startsWith("http://")){
                linhaFinal = linhaFinal + linhaSplitada[i] + " ";
            }
        }

        return linhaFinal;
        
    }

    //Metodo para remover as tags RT e @usuario de uma linhaRemocoes do Twitter
    public String removeTagsTwitter(String linha) {
        
        String token;
        Scanner leitor = new Scanner(linha);
        StringBuilder saida = new StringBuilder();

        //Enquanto existirem tokens a serem lidos
        while (leitor.hasNext()){

            token = leitor.next();

            //Se o token em questao nao comeca com RT nem com @, adiciona à saida
            if(!token.startsWith("RT") && !token.startsWith("@")){
                saida.append(token);
                saida.append(" ");
            }
        }

        return saida.toString();
        

    }

    //Metodo para transformar todas as letras maisculas do texto em minusculas
    public String minimizaLetrasTexto(String linha){

        return linha.toLowerCase();

    }

    
    //Metodo para realizar o calculo da frequencia do termo, ou seja, quantas vezes
    //o termo x esta presente no tweet y.
    //Obs: Deve-se passar o documento com a pontuacao removida, para evitar erros como
    //desejar verificar quantas vezes o termo saber e este estiver proximo a uma virgula no texto
    //(Ex: Eu quero saber, mas quero saber mesmo, se o saber sabe da sua importancia!)
    public int calculaTF(String termo, String tweet){

        int termFrequency = 0;

        Scanner leitor = new Scanner(tweet);

        //Percorre o tweet token a token verificando se o termo está contido nele
        //Se sim, incrementa o TF
        while(leitor.hasNext()){
            if(leitor.next().equalsIgnoreCase(termo)){
                termFrequency++;
            }
        }

        leitor.close();

        return termFrequency;

    }

    //Metodo para realizar o calculo da frequencia do documento, ou seja, em quantos
    //documentos o termo x aparece
    //Obs: Deve-se passar o documento com a pontuacao removida, para evitar erros como
    //desejar verificar o termo saber e este estiver proximo a uma virgula no texto
    //(Ex: Nao quero saber, eu quero e viver!)
    public int calculaDF (String termo, BaseDeTextoModel baseDeTextoModel){

        int indexTermo;
        int documentFrequency;

        indexTermo = baseDeTextoModel.getDicionario().indexOf(termo);

        //Se o dicionario contiver o termo, calcula-se o df dele, caso contrario, atribui-se 0 (teoricamente, é
        // um termo não relevante, se o modelo foi bem treinado)
        if(indexTermo != -1 ){
            documentFrequency = baseDeTextoModel.getListaInvertidaDocumentos().get(indexTermo).size();
        } else {
            documentFrequency = 0;
        }
        
        return documentFrequency;

    }
  
    //Metodo para criar um dicionario de uma determinada base de texto. Cria-se tambem
    //uma lista invertida mostrando em quais documentos determinado token esta.
    public void montaDicionarioListaInvertida(BaseDeTextoModel baseDeTextoModel){

        String token;
        //Indice do documento sendo tratado no momento
        int contadorDocumentos = 1;
        //Indice do token sendo tratado no momento
        int indexToken;
        
        try {

            //Abre o arquivo com tokensRemovidos para leitura.
            Scanner leitorLinha = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto() + "-TOKENSREMOVIDOS"));

            TelaRemocoes.barraProgDicionario.setVisible(true);
            TelaRemocoes.labelDicionario.setVisible(true);

            while(leitorLinha.hasNext()){

                //Utiliza-se um scanner para ler token a token a linhaRemocoes do arquivo
                Scanner leitorPalavras = new Scanner(leitorLinha.nextLine());

                while(leitorPalavras.hasNext()){

                    token = leitorPalavras.next();

                    //Se o token nao estiver presente no dicionario
                    if(!baseDeTextoModel.getDicionario().contains(token)){

                        //Adiciona-se o token no dicionario
                        baseDeTextoModel.getDicionario().add(token);

                        //Salva-se o indice desse token em indexToken
                        indexToken = baseDeTextoModel.getDicionario().indexOf(token);

                        //Cria um arraylist que será a listaInvertida do token em questao
                        ArrayList<String> lista = new ArrayList<String>();
                        baseDeTextoModel.getListaInvertidaDocumentos().add(lista);

                        //Insere o indice do documento na listaInvertida deste token
                        baseDeTextoModel.getListaInvertidaDocumentos().get(indexToken).add(Integer.toString(contadorDocumentos));

                    //Se a palavra ja existir no dicionario, adiciona-se apenas o indice do documento em sua listaInvertida
                    } else {

                        //Salva-se o indice desse token em indexToken
                        indexToken = baseDeTextoModel.getDicionario().indexOf(token);

                        //Verifica se este indice de documento ja esta inserido na listaInvertida para evitar duplicacoes na lista
                        //Serve para o caso de uma mesma mensagem contiver duas ou mais vezes determinada palavra
                        if(!baseDeTextoModel.getListaInvertidaDocumentos().get(indexToken).contains(Integer.toString(contadorDocumentos))){
                            baseDeTextoModel.getListaInvertidaDocumentos().get(indexToken).add(Integer.toString(contadorDocumentos));
                        }

                    }

                }

                //Monta a saida que é escrita na barra de progresso na GUI
                StringBuilder saidaBarraDic = new StringBuilder(Integer.toString(contadorDocumentos));
                saidaBarraDic.append(" de ");
                saidaBarraDic.append(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
                saidaBarraDic.append(" docs");

                TelaRemocoes.barraProgDicionario.setValue(contadorDocumentos);
                TelaRemocoes.barraProgDicionario.setString(saidaBarraDic.toString());

                //Incrementa o indice do documento
                contadorDocumentos++;

                leitorPalavras.close();

            }

            leitorLinha.close();
            
            //Reinicia o contador de documentos para uma proxima utilizacao
            contadorDocumentos = 0;


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }    
   
    //Metodo para achar o indice de uma determinada palavra no dicionario especificado.
    //Retorna-se esse indice.
    public int retornaIndice(ArrayList<String> dicionario, String palavra){

        return dicionario.indexOf(palavra);

    }    

    //Metodo que realiza o calculo final dos pesos, resultando no Tf-Idf
    public double calculaTFIDF(String termo, String tweet, BaseDeTextoModel baseDeTextoModel){

        int tf;
        int df;
        double tfIdf = 0;

        tf = calculaTF(termo, tweet);
        df = calculaDF(termo, baseDeTextoModel);

        //Calculo do Tf-Idf: frequenciaDoTermo * log10(quantidadeDeDocumentos/frequenciaDoDocumento)
        if(TelaPrincipal.ehProcessoInteiro){
            
            tfIdf = tf * Math.log10(baseDeTextoModel.getQtdTextos()/df);
            
        //Trata o caso de utilizar modelo previamente treinado    
        } else {
            
            String caminhoArquivo;
            caminhoArquivo = TelaUtilizaModeloExistente.arquivoModelo.replace("-SAIDACLASSIFICACAO", "");
            caminhoArquivo = TelaUtilizaModeloExistente.arquivoModelo.replace("-MODELOSVM", "");
            
            GraficoController graficoController = new GraficoController();
            
            try {

                tfIdf = tf * Math.log10(contaLinhas(graficoController.getCaminhoArquivo(retornaNomeArquivo(caminhoArquivo))) / df);
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        return tfIdf;

    }
    
    //Método para realizar a primeira etapa do preprocessamento de um arquivo (baseDeTextoModel). Remove-se
    //apenas o que foi especificado passado por parametro
    public void realizaPreProcessamentoIntermediario(boolean minimizaTexto, boolean removeLinksHttp, boolean removeTagsTwitter,
                                                     boolean removeStopWords, boolean removePontuacao, BaseDeTextoModel baseDeTextoModel){
        
        String linha;
        int contador = 0;

        try {

            Scanner leitor;

            leitor = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto()));

            try {

                BufferedWriter escritor;

                //Abre o arquivo para ser escrito com o nome do lido concatenado com "-TOKENSREMOVIDOS"
                escritor = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-TOKENSREMOVIDOS"));

                //Lê o arquivo e remove os tokens de acordo com as opções passadas por parametro
                while(leitor.hasNext()){

                    linha = leitor.nextLine();

                    if(minimizaTexto){
                        linha = minimizaLetrasTexto(linha);
                    }

                    if(removeLinksHttp){
                        linha = removeLinksHttp(linha);
                    }

                    if(removeTagsTwitter){
                        linha = removeTagsTwitter(linha);
                    }

                    if(removeStopWords){
                        linha = removeStopWords(linha);
                    }

                    if(removePontuacao){
                        linha = removePontuacao(linha);
                    }

                    //Adiciona um espaco em branco no começo e final da linhaRemocoes para possibilitar o reconhecimento
                    //da primeira e última palavra como " palavra " (um espaço antes e um depois)
                    StringBuilder saida = new StringBuilder(" ");
                    saida.append(linha);
                    saida.append(" ");

                    //Escreve a linhaRemocoes resultante no arquivo de saida
                    escritor.write(saida.toString());
                    escritor.newLine();

                    contador++;

                    //Monta a saida que é escrita na barra de progresso na GUI
                    StringBuilder saidaBarra = new StringBuilder(Integer.toString(contador));
                    saidaBarra.append(" de ");
                    saidaBarra.append(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
                    saidaBarra.append(" docs");

                    TelaRemocoes.barraProgRemocoes.setValue(contador);
                    TelaRemocoes.barraProgRemocoes.setString(saidaBarra.toString());

                }

                leitor.close();
                escritor.close();

                //Se for o processo de mineração inteiro, deve-se gerar o dicionario e lista invertida
                if(TelaPrincipal.ehProcessoInteiro){

                    //Gera o dicionario e lista invertida das palavras contidas nessa base de texto
                    montaDicionarioListaInvertida(baseDeTextoModel);

                    //Escreve o dicionario e listaInvertida em arquivo para futura utilizacao
                    escreveDicionarioEmArquivo(baseDeTextoModel);
                    escreveListaInvertidaEmArquivo(baseDeTextoModel);

                //Caso se utilize um modelo existente e o arquivo que se deseja classificar nao esta
                //pre-processado, deve-se carregar o dicionario e a lista invertida do arquivo relacionado ao modelo
                // (o que foi usado para treina-lo)
                } else {

                    if(!TelaUtilizaModeloExistente.jaPreProcessado){

                        GraficoController graficoController = new GraficoController();
                        
                        try {

                            String caminhoArquivo;
                            caminhoArquivo = TelaUtilizaModeloExistente.arquivoModelo.replace("-SAIDACLASSIFICACAO", "");
                            caminhoArquivo = TelaUtilizaModeloExistente.arquivoModelo.replace("-MODELOSVM", "");
                            
                            carregaDicionarioDeArquivo(baseDeTextoModel, graficoController.getCaminhoArquivo(retornaNomeArquivo(caminhoArquivo)) + "-DICIONARIO");
                            carregaListaInvertidaDeArquivo(baseDeTextoModel, graficoController.getCaminhoArquivo(retornaNomeArquivo(caminhoArquivo)) + "-LISTAINVERTIDA");

                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }                        

                    }

                }


            } catch (IOException ex) {
                System.out.println("Excecao de IO - realizaPreProcessamento");
                System.out.println(ex);
            }

        } catch (FileNotFoundException ex) {

            System.out.println(ex);
            System.out.println("Arquivo Não Encontrado!");

        }
        
    }

    //Metodo para realizar o pré-processamento final da linhaRemocoes passada por parametro, com a classe
    //tambem definida
    public String realizaPreProcessamentoFinalLinhaALinha(String linha, int classeDefinida){

        String token;
        double tfIdf;

        //StringBuilder para construir as linhas que contituirao o arquivo que sera submetido ao SVM
        StringBuilder saidaParaSerEscrita = new StringBuilder(Integer.toString(classeDefinida));
        saidaParaSerEscrita.append(" ");

        Scanner leitorLinha = new Scanner(linha);

        ArrayList<String> tweetSemRepeticoes = new ArrayList<String>();
        ArrayList<Integer> indicesTweetSemRepeticoes = new ArrayList<Integer>();
        List indicesOrdenados = new ArrayList();

        int valorIndiceOrdenado;
        int posicaoIndiceOrdenado;

        //Adiciona os tokens em um arraylist, tomando o cuidado de nao permitir repeticoes
        while(leitorLinha.hasNext()){
            token = leitorLinha.next();

            if(!tweetSemRepeticoes.contains(token)){
                tweetSemRepeticoes.add(token);
            }
        }

        //Preenche o arrayList com os indices dos tweets e realiza uma copia para a lista que sera ordenada
        for(int i = 0; i < tweetSemRepeticoes.size(); i++){

            indicesTweetSemRepeticoes.add(retornaIndice(baseDeTextoModel.getDicionario(), tweetSemRepeticoes.get(i)));
            System.out.println(indicesTweetSemRepeticoes.get(i));
            indicesOrdenados.add(retornaIndice(baseDeTextoModel.getDicionario(), tweetSemRepeticoes.get(i)));

        }

        //Ordena a lista contendo os indices dos tokens
        Collections.sort(indicesOrdenados);
     
        leitorLinha.close();

        for(int i = 0; i < indicesOrdenados.size(); i++){


            //Converte o valor da lista dos indices ordenados para int
            valorIndiceOrdenado = Integer.valueOf(indicesOrdenados.get(i).toString());

            //Se o termo for um termo presente no dicionario, realiza-se seu processamento e escrita no arquivo pre-processado
            if(valorIndiceOrdenado != -1){

                //Recupera a  posicao do termo em questao no vetor dos indices correpondente ao tweetSemRepeticoes desordenado
                //e converte o mesmo para int
                posicaoIndiceOrdenado = indicesTweetSemRepeticoes.indexOf(valorIndiceOrdenado);

                //Realiza o calculo do Tf-Idf atraves do metodo calculaTFIDF(termo, tweet, arquivo)
                tfIdf = calculaTFIDF(tweetSemRepeticoes.get(posicaoIndiceOrdenado), linha, baseDeTextoModel);

                //Monta a saida como -> indice:tfIdf
                //Ex: 4:1.477121
                saidaParaSerEscrita.append(valorIndiceOrdenado+1);
                saidaParaSerEscrita.append(":");
                saidaParaSerEscrita.append(tfIdf);
                saidaParaSerEscrita.append(" ");

            }

        }

        System.out.println(saidaParaSerEscrita);

        return saidaParaSerEscrita.toString();

    }

    //Metodo que salva a indexacao feita (listaInvertida) em um arquivo para futura consulta
    public void escreveListaInvertidaEmArquivo(BaseDeTextoModel baseDeTextoModel){

        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-LISTAINVERTIDA"));

            //Percorre a listaInvertida em memoria. Ela eh um ArrayList de ArrayList (guarda em quais documentos a palavra esta)
            for(int i = 0; i<baseDeTextoModel.getListaInvertidaDocumentos().size(); i++){
                for(int j = 0; j< baseDeTextoModel.getListaInvertidaDocumentos().get(i).size(); j++){

                    //Escreve o indice do documento, separando por vírgula
                    escritor.write(baseDeTextoModel.getListaInvertidaDocumentos().get(i).get(j).toString());
                    escritor.write(",");

                }

                escritor.newLine();

            }

            escritor.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    //Metodo para escrever o dicionario de uma determinada base em arquivo para futura consulta
    public void escreveDicionarioEmArquivo(BaseDeTextoModel baseDeTextoModel){

        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-DICIONARIO"));

            //Percorre o dicionario em memoria, escrevendo uma palavra por linhaRemocoes no arquivo
            for(int i = 0; i < baseDeTextoModel.getDicionario().size(); i++){

                escritor.write(baseDeTextoModel.getDicionario().get(i));
                escritor.newLine();

            }

            escritor.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    //Metodo para escrever o arquivo dos textos convertidos dos objetos JSON
    public void escreveArquivoTextoConvertidoDoJson(String caminhoArquivo){

        TweetController tweetController = new TweetController();
        String linha;
        String msgTweet;
        int contaLinhas = 0;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));
            
            try {

                BufferedWriter escritor;

                escritor = new BufferedWriter(new FileWriter(caminhoArquivo + "Normal"));              

                while(leitor.hasNext()){

                    linha = leitor.nextLine();
                    contaLinhas++;

                    //Chama o metodo de TweetController que converte um objeto JSON para o texto contido nele
                    msgTweet = tweetController.converteJsonParaMensagemApenas(linha);

                    //Se nao for uma linhaRemocoes em branco, escreve ela no arquivo
                    if(!msgTweet.equals("LINHABRANCA")){
                        escritor.write(msgTweet);
                        escritor.newLine();
                    }
                    
                    //Monta a saida que é escrita na barra de progresso na GUI
                    StringBuilder saidaBarra = new StringBuilder(Integer.toString(contaLinhas));
                    saidaBarra.append(" de ");
                    saidaBarra.append(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
                    saidaBarra.append(" docs");

                    TelaConverteJson.barraProgConverteJson.setValue(contaLinhas);
                    TelaConverteJson.barraProgConverteJson.setString(saidaBarra.toString());
                    

                }

                leitor.close();

            } catch (IOException ex) {
                System.out.println("Erro de IO - escreveArquivoTextoConvertido");
                System.out.println(ex);
            }

            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            System.out.println("Arquivo nao encontrado - escreveArquivoTextoConvertido");
        }

    }

    //Metodo para realizar o treinamento do algoritmo de aprendizado de maquina SVM
    //Chama um executavel presente na pasta do projeto.
    public void realizaTreinamentoSVM(String arquivoContendoClassificacao, String arquivoSaidaModelo){

        String line;
        
        InputStream stderr = null;
        InputStream stdout = null;

        //Recupera o separador de diretorios do sistema
        String separador = System.getProperty("file.separator");

        //Monta-se a string contendo o trecho inicial do comando
        StringBuilder comandoInicial = new StringBuilder();
        comandoInicial.append("./svm");
        comandoInicial.append(separador);
        comandoInicial.append("svm_learn.exe");

        //Monta-se a string contendo o parametro final do comando
        StringBuilder parametroFinal = new StringBuilder();
        parametroFinal.append("./svm");
        parametroFinal.append(separador);
        parametroFinal.append("modelosSvm");
        parametroFinal.append(separador);
        parametroFinal.append(arquivoSaidaModelo);

        //Vetor contendo todos os parametros que serao passados no comando
        String[] comando = {comandoInicial.toString(),
                            arquivoContendoClassificacao,
                            parametroFinal.toString()};

        try {

            Process process = Runtime.getRuntime().exec(comando);

            stderr = process.getErrorStream ();
            stdout = process.getInputStream ();

            //Limpa qualquer saida em stdout
            BufferedReader brCleanUp = new BufferedReader (new InputStreamReader (stdout));

            //Exibe na tela a saida
            while ((line = brCleanUp.readLine ()) != null) {
                TelaSaidaTreinamento.textAreaSaidaTreino.append(line);
                TelaSaidaTreinamento.textAreaSaidaTreino.append("\n");
                System.out.println (line);
            }

            brCleanUp.close();

            //Limpa qualquer saida em stderr
            brCleanUp = new BufferedReader (new InputStreamReader (stderr));

            while ((line = brCleanUp.readLine ()) != null) {
                System.out.println ("[Stderr] " + line);
            }

            brCleanUp.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    //Metodo responsavel por executar o comando do SVM que realiza a classificacao binaria dos dados
    public void realizaClassificacao(String arquivoParaClassificar, String arquivoModelo, String saidaSvm ){

        InputStream stderr = null;
        InputStream stdout = null;
        String line;

        String separador = System.getProperty("file.separator");

        //Monta-se a string contendo o trecho inicial do comando
        StringBuilder comandoInicial = new StringBuilder();
        comandoInicial.append("./svm");
        comandoInicial.append(separador);
        comandoInicial.append("svm_classify");

        //Monta-se a string contendo o parametro final do comando
        StringBuilder parametroFinal = new StringBuilder();
        parametroFinal.append("./svm");
        parametroFinal.append(separador);
        parametroFinal.append("saidasSvm");
        parametroFinal.append(separador);
        parametroFinal.append(saidaSvm);

        //Vetor contendo todos os parametros que serao passados no comando
        String[] comando = {comandoInicial.toString(),
                            arquivoParaClassificar,
                            arquivoModelo,
                            parametroFinal.toString()};

        try {
            
            Process process = Runtime.getRuntime().exec(comando);

            stderr = process.getErrorStream ();
            stdout = process.getInputStream ();

            //Limpa qualquer saida em stdout
            BufferedReader brCleanUp = new BufferedReader (new InputStreamReader (stdout));

            //Exibe na tela a saida
            while ((line = brCleanUp.readLine ()) != null) {
                if(TelaPrincipal.telaAtual == 7){
                    
                    TelaDecideValidadeTreino.textAreaSaidaTeste.append(line);
                    TelaDecideValidadeTreino.textAreaSaidaTeste.append("\n");
                    
                } else if(TelaPrincipal.telaAtual == 8){
                    
                    TelaSaidaClassificacao.textAreaSaidaClassificacao.append(line);
                    TelaSaidaClassificacao.textAreaSaidaClassificacao.append("\n");
                    
                }

                System.out.println (line);
            }

            brCleanUp.close();

            //Limpa qualquer saida emstderr
            brCleanUp = new BufferedReader (new InputStreamReader (stderr));

            while ((line = brCleanUp.readLine ()) != null) {
                System.out.println ("[Stderr] " + line);
            }

            brCleanUp.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }


    //Metodo para contar a quantidade de documentos classificados como pertencentes
    //à cada classe
    public int[] contaResultado(String arquivoFinal){

        //Vetor contendo os dois resultados que sera retornado
        int vetResultados[] = new int[2];
        int contaClasseNegativa = 0;
        int contaClassePositiva = 0;
        //Saida do classificador
        double resultado;

        try {

            Scanner leitor = new Scanner(new File(arquivoFinal));

            while(leitor.hasNext()){

                //Converte a string para Double
                resultado = Double.parseDouble(leitor.nextLine());

                //Incrementa o contador de cada classe de acordo com o resultado
                if(resultado < 0){
                    contaClasseNegativa++;
                } else {
                    contaClassePositiva++;
                }

            }

            //Salva cada quantidade no vetor de saida
            vetResultados[0] = contaClasseNegativa;
            vetResultados[1] = contaClassePositiva;

            leitor.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }


        return vetResultados;

    }

    //Metodo para escrever os 3 arquivos que serao PreProcessados. O de treino, de teste e o que sera classificado.
    //Recebe como parametro a quantidade desejada de treino e teste.
    public String selecionaAmostraParaTreinoETeste(int quantidadeTreino, int quantidadeTeste, BaseDeTextoModel baseDeTextoModel){

        //ArrayList contendo dois ArrayLists de numeros aleatorios.
        //O primeiro [0] contem os de treinamento e o segundo [1] contem os de teste
        ArrayList<ArrayList> numerosAleatorios = new ArrayList<ArrayList>();
        int indiceDocumento = 0;

        String linhaRemocoes;
        String linhaNormal;

        int contador = 0;

        //Verifica se a quantidade requisitada nao excede a disponibilidade de textos
        if(quantidadeTreino+quantidadeTeste > baseDeTextoModel.getQtdTextos()){

            return "Quantidade excede o numero de documentos presente no arquivo original.";

        } else{

            try {

                //Abre o arquivo com os tokens removidos para utilizar como fonte de copia.
                Scanner leitor = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto() + "-TOKENSREMOVIDOS"));

                //Caso deseje-se gerar arquivos com as mensagens em ser formato original (sem remocoes), deve abrir o arquivo inicial
                Scanner leitorSemRemocoes = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto()));

                try {

                    //Abre os 3 escritores, um para cada arquivo
                    BufferedWriter escritorTreinamento = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-TREINAMENTO"));
                    BufferedWriter escritorTeste = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-TESTE"));
                    BufferedWriter escritorClassificacao = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAO"));

                    //Abre escritores para gerar arquivos sem os tokens removidos para exibi-los na tela.
                    BufferedWriter escritorTreinamentoSemRemov = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-TREINAMENTONORMAL"));
                    BufferedWriter escritorTesteSemRemov = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-TESTENORMAL"));
                    BufferedWriter escritorClassificacaoSemRemov = new BufferedWriter(new FileWriter(baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAONORMAL"));

                    //Os numeros aleatorios para selecionar os documentos para treino e teste
                    numerosAleatorios = geraListaNumerosAleatorios(quantidadeTreino, quantidadeTeste, baseDeTextoModel.getQtdTextos());

                    while(leitor.hasNext()){

                        linhaRemocoes = leitor.nextLine();

                        linhaNormal = leitorSemRemocoes.nextLine();

                        //Se o documento em questao foi selecionado para treino, escreve ele no respectivo arquivo
                        if(numerosAleatorios.get(0).contains(indiceDocumento)){

                            escritorTreinamento.write(linhaRemocoes);
                            escritorTreinamento.newLine();

                            escritorTreinamentoSemRemov.write(linhaNormal);
                            escritorTreinamentoSemRemov.newLine();


                        } else {

                            //Se o documento em questao foi selecionado para teste, escreve ele no respectivo arquivo
                            if(numerosAleatorios.get(1).contains(indiceDocumento)){

                                escritorTeste.write(linhaRemocoes);
                                escritorTeste.newLine();

                                escritorTesteSemRemov.write(linhaNormal);
                                escritorTesteSemRemov.newLine();

                            } else {

                                //Caso contrario, ele nao foi selecionado para nenhum dos dois e portanto é escrito
                                //no arquivo para classificacao
                                escritorClassificacao.write(linhaRemocoes);
                                escritorClassificacao.newLine();

                                escritorClassificacaoSemRemov.write(linhaNormal);
                                escritorClassificacaoSemRemov.newLine();

                            }

                        }

                        //Incrementa o indice do documento de acordo com a leitura de novas linhas
                        indiceDocumento++;

                        contador++;

                        //Monta a saida que é escrita na barra de progresso na GUI
                        StringBuilder saidaBarra = new StringBuilder(Integer.toString(contador));
                        saidaBarra.append(" de ");
                        saidaBarra.append(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
                        saidaBarra.append(" docs");

                        TelaQuantidadeTextos.barraProgArquivosTreinoTeste.setValue(contador);
                        TelaQuantidadeTextos.barraProgArquivosTreinoTeste.setString(saidaBarra.toString());

                    }

                    //Fecha o leitor e os escritores
                    leitor.close();
                    
                    escritorTreinamento.close();
                    escritorTeste.close();
                    escritorClassificacao.close();

                    escritorTreinamentoSemRemov.close();
                    escritorTesteSemRemov.close();
                    escritorClassificacaoSemRemov.close();

                } catch (IOException ex) {
                    System.out.println(ex);
                }

                return "Seleção realizada com sucesso!";

            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }

        }

        return "";

    }

    //Metodo para gerar a lista de numeros aleatorios que servirao como indices para selecionar os documentos
    //para teste e treino. Retorna um ArrayList de ArrayList, //O primeiro [0] contem os de treinamento e o
    //segundo [1] contem os de teste
    public ArrayList<ArrayList> geraListaNumerosAleatorios(int qtdTreino, int qtdTeste, int qtdTotalDocumentos){

        //Instancia os ArrayLists
        ArrayList<ArrayList> numerosAleatorios = new ArrayList<ArrayList>();
        ArrayList numAleatoriosTeste = new ArrayList();
        ArrayList numAleatoriosTreinamento = new ArrayList();

        //Gera o random
        Random random = new Random();
        int numeroRandomico;

        int contador = 0;

        //Loop responsavel pelo preenchimento do ArrayList de treinamento
        while(contador < qtdTreino){

            //Gera um numero aleatorio, variando de 0 ate a quantidadeTotalDeDocumentos
            numeroRandomico = random.nextInt(qtdTotalDocumentos);

            //Se esse numero gerado ja nao estiver no ArrayList, adiciona ele e incrementa o contador
            if(!numAleatoriosTreinamento.contains(numeroRandomico)){

                numAleatoriosTreinamento.add(numeroRandomico);
                contador++;

            }

        }

        //Zera o contador para novo processamento
        contador = 0;

        //Loop responsavel pelo preenchimento do ArrayList de teste
        while(contador < qtdTeste){

            //Gera um numero aleatorio, variando de 0 ate a quantidadeTotalDeDocumentos
            numeroRandomico = random.nextInt(qtdTotalDocumentos);

            ////Se esse numero gerado ja nao estiver nos dois ArrayLists, adiciona ele e incrementa o contador
            if(!numAleatoriosTeste.contains(numeroRandomico) && !numAleatoriosTreinamento.contains(numeroRandomico)){

                numAleatoriosTeste.add(numeroRandomico);
                contador++;

            }

        }

        //Coloca os dois ArrayLists no ArrayList final, que sera retornado pelo metodo
        numerosAleatorios.add(numAleatoriosTreinamento);
        numerosAleatorios.add(numAleatoriosTeste);

        return numerosAleatorios;


    }

    //Método para realizar coleta no Twitter atraves de palavras chave especificadas no arquivo passado por parametro
    public void realizaColeta(String caminhoTracking, String usuario, String senha, String arquivoSaida){

        //Constrói a linhaRemocoes de comando utilizada para realizar a coleta
        //Ex de linhaRemocoes construida:
        //"./curl\\curl -d @D:\\tracking http://stream.twitter.com/1/statuses/filter.json -u testeMinera:teste123 -o D:\\saida"
        StringBuilder cmd = new StringBuilder("./curl");
        cmd.append(System.getProperty("file.separator"));
        cmd.append("curl -d @");
        cmd.append(caminhoTracking);
        cmd.append(" http://stream.twitter.com/1/statuses/filter.json -u ");
        cmd.append(usuario);
        cmd.append(":");
        cmd.append(senha);
        cmd.append(" -o ");
        cmd.append("./coleta");
        cmd.append(System.getProperty("file.separator"));
        cmd.append(arquivoSaida);

        try {

            Process process = Runtime.getRuntime().exec(cmd.toString());

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    //Finaliza a coleta iniciada no método realizaColeta
    public void finalizaColeta(){

        try {

            //Especifico para windows, caso se deseja utilizar em outro SO, seria necessario
            //utilizar o "kill" do sistema em questao
            Runtime.getRuntime().exec("taskkill /F /IM curl.exe");

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    //Metodo para verificar se determinado arquivo contem objetos Json
    public boolean verificaSeEhArquivoJson(String caminhoArquivo) {

        boolean retorno = false;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));

            //Tenta instanciar um Objeto Json com a linhaRemocoes lida
            try{

                //Se conseguir, retorna que eh um objeto json
                JSONObject linhaJson = new JSONObject(leitor.nextLine());
                retorno = true;
                
            } catch (JSONException ex) {
                System.out.println(ex);
                retorno = false;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return retorno;

    }

    //Metodo para identificar os idiomas contidos em um arquivo
    public void identificaLingua(String arquivo){

        String linhaSemEspaco;
        
        //Conta linhas começa com 1 para nao parar de enviar requisicoes logo na primeira leitura
        int contaLinhas = 1;

        try {

            Scanner leitor = new Scanner(new File(arquivo));

            try {

                BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo + "-IDLINGUAS"));

                while(leitor.hasNext()){

                    if(!TelaIdentificaLingua.worker.isCancelled()){
                        URL url;

                        StringBuilder link = new StringBuilder("http://ajax.googleapis.com/ajax/services/language/detect?v=1.0&q=");

                        //Adiciona os "%20" onde tem espaço na String
                        linhaSemEspaco = leitor.nextLine().replaceAll(" ", "%20");

                        //Acaba de montar o link para fazer as requisicoes
                        link.append(linhaSemEspaco);

                        contaLinhas++;

                        try {

                            url = new URL(link.toString());

                            URLConnection connection;

                            try {

                                connection = url.openConnection();

                                String line;
                                StringBuilder builder = new StringBuilder();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                                //Vai montando a string enquanto houver o que ser lido
                                while((line = reader.readLine()) != null) {
                                    builder.append(line);
                                }

                                //Escreve o objeto JSON retornado pelo api no arquivo
                                escritor.write(builder.toString());
                                escritor.newLine();

                                System.out.println(builder.toString());

                                StringBuilder saidaBarra = new StringBuilder();
                                saidaBarra.append(contaLinhas - 1);
                                saidaBarra.append(" de ");
                                saidaBarra.append(TelaIdentificaLingua.barraProgIdLingua.getMaximum());
                                saidaBarra.append(" docs");

                                TelaIdentificaLingua.barraProgIdLingua.setValue(contaLinhas - 1);
                                TelaIdentificaLingua.barraProgIdLingua.setString(saidaBarra.toString());

                                //A cada 1500 processados, espera 20 minutos (para não sobrecarregar a api)
                                if(contaLinhas%1500 == 1){

                                    System.out.println("Intervalo!");

                                    long t0, t1;

                                    StringBuilder tempo = new StringBuilder();
                                    tempo.append(saidaBarra.toString());
                                    tempo.append(" - Intervalo: ");
                                    tempo.append("Aguarde 20");
                                    tempo.append(" min");

                                    TelaIdentificaLingua.barraProgIdLingua.setString(tempo.toString());

                                    t0 =  System.currentTimeMillis();

                                    do{

                                        t1 = System.currentTimeMillis();                                    

                                        //Interrompe a espera caso a thread seja cancelada
                                        if(TelaIdentificaLingua.worker.isCancelled()){
                                            break;
                                        }

                                    }while ((t1 - t0) < (1200 * 1000));

                                }

                            } catch (IOException ex) {
                                System.out.println(ex);

                                escritor.write(ex.toString());
                                escritor.newLine();
                                
                            }

                        } catch (MalformedURLException ex) {
                            System.out.println(ex);

                            escritor.write(ex.toString());
                            escritor.newLine();

                        }

                    } else {

                        leitor.close();
                        escritor.close();                      

                    }
                }

                leitor.close();
                escritor.close();
                
            } catch (IOException ex) {
                System.out.println(ex);
            }        

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    //Método para filtrar um arquivo obtendo apenas as mensagens na lingua especificada
    public void filtraLingua(String arquivoOriginal, String arquivoLingua, String lingua){

        String linha;
        int contador = 0;

        try {
            Scanner leitorOriginal = new Scanner(new File(arquivoOriginal));
            Scanner leitorLingua = new Scanner(new File(arquivoLingua));

            try {

                BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoOriginal + "-" + lingua.toUpperCase()));

                //Enquanto houver o que ler
                while(leitorOriginal.hasNext() || leitorLingua.hasNext()){

                    linha = leitorOriginal.nextLine();

                    //Escreve as mensagens na lingua escolhida
                    try{

                        JSONObject saidaApi = new JSONObject(leitorLingua.nextLine());
                        JSONObject idLingua = new JSONObject(saidaApi.get("responseData").toString());

                        if(lingua.equals("Inglês")){

                            if((idLingua.get("language").equals("en")) && (!idLingua.get("confidence").toString().equals("0"))){

                                escritor.write(linha);
                                escritor.newLine();

                            }

                        } else if(lingua.equals("Português")){

                            if((idLingua.get("language").equals("pt")) && (!idLingua.get("confidence").toString().equals("0"))){

                                escritor.write(linha);
                                escritor.newLine();

                            }

                        } else if(lingua.equals("Espanhol")){

                            if((idLingua.get("language").equals("es")) && (!idLingua.get("confidence").toString().equals("0"))){

                                escritor.write(linha);
                                escritor.newLine();

                            }

                        }

                    }catch(JSONException ex){

                        System.out.println(ex);
                        
                    }

                    contador++;

                    //Monta a saida que é escrita na barra de progresso na GUI
                    StringBuilder saidaBarra = new StringBuilder(Integer.toString(contador));
                    saidaBarra.append(" de ");
                    saidaBarra.append(TelaPrincipal.baseDeTextoController.baseDeTextoModel.getQtdTextos());
                    saidaBarra.append(" docs");

                    //Atualiza valor da barra e escreve a quantidade de docs que ainda faltam na tela
                    TelaIdioma.barraProgIdioma.setValue(contador);
                    TelaIdioma.barraProgIdioma.setString(saidaBarra.toString());

                }

                escritor.close();
                leitorLingua.close();
                leitorOriginal.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    //Método para selecionar uma quantidade desejada de amostras da base classificada final
    public String retornaAmostraClassificada(BaseDeTextoModel baseDeTextoModel, int qtdDesejada, int classe){

        int contadorAmostra = 0;
        String linhaSaidaSvm;
        String linhaMensagem;

        StringBuilder saida = new StringBuilder();

        Scanner leitorClassificacao;
        Scanner leitorMensagens;

        //Monta o caminho do arquivo com a saida da classificacao
        String arquivo = "./svm" + System.getProperty("file.separator") +
                         "saidasSvm" + System.getProperty("file.separator") +
                         retornaNomeArquivo(baseDeTextoModel.getCaminhoAbsoluto()) +
                         "-SAIDACLASSIFICACAO";

        try {

            leitorClassificacao = new Scanner(new File(arquivo));

            if(TelaPrincipal.ehProcessoInteiro){
                leitorMensagens = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto() + "-CLASSIFICACAONORMAL"));
            } else {
                leitorMensagens = new Scanner(new File(baseDeTextoModel.getCaminhoAbsoluto()));
            }
            

            //Enquanto tiver linhas pra ler e nao atingir a quantidade desejada pelo usuario
            while((leitorClassificacao.hasNext()) && (contadorAmostra < qtdDesejada)){

                linhaSaidaSvm = leitorClassificacao.nextLine();
                linhaMensagem = leitorMensagens.nextLine();

                System.out.println(linhaMensagem);
                System.out.println(contadorAmostra);

                //Adiciona as mensagens na saida de acordo com a classe desejada
                if(classe == 1){
                    if(Double.valueOf(linhaSaidaSvm) > 0){

                        contadorAmostra++;

                        saida.append(linhaMensagem);
                        saida.append("\n");

                    }
                } else {
                    if (Double.valueOf(linhaSaidaSvm) < 0){

                        contadorAmostra++;

                        saida.append(linhaMensagem);
                        saida.append("\n");

                    }
                }
            }

            leitorClassificacao.close();
            leitorMensagens.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return saida.toString();

    }

    //Método que escreve as palavras-chave especificadas pela interface
    public void geraArquivoTracking(String palavras) {

        try {
            
            BufferedWriter escritor = new BufferedWriter(new FileWriter("./tracking"));

            escritor.write("track=");
            escritor.write(palavras);
            escritor.close();

        } catch (IOException ex) {
            System.out.println();
        }


    }

    //Metodo para verificar ate onde já foi feita a identificacao dos idiomas de um arquivo
    // e gerar um novo arquivo começando onde parou
    public void separaArquivoIdLinguas(String arquivo){

        int contador = 0;

        try {

            Scanner leitorOriginal = new Scanner(new File(arquivo));
            Scanner leitorIdLingua = new Scanner(new File(arquivo + "-IDLINGUAS"));

            try {

                BufferedWriter escritorParou = new BufferedWriter(new FileWriter(arquivo + "-ONDEPAROU"));

                while (leitorIdLingua.hasNext()){

                    leitorIdLingua.nextLine();

                    escritorParou.write(leitorOriginal.nextLine());
                    escritorParou.newLine();

                }
                
                escritorParou.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            try {

                BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo + "-CONT"));

                while(leitorOriginal.hasNext()){

                    escritor.write(leitorOriginal.nextLine());
                    escritor.newLine();

                    contador++;

                    //Monta a saida que é escrita na barra de progresso na GUI
                    StringBuilder saidaBarra = new StringBuilder();
                    saidaBarra.append(contador);
                    saidaBarra.append(" de ");
                    saidaBarra.append(TelaIdentificaLingua.barraArquivoAtehMomento.getMaximum());
                    saidaBarra.append(" docs");

                    //Atualiza valor da barra e escreve a quantidade de docs que ainda faltam na tela
                    TelaIdentificaLingua.barraArquivoAtehMomento.setValue(contador);
                    TelaIdentificaLingua.barraArquivoAtehMomento.setString(saidaBarra.toString());

                }

                escritor.close();

            } catch (IOException ex) {
                System.out.println();
            }

            leitorOriginal.close();
            leitorIdLingua.close();

            //Renomeia o arquivo IDLINGUAS para corresponder ao arquivo de onde parou
            File arqOriginal = new File(arquivo + "-IDLINGUAS");
            File arqRenomeado = new File(arquivo + "-ONDEPAROU" + "-IDLINGUAS");
            arqOriginal.renameTo(arqRenomeado);
            

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    //Metodo para verificar se determinado arquivo existe. Retorna true se sim e false caso contrario.
    public boolean verificaExistenciaArquivo(String arquivo) {

        try {

            Scanner leitor = new Scanner(new File(arquivo));

            return true;

        } catch (FileNotFoundException ex) {

            System.out.println(ex);

            return false;
        }

    }

    //Método para contar a quantidade de linhas de um arquivo
    public int contaLinhas(String caminhoArquivo) {

        int contador = 0;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));

            while (leitor.hasNext()){

                leitor.nextLine();
                contador++;

            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return contador;
        
    }

    //Carrega o dicionario de uma baseDeTexto atraves de seu arquivo
    public void carregaDicionarioDeArquivo(BaseDeTextoModel baseDeTextoModel, String caminhoArquivo){

        String linha;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));

            while(leitor.hasNext()){

                linha = leitor.nextLine();

                baseDeTextoModel.getDicionario().add(linha);

            }

            leitor.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    //Carrega a Lista Invertida de uma baseDeTexto atraves de seu arquivo
    public void carregaListaInvertidaDeArquivo(BaseDeTextoModel baseDeTextoModel, String caminhoArquivo){

        String linha;
        String[] indicesDocs;
        int contaLinha = 0;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));

            while(leitor.hasNext()){

                linha = leitor.nextLine();

                indicesDocs = linha.split(",");

                //Cria um arraylist que será a listaInvertida do token em questao
                ArrayList<String> lista = new ArrayList<String>();
                baseDeTextoModel.getListaInvertidaDocumentos().add(lista);

                for(int i = 0; i < indicesDocs.length; i++){
                    baseDeTextoModel.getListaInvertidaDocumentos().get(contaLinha).add(indicesDocs[i]);
                }

                contaLinha++;

            }

            leitor.close();


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }




    }

    //Metodo para remover mensagens que sao consideradas nao relacionadas ao assunto coletado.
    //Remove-se atraves da lista de strings passada por parametro
    public void removeMensagensDesejadas(String caminhoArquivo, String[] trechosRemove){

        boolean remove = false;
        String linha;
        int contaLinhas = 0;
        int totalLinhas = contaLinhas(caminhoArquivo);
        int i = 0;

        try {

            Scanner leitor = new Scanner(new File(caminhoArquivo));

            try {

                BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo + "-FILTRADO"));

                while(leitor.hasNext() && remove == false){

                    i = 0;

                    linha = leitor.nextLine();
                    
                    contaLinhas++;

                    //Monta a saida que é escrita na barra de progresso na GUI
                    StringBuilder saidaBarra = new StringBuilder();
                    saidaBarra.append(contaLinhas);
                    saidaBarra.append(" de ");
                    saidaBarra.append(totalLinhas);
                    saidaBarra.append(" docs");

                    //Atualiza valor da barra e escreve a quantidade de docs que ainda faltam na tela
                    TelaRemoveMsgIndesejadas.barraProgRemocao.setValue(contaLinhas);
                    TelaRemoveMsgIndesejadas.barraProgRemocao.setString(saidaBarra.toString());

                    while(i<trechosRemove.length && remove == false){

                        //Se a linha contiver algum trecho que deseja-se ignorar, marca-se a mensagem para ser removida
                        if(linha.toLowerCase().contains(trechosRemove[i].toLowerCase())){
                            remove = true;
                        }

                        i++;

                    }

                    //Só escreve a mensagem caso ela não contenha trechos que devem ser removidos
                    if(remove == false){

                        escritor.write(linha);
                        escritor.newLine();

                    }

                    remove = false;

                }

                escritor.close();
                leitor.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }




    }

    //Metodo para concatenar 2 arquivos
    public void concatenaArquivos(String arquivoOriginal, String arquivoConcatenar){

        try {

            Scanner leitor = new Scanner(new File(arquivoConcatenar));

            try {

                BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoOriginal, true));

                while(leitor.hasNext()){

                    escritor.write(leitor.nextLine());
                    escritor.newLine();

                }

                leitor.close();
                escritor.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    //Metodo para separar em 2 arquivos o resultado final de classificação (2 classes identificadas)
    public void separaClassificacaoFinal(String arquivoClassificacao, String arquivoSaidaSvm, String classePos, String classeNeg){

        String linhaLida;
        double saidaSvm;

        try {

            Scanner leitorClassificacao = new Scanner(new File(arquivoClassificacao));
            Scanner leitorSaidaSvm = new Scanner(new File(arquivoSaidaSvm));

            try {

                BufferedWriter escritorClassePos = new BufferedWriter(new FileWriter(arquivoClassificacao + "-" + classePos));
                BufferedWriter escritorClasseNeg = new BufferedWriter(new FileWriter(arquivoClassificacao + "-" + classeNeg));

                while(leitorClassificacao.hasNext()){

                    linhaLida = leitorClassificacao.nextLine();
                    saidaSvm = Double.valueOf(leitorSaidaSvm.nextLine());

                    //Trata da classe negativa
                    if(saidaSvm<0){

                        escritorClasseNeg.write(linhaLida);
                        escritorClasseNeg.newLine();

                    //Trata da classe positiva
                    } else {

                        escritorClassePos.write(linhaLida);
                        escritorClassePos.newLine();
                    }

                }

                leitorClassificacao.close();
                leitorSaidaSvm.close();

                escritorClasseNeg.close();
                escritorClassePos.close();

            } catch (IOException ex) {
                System.out.println(ex);
            }


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

}
