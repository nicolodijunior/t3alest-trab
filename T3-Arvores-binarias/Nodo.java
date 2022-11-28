public class Nodo {

    public Nodo anterior;
    public Nodo proximo;
    public Produto elemento;

    public Nodo(Produto p){
        this.anterior = null;
        this.proximo = null;
        this.elemento = p;
    }
}
