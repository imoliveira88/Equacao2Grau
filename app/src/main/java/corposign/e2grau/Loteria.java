package corposign.e2grau;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 23/11/2016.
 */
public class Loteria {
    private int numInicial, numFinal, quantidade;
    private List<Integer> sorteados;

    public Loteria(){
        sorteados = new ArrayList<>();
    }

    public int getNumInicial() {
        return numInicial;
    }

    public void setNumInicial(int numInicial) {
        this.numInicial = numInicial;
    }

    public int getNumFinal() {
        return numFinal;
    }

    public void setNumFinal(int numFinal) {
        this.numFinal = numFinal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Integer> getSorteados() {
        return sorteados;
    }

    public void setSorteados(List<Integer> sorteados) {
        this.sorteados = sorteados;
    }

    public int maisProximo(int numero){
        int maisproximo = 100;
        for(int i = 0; i < sorteados.size(); i++){
            if(Math.abs(sorteados.get(i) - numero) < Math.abs(numero - maisproximo)){
                maisproximo = sorteados.get(i);
            }
        }
        return maisproximo;
    }

    public void sorteia(){
        int i = 0;
        int atual;
        do{
            atual = (int) Math.floor((numFinal - numInicial)*Math.random());
            if(!sorteados.contains(atual) && this.maisProximo(atual) > 5){
                i++;
                sorteados.add(atual);
            }
        }while(i < quantidade);
    }
}
