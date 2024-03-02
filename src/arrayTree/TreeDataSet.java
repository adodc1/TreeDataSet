package arrayTree;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeDataSet implements Iterable<TreeDataNode> {

	private final TreeDataNode data;
	private TreeDataSet parent;
	private final ArrayList<TreeDataSet> children;

	public TreeDataSet(Object data) {
		this.data = new TreeDataNode(data, this);
		this.parent = null;
		this.children = new ArrayList<TreeDataSet>();
	}

	/**
	 * Inserta un nuevo nodo en el arbol. El nodo de tipo TreeDataNode contendra el
	 * objeto. No pueden haber dos objetos iguales que sean hijos del mismo nodo.
	 * 
	 * @param data : Object: Objeto para insertar en el arbol.
	 * @return TreeDataSet : Nodo sobre el que se pueden insertar otros en forma de
	 *         arbol.
	 */
	public TreeDataSet addChild(Object data) {
		TreeDataSet node = new TreeDataSet(data);

		for (TreeDataSet n : this.children) {
			if (n.getNode().equals(node.getNode())) {
				return n;
			}
		}
		node.parent = this;
		this.children.add(node);
		return node;
	}

	/**
	 * Elimina los nodos que cuelgan de este nodo.<br>
	 * El procedimiento es recursivo empezando por los nodos hoja hasta llegar al
	 * raiz.
	 */
	public void removeAll() {
		removeAllRecursive();
		this.children.clear();
	}

	private void removeAllRecursive() {
		for (Iterator<TreeDataSet> it = this.children.iterator(); it.hasNext();) {
			TreeDataSet node = it.next();
			while (node.hasChildren()) {
				node.removeAllRecursive();
				node.getChildren().remove(0);
			}
		}
	}

	/**
	 * @return Retorna cierto si el nodo tiene hijos.<br>
	 *         Retorna falso en caso contrario.
	 */
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	/**
	 * @return Retorna cierto si es un nodo hoja.<br>
	 *         Retorna falso en caso contrario.
	 */
	public boolean isLeaf() {
		return this.children.isEmpty();
	}

	/**
	 * @return Retorna el contenedor de objetos TreeDataNode que se creo dentro de
	 *         la estrucutra de arbol
	 */
	public TreeDataNode getNode() {
		return this.data;
	}

	/**
	 * @return Retorna un ArrayList que contiene todos los hijos que cuelgan de este
	 *         noso.<br>
	 *         En caso de que este nodo no tenga hijos ArrayList estara vacio.
	 */
	public ArrayList<TreeDataSet> getChildren() {
		return this.children;
	}

	/**
	 * @return Retorna cierto si este nodo es raiz.<br>
	 *         Retorna falso en caso contrario.
	 */
	public boolean isRoot() {
		return this.parent == null;
	}

	/**
	 * @return Retorna cierto si este nodo no tiene hijos.<br>
	 *         Retorna falso en caso contrario.
	 */
	public boolean isEmpty() {
		return this.children.isEmpty();
	}

	/**
	 * @return Retorna el numero identifiador del nivel en el que se encuentra el
	 *         nodo. Esto lo hace contando desde su posicion hasta el nodo raiz.
	 */
	public int getLevel() {
		if (this.isRoot()) {
			return 0;
		} else {
			return this.parent.getLevel() + 1;
		}
	}

	public Iterator<TreeDataNode> iterator() {
		return new TreeDataIterator(this);
	}

	/**
	 * @return Retorna una cadena de texto con los titulos de cada objeto
	 *         enctontrado desde el nodo indicado hasta el nodo raiz. <br>
	 *         <br>
	 *         ej.: "NODO1|NODO2|NODO3"
	 */
	public String getPath() {
		if (this.isRoot()) {
			return "";
		} else {
			return this.parent != null ? this.parent.getPath() + "|" + this.data.toString() : "null";
		}
	}

	/**
	 * Localiza un nodo utilizando una cadena en la que se declaran la secuencia de
	 * nodos enlazados.<br>
	 * <br>
	 * ej.: "NODO1.NODO2.NODO3"
	 * 
	 * @param path : String: Secuencia de nodos enlazados.
	 * @return Retorna el objeto nodo que contiene el objeto que se insertó
	 *         previamente.<br>
	 *         Si la secuencia no es correcta retornara <b>"null"</b>.
	 */
	public TreeDataNode findNode(String path) {
		TreeDataNode node = null;
		if (path.length() > 0) {
			for (TreeDataSet n : this.children) {
				if (path.startsWith(n.getPath())) {
					node = n.findNode(path);
					if (node == null) {
						node = n.getNode();
					}
				}
			}
		}
		return node;
	}

	@Override
	public String toString() {
		return this.data != null ? this.data.toString() : "null";
	}

}
