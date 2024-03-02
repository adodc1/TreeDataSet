package arrayTree;

public class TreeDataNode {

	private final Object object;
	private final TreeDataSet parent;

	/**
	 * @param object
	 * @param parent
	 */
	public TreeDataNode(Object object, TreeDataSet parent) {
		this.object = object;
		this.parent = parent;
	}

	/**
	 * @return Retrona el nivel en el que esta situado el nodo dentro del arbol.
	 */
	public int getLevel() {
		return this.parent.getLevel();
	}

	/**
	 * @return Retorna la secuencia de objetos enlazados desde la posicion actual
	 *         hasta la raiz.
	 */
	public String getPath() {
		return this.parent.getPath();
	}

	/**
	 * @return Retorna el objeto que se inserto previamente en el arbol.
	 */
	public Object getObject() {
		return object;
	}

	/**
	 *
	 */
	@Override
	public int hashCode() {
		return object.hashCode();
	}

	/**
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		return object.equals(obj);
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return object.toString();
	}

}
