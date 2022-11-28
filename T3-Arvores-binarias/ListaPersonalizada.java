public class ListaPersonalizada{
    
    private Nodo inicio;
    private Nodo fim;
    private int quantidade;

    public ListaPersonalizada(){
        this.inicio = null;
        this.fim = null;
        this.quantidade = 0;
    }    
    

    public void adicionar(Produto p) {
        Nodo novo = new Nodo(p);

        if (this.inicio == null){
            this.inicio = novo;
            this.fim = novo;
            quantidade++;
        }
        else {
            Nodo antigoFim = this.fim;
            antigoFim.proximo = novo;
            novo.anterior = antigoFim;
            this.fim = novo;
            quantidade++;
        }
    }

    public Produto consultarPorCodigo(int codigo){
        Nodo aux = this.inicio;
        int count = 0;
        while(aux != null){
            count++;
            if(aux.elemento.getCodigo()==codigo) {
                System.out.println("Operacoes realizadas: "+count);
                return aux.elemento;
            }
            aux = aux.proximo;
        }
        return null;
    }

    public void remover() {
        if (this.inicio != null) {
            this.inicio = this.inicio.proximo;
            this.quantidade--;
        } else {
            this.inicio = null;
        }
    }

    public Produto inicio() {
        return this.inicio.elemento;
    }

    public void limpar() {
        this.inicio.proximo = this.fim;
        this.fim.anterior = this.inicio;
    }

    public boolean isEmpty() {
        return quantidade == 0;
    }

    public int size() {
        return quantidade;
    }

    public Produto getFirst(){
        return this.inicio.elemento;
    }
    public String toString(){
        String text = "";
        Nodo nodo = this.inicio;
        while(nodo != null){
            text += "["+nodo.elemento.toString()+"]";
            nodo = nodo.proximo;
        }
        return text;
    }
    
}
