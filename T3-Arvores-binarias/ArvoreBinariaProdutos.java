import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class ArvoreBinariaProdutos
{

    private static final class Node<Produto> {

        public Node<Produto> parent;
        public Node<Produto> left;
        public Node<Produto> right;
        public Produto element;

        public Node(Produto element) {
            parent = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos
    private int count; //contagem do número de nodos
    private Node<Produto> root; //referência para o nodo raiz

    public ArvoreBinariaProdutos() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return count;
    }

    public Node<Produto> getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return this.root;
    }

    public List<Produto> caminharPreOrdem() {
        List<Produto> list = new ArrayList<>();
        preOrdemRecursivo(list, root);
        return list;
    }

    public void preOrdemRecursivo(List<Produto> caminho, Node<Produto> nodo) {
        caminho.add(nodo.element);
        if(nodo.left!=null) preOrdemRecursivo(caminho, nodo.left);
        if(nodo.right!=null) preOrdemRecursivo(caminho, nodo.right);
    }

    public void adicionar(Produto element) {
        Node<Produto> aux = new Node<Produto>(element);
        if (root == null) { // Se for o primeiro elemento, cria como raiz e retorna
            root = aux;
            return;
        }

        Node<Produto> pai = root;
        boolean adicionou = false;

        while(!adicionou) {
            if(aux.element.getCodigo()<=pai.element.getCodigo()) {
                if(pai.left==null) {
                    pai.left = aux;
                    aux.parent = pai;
                    adicionou = true;
                }
                else pai = pai.left;
            }
            else {
                if(pai.right==null) {
                    pai.right = aux;
                    aux.parent = pai;
                    adicionou = true;
                }
                else pai = pai.right;
            }
        }


    }
	/*
    public boolean contains(E element)
    {
        Node<Produto> atual = root;
        // Completar
        return false;
    }
	 */

    public List<Produto> positionsCentral() {
        List<Produto> res = new LinkedList<>();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node<Produto> n, List<Produto> res) {
        if (n != null) {
            positionsCentralAux(n.left, res); //Visita a subárvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subárvore da direita
        }
    }

    private void geraConexoesDOT(StringBuilder sb, Node<Produto> nodo) {
        if (nodo == null) {
            return;
        }

        geraConexoesDOT(sb, nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            sb.append("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" \n");
        }

        geraConexoesDOT(sb, nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            sb.append("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" \n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void geraNodosDOT(StringBuilder sb, Node<Produto> nodo) {
        if (nodo == null) {
            return;
        }
        geraNodosDOT(sb,nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        sb.append("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]\n");
        geraNodosDOT(sb,nodo.right);
    }

    public void geraConexoesDOT(StringBuilder sb) {
        geraConexoesDOT(sb, root);
    }

    public void geraNodosDOT(StringBuilder sb) {
        geraNodosDOT(sb,root);
    }

	

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline
    public String toDOT() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g { \nnode [shape = record,height=.1];\n");
        geraNodosDOT(sb);
        geraConexoesDOT(sb);
        sb.append("}\n");
        return sb.toString();
    }

	public String findCod(Node<Produto> aux, int cod, int count){
		count++;
		if(aux==null) return "Nao encontrado, operacoes = "+count;
		if(aux.element.getCodigo()==cod){
			return "Operacoes Realizadas: "+count+"\n"+aux.element.toString();
		}
		if(cod<=aux.element.getCodigo()) return findCod(aux.left, cod, count);
		else return findCod(aux.right, cod, count);
	}
	 
}