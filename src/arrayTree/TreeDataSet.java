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

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean isLeaf() {
		return this.children.isEmpty();
	}

	public TreeDataNode getNode() {
		return this.data;
	}

	public ArrayList<TreeDataSet> getChildren() {
		return this.children;
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isEmpty() {
		return this.children.isEmpty();
	}

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

	public String getPath() {
		if (this.isRoot()) {
			return "";
		} else {
			return this.parent != null ? this.parent.getPath() + this.data.toString() : "null";
		}
	}

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
