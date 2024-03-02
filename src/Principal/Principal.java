package Principal;

import java.util.Iterator;

import arrayTree.TreeDataNode;
import arrayTree.TreeDataSet;

public class Principal {

	public Principal() {
		int nivel = 0;
		TreeDataSet tree = new TreeDataSet("ROOT");
		
		recursivo(tree, nivel);
		
		Iterator<TreeDataNode> it = tree.iterator();
		while (it.hasNext()) {
			TreeDataNode x = it.next();
			System.out.print(x.getObject().toString() + "\t" + x.getLevel() + "\t" + x.getPath());
			if (tree.findNode(x.getPath()) != null) {
				System.out.println("\t\t"+ tree.findNode(x.getPath()).toString());
			} else {
				System.out.println();
			}
		}

		tree.removeAll();

	}

	public static void recursivo(TreeDataSet tree, int nivel) {
		if (nivel < 3) {
			nivel++;

			TreeDataSet tree1 = tree.addChild("A=" + nivel + ":" + Math.round(Math.random() * 99));
			recursivo(tree1, nivel);

			TreeDataSet tree2 = tree.addChild("B=" + nivel + ":" + Math.round(Math.random() * 99));
			recursivo(tree2, nivel);
		}

	}

	public static void main(String[] args) {
		new Principal();
	}

}