import java.io.FileWriter;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) {
        String out = "";    /*saída para um arquivo de texto - resultado das comparações e tempo gasta em cada caso*/
        Item reg;           /*o registro a ser inserido ou procurado*/
        long tempoInicial, tempoFinal; /*tempos em nano segundos inicial e final da pesquisa por um registro*/

        for(int n = 10000; n <= 100000; n += 10000) {   /*árvores com n variando de 10000 a 100000 com passo 10000 - 10x4 árvores*/
            out += "n = " + n + "\n";
            ArvoreSBB SBB = new ArvoreSBB();   /*árvore SBB ordenada*/
            ArvoreB B2 = new ArvoreB(2);
            ArvoreB B4 = new ArvoreB(4);
            ArvoreB B6 = new ArvoreB(6);

            for(int i = 0; i < n; i++) {    /*insere n nós nas árvores*/
                reg = new Item(i);  /*0 - n-1*/
                SBB.insere(reg);
                B2.insere(reg);
                B4.insere(reg);
                B6.insere(reg);
            }

            /*procura por itens inexistentes na árvore (-1 e n+1)*/
            reg = new Item(-1);
            tempoInicial = System.nanoTime();
            SBB.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE SBB (-1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + SBB.getCount() + "\n";

            reg = new Item(n+1);
            tempoInicial = System.nanoTime();
            SBB.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE SBB (n+1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + SBB.getCount() + "\n";

            reg = new Item(-1);
            tempoInicial = System.nanoTime();
            B2.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B2 (-1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B2.getCount() + "\n";

            reg = new Item(n+1);
            tempoInicial = System.nanoTime();
            B2.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B2 (n+1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B2.getCount() + "\n";

            reg = new Item(-1);
            tempoInicial = System.nanoTime();
            B4.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B4 (-1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B4.getCount() + "\n";

            reg = new Item(n+1);
            tempoInicial = System.nanoTime();
            B4.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B4 (n+1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B4.getCount() + "\n";

            reg = new Item(-1);
            tempoInicial = System.nanoTime();
            B6.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B6 (-1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B6.getCount() + "\n";

            reg = new Item(n+1);
            tempoInicial = System.nanoTime();
            B6.pesquisa(reg);
            tempoFinal = System.nanoTime();
            out += "ERRO:REGISTRO NAO ENCONTRADO NA ARVORE B6 (n+1)\n";
            out += "Tempo gasto: " + (tempoFinal-tempoInicial) + " ns - Comparacoes: " + B6.getCount() + "\n";
        }

        /*escreve a saída em um arquivo "pratica03.txt"*/
        try {
            FileWriter fp = new FileWriter("ArvoreB_SBB_Saida.txt");
            fp.write(out);
            fp.close();
        } catch (IOException e) {
            System.out.println("ERRO:NAO FOI POSSIVEL CRIAR O ARQUIVO");
        }
    }
}
