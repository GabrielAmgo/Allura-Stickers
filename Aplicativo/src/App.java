import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    private static List<Conteudo> extraiConteudos;

    public static void main(String[] args) throws Exception {

        
        //String url = "https://api.mocki.io/v2/549a5d8b";
        //ExtratorDeConteudoDoImdb extrator = new ExtratorDeConteudoDoImdb();
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-19&end_date=2022-07-22";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();
        
        for  (int i = 0; i < 3; i++)
        {

            Conteudo conteudo = conteudos.get(i);

            

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "aplicativo/saida/" + conteudo.getTitulo() + ".png";

            
            geradora.cria(inputStream, nomeArquivo);
        
            System.out.println("\u001b[1m Título: \u001b[0m" + conteudo.getTitulo());
            

            
        }
    }
}