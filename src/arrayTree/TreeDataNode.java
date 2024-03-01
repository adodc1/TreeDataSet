package arrayTree;

public class TreeDataNode {

	private final Object object;
	private final TreeDataSet parent;

	public TreeDataNode(Object object, TreeDataSet parent) {
		this.object = object;
		this.parent = parent;
	}

	public int getLevel() {
		return this.parent.getLevel();
	}

	public String getPath() {
		return this.parent.getPath();
	}

	public Object getObject() {
		return object;
	}

	@Override
	public int hashCode() {
		return object.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return object.equals(obj);
	}

	@Override
	public String toString() {
		return object.toString();
	}

}
