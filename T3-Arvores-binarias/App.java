import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;



public class App{

       public static void inicializa(ArvoreBinariaProdutos arv, ListaPersonalizada lst,String fName)throws IOException{        
        File file = new File(fName); 
        Scanner s = new Scanner(file); 
        String line = s.nextLine(); 
        while(s.hasNextLine()){ 
            line = s.nextLine(); 
            StringTokenizer st = new StringTokenizer(line,","); 
            int codigo = Integer.parseInt(st.nextToken()); 
            String nome = st.nextToken(); 
            String tamanho = st.nextToken(); 
            double valor = Double.parseDouble(st.nextToken());
            Produto p = new Produto(codigo,nome,tamanho,valor); 
            lst.adicionar(p); 
            arv.adicionar(p);
        }
        s.close();
    }  
    public static void main(String[] args){  


        // Criando as Listas
        ListaPersonalizada ListaDeProdutos = new ListaPersonalizada();
        ArvoreBinariaProdutos ArvoreProdutos = new ArvoreBinariaProdutos();

        //Criando o scanner para ler inputs
        Scanner leia = new Scanner(System.in);
        try{
            inicializa(ArvoreProdutos, ListaDeProdutos, "Produtos_50.csv");
            int op = 1;

            do{
                System.out.println("----Trabalho 3 de ALEST I----");
                
                System.out.println("1 - Realizar uma busca");            
                System.out.println("0 - Finalizar o programa");
                op = leia.nextInt();

                switch(op){
                    case 1:
                        System.out.println("\nDigite o ID do produto");
                        int id = leia.nextInt();

                        if(ListaDeProdutos.consultarPorCodigo(id) != null && ArvoreProdutos.findCod(ArvoreProdutos.getRoot(), id, 0) != null){
                            System.out.println("\nProduto");
                            System.out.println("*Consulta por Codigo Lista");
                            System.out.println(ListaDeProdutos.consultarPorCodigo(id));
                            System.out.println("");
                            System.out.println("*Consulta Por Codigo Arvore");
                            System.out.println(ArvoreProdutos.findCod(ArvoreProdutos.getRoot(), id, 0)+"\n");
                        }else{  
                            System.out.println("Esse produto não existe");
                        }
                    break;

                    case 0:
                        System.out.println("Fim do programa...");
                    break;

                    default:
                        System.out.println("ERRO! opção não encontrada");
                    break;
                }
    
            }while(op != 0);


        }catch(IOException e){
            System.out.println("Problema ao ler o arquivo.");
        }
        /* System.out.println("Consulta por Codigo Lista");
        System.out.println(ListaDeProdutos.consultarPorCodigo(49));
        System.out.println("Consulta Por Codigo Arvore");
        System.out.println(ArvoreProdutos.findCod(ArvoreProdutos.getRoot(), 49, 0)); */

        //List<Produto> caminhoPreOrdem = arvoreProdutos.nossaArvore.caminharPreOrdem();
        //caminhoPreOrdem.toString();
    }
}