package arrayTree;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeDataIterator implements Iterator<TreeDataNode> {

	private ArrayList<TreeDataNode> lista;
	private Iterator<TreeDataNode> it;

	/**
	 * @param data
	 */
	public TreeDataIterator(TreeDataSet data) {
		this.lista = new ArrayList<TreeDataNode>();
		this.recursivo(data);
		it = this.lista.iterator();
	}

	private void recursivo(TreeDataSet node) {

		if (node != null) {
			this.lista.add(node.getNode());
			for (TreeDataSet n : node.getChildren()) {
				recursivo(n);
			}
		}
	}

	public boolean hasNext() {
		return this.it.hasNext();
	}

	public TreeDataNode next() {
		return it.next();
	}

}
