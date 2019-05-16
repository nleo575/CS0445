// CS 0445 Spring 2018 Assignment 4 Test Program for extra credit
// This program should run as is with your program files.
// See the file A4Out.txt for the output -- your output should match
// that shown in A4Out.txt exactly.
import java.util.*;
import MyTreePackage.*; // Put all of your tree files into this package.
		// These should be in a subdirectory called MyTreePackage and the
		// package header should be at the top of each file.
public class A4EC
{
	public static void main(String [] args)
	{
		BinaryNode<Integer>  root = init2();
		ComparableBinaryTree<Integer> T2 = new ComparableBinaryTree<Integer>();
		T2.setRootNode(root);
		System.out.println("Testing T2");
		testTree(T2);
		T2.drawTree("T2");
		T2.saveInorder("BST.dat");
		T2.buildComplete("BST.dat");
		T2.drawTree("T2Complete");


		BinarySearchTree<Integer> T5 = new BinarySearchTree<Integer>();
		for (int i = 1; i <= 15; i++)
		{
			T5.add(new Integer(i));
		}
		T5.saveInorder("BST2.dat");
		T5.buildInorder("BST2.dat");
		System.out.println("Testing T5");
		testTree(T5);
		T5.drawTree("T5");


		BinarySearchTree<String> T6 = new BinarySearchTree<String>();
		initBST(T6);
		T6.saveInorder("BST3.dat");
		T6.buildInorder("BST3.dat");
		System.out.println("Testing T6");
		testTree(T6);
		T6.drawTree("T6");
		T6.buildComplete("BST3.dat");
		T6.drawTree("T6Complete");
	}

	// This method tests the methods you have written. Note that, due
	// to inheritance, this will work with a ComparableBinaryTree or
	// a BinarySearchTree (from your methods -- not the Author's).  However,
	// due to polymorphism some of the methods will have different
	// implementations based on the type of the tree.
	public static <T extends Comparable<? super T>> void testTree(ComparableBinaryTree<T> tree)
	{
		System.out.println("---------");
		System.out.println("Height: " + tree.getHeight());
		System.out.println("Nodes: " + tree.getNumberOfNodes());
		boolean test;

		System.out.println();

		test = tree.isFull();
		if (test)
			System.out.println("Tree is FULL");
		else
			System.out.println("Tree is NOT FULL");
		test = tree.isBST();
		if (test)
			System.out.println("Tree is a BST");
		else
			System.out.println("Tree is NOT A BST");

		Iterator<T> I = tree.getInorderIterator();
		System.out.println("Inorder the data is:");
		IterateOn(I);
		System.out.println();
	}

	public static <T> void IterateOn(Iterator<T> I)
	{
		while (I.hasNext())
		{
			T curr = I.next();
			System.out.print(curr + " ");
		}
		System.out.println();
	}

	// This tree will be a binary search tree (BST), but not full. 
	public static BinaryNode<Integer> init2()
	{
		BinaryNode<Integer> temp1 = new BinaryNode<Integer>(new Integer(17));
		BinaryNode<Integer> temp2 = new BinaryNode<Integer>(new Integer(20), temp1, null);
		temp1 = new BinaryNode<Integer>(new Integer(10));
		BinaryNode<Integer> temp3 = new BinaryNode<Integer>(new Integer(15), temp1, temp2);
		temp2 = new BinaryNode<Integer>(new Integer(30));
		temp1 = new BinaryNode<Integer>(new Integer(25), temp3, temp2);
		temp3 = temp1;

		temp1 = new BinaryNode<Integer>(new Integer(55));
		temp2 = new BinaryNode<Integer>(new Integer(70));
		BinaryNode<Integer> temp4 = new BinaryNode<Integer>(new Integer(60), temp1, temp2);
		temp1 = new BinaryNode<Integer>(new Integer(80));
		temp2 = new BinaryNode<Integer>(new Integer(85), temp1, null);
		temp1 = new BinaryNode<Integer>(new Integer(75), temp4, temp2);

		temp4 = new BinaryNode<Integer>(new Integer(50), temp3, temp1);
		return temp4;
	}

	// This tree will be a BST
	public static void initBST(BinarySearchTree<String> T)
	{
		T.add(new String("Outrageous"));
		T.add(new String("Zany"));
		T.add(new String("Bogus"));
		T.add(new String("Wacky"));
		T.add(new String("Weasel"));
		T.add(new String("Esoteric"));
		T.add(new String("Zippy"));
		T.add(new String("Uncertainty"));
		T.add(new String("Melancholy"));
	}
}

