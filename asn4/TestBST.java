package cs2210.esraa;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class TestBST {

	private static BinarySearchTree tree;

	public static void main(String[] args) {
		tree = new BinarySearchTree();
		Pixel res;

		// Tests with few data items

		Pixel pixels[] = new Pixel[5];
		Location points[] = new Location[5];
		int colors[] = {5, 3, 1, 2, 4};
		int x[] = {4,2,1,1,3};
		int y[] = {4,3,1,2,4};

		for (int i = 0; i < 5; ++i) {
			points[i] = new Location(x[i],y[i]);
			pixels[i] = new Pixel(points[i],colors[i]);
		}

		try {
			tree.put(tree.getRoot(),pixels[0]);
			res = tree.get(tree.getRoot(),points[0]); // Look for existent data
			if (res.getColor() == colors[0])
				System.out.println("Test 1 passed");
			else System.out.println("Test 1 failed");
		}
		catch(Exception e) {
			System.out.println("Test 1 failed");
		}

		try {
			res = tree.get(tree.getRoot(),points[1]); // Look for inexistent data
			if (res == null) System.out.println("Test 2 passed");
			else System.out.println("Test 2 failed");
		}
		catch(Exception e) {
			System.out.println("Test 2 failed");
		}

		try {
			tree.put(tree.getRoot(),pixels[0]);  // Try to insert same key twice
			System.out.println("Test 3 failed");
		}
		catch(DuplicatedKeyException e) {
			System.out.println("Test 3 passed");
		}
		catch (Exception e) {
			System.out.println("Test 3 failed");
		}

		try {
			tree.put(tree.getRoot(),pixels[1]);
			tree.remove(tree.getRoot(),points[1]);
			res = tree.get(tree.getRoot(),points[1]);  // Test data deletion
			if (res == null) System.out.println("Test 4 passed");
			else System.out.println("Test 4 failed");
		}
		catch(Exception e) {
			System.out.println("Test 4 failed");
		}

		try {
			tree.remove(tree.getRoot(),points[3]);  // Delete inexistent data
			System.out.println("Test 5 failed");
		}
		catch(InexistentKeyException e) {
			System.out.println("Test 5 passed");
		}
		catch (Exception e) {
			System.out.println("Test 5 failed");
		}

		try {
			// For a tree with 5 data items and check successor method
			tree.remove(tree.getRoot(),points[0]);
			tree.put(tree.getRoot(),pixels[1]);
			tree.put(tree.getRoot(),pixels[0]);
			for (int i = 2; i < 5; ++i)
				tree.put(tree.getRoot(),pixels[i]);

			res = tree.successor(tree.getRoot(),points[3]);
			if (res.getLocation().compareTo(points[1]) == 0)
				System.out.println("Test 6 passed");
			else System.out.println("Test 6 failed");
		}
		catch (Exception e) {
			System.out.println("Test 6 failed");
		}

		try {
			res = tree.successor(tree.getRoot(),points[2]);
			if (res.getLocation().compareTo(points[3]) == 0)
				System.out.println("Test 7 passed");
			else System.out.println("Test 7 failed");
		}
		catch (Exception e) {
			System.out.println("Test 7 failed");
		}

		// Check predecessor method
		try {
			res = tree.predecessor(tree.getRoot(),points[0]);
			if (res.getLocation().compareTo(points[4]) == 0)
				System.out.println("Test 8 passed");
			else System.out.println("Test 8 failed");
		}
		catch (Exception e) {
			System.out.println("Test 8 failed");
		}

		try {
			res = tree.predecessor(tree.getRoot(),points[4]);
			if (res.getLocation().compareTo(points[1]) == 0)
				System.out.println("Test 9 passed");
			else System.out.println("Test 9 failed");
		}
		catch (Exception e) {
			System.out.println("Test 9 failed");
		}


		/* Tests with a large set of pixels */
		BufferedImage im;
		int width;
		tree = new BinarySearchTree();
		Location p = new Location(3,25);

		try {
			// Insert data in tree and test find method

			im = ImageIO.read(new File("g.jpg"));
			width = im.getWidth();
			insertPoints(im,0,0,width-1,im.getHeight()-1,width); 
			res = tree.get(tree.getRoot(),p);  
			if (res == null) System.out.println("Test 10 failed");
			else System.out.println("Test 10 passed");
		}
		catch (IOException e) {
			System.out.println("Error opening file. "+e.getMessage());
			System.out.println("Test 10 aborted");

		}
		catch (DuplicatedKeyException e) {
			System.out.println("Test 10 failed");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Test 10 failed");
		}

		p = new Location(50,25);
		try {
			res = tree.get(tree.getRoot(),p);
			if (res != null) System.out.println("Test 11 failed");
			else System.out.println("Test 11 passed");
		}
		catch (Exception e) {
			System.out.println("Test 11 failed");
		}

		// Test remove method
		p = new Location(19,2);
		try {
			tree.remove(tree.getRoot(),p);
			res = tree.get(tree.getRoot(),p);
			if (res != null) System.out.println("Test 12 failed");
			else {
			    res = tree.get(tree.getRoot(),new Location(1,0));
			    if (res == null) System.out.println("Test 12 failed");
			    else {
				res = tree.get(tree.getRoot(),new Location(19,10));
				if (res == null) System.out.println("Test 12 failed");
				else System.out.println("Test 12 passed");
			    }
			}
		}
		catch (Exception e) {
			System.out.println("Test 12 failed");
		}

		// Try to insert duplicated key
		p = new Location(3,35);
		try {
			tree.put(tree.getRoot(),new Pixel(p,1));
			System.out.println("Test 13 failed");
		}
		catch(DuplicatedKeyException e) {
			System.out.println("Test 13 passed");
		}
		catch (Exception e) {
			System.out.println("Test 13 failed");
		}

		// Test predecessor method
		p = new Location(4,25);
		try {
			res = tree.predecessor(tree.getRoot(),p);

			if (res.getLocation().compareTo(new Location(4,24)) == 0) 
				System.out.println("Test 14 passed");
			else System.out.println("Test 14 failed");
		}
		catch (Exception e) {
			System.out.println("Test 13 failed");
		}

		// Test successor method
		p = new Location(1,25);
		try {
			res = tree.successor(tree.getRoot(),p);

			if (res.getLocation().compareTo(new Location(1,26)) == 0)
				System.out.println("Test 15 passed");
			else System.out.println("Test 15 failed");
		}
		catch (Exception e) {
			System.out.println("Test 15 failed");
		}

		// Test remove method
		p = new Location(39,19);
		try {
			tree.remove(tree.getRoot(),p);
			res = tree.successor(tree.getRoot(),new Location(39,19));

			if (res.getLocation().compareTo(new Location(39,20)) != 0)
				System.out.println("Test 16 failed");
			else {
			    res = tree.get(tree.getRoot(),new Location(39,4));
			    if (res == null) System.out.println("Test 16 failed");
			    System.out.println("Test 16 passed");
			}
		}
		catch (Exception e) {
			System.out.println("Test 16 failed");
		}

	}


	/* =================================================================== */
	private static void insertPoints(BufferedImage im, int xf, int yf, int xl, 
			int yl, int width) throws DuplicatedKeyException{
		/* =================================================================== */
		/* Insert the pixels into the binary search tree */
		int m, xm, ym, rgb;
		int xm1, xm2, ym1, ym2;


		/* Perform a binary splitting of the set of pixels and insert them 
           in that order in the binary search tree  */
		if ((yf < yl) || ((yf == yl) && (xf <= xl))) {
			m = (yf*width+xf+yl*width+xl)/2;   // Pixel in the middle of figure
			xm = m % width;           // Coordinates of the middle pixel
			ym = m / width;
			rgb = im.getRGB(xm,ym);   // Color of middle pixel
			tree.put(tree.getRoot(),new Pixel(new Location(xm,ym),rgb));

			// Split the figure around the middle pixel
			if (xm > 0) {
				xm1 = xm -1;
				ym1 = ym;
			}
			else {
				xm1 = width -1;
				ym1 = ym -1;
			}
			if (xm < width -1) {
				xm2 = xm+1;
				ym2 = ym;
			}
			else {
				xm2 = 0;
				ym2 = ym+1;
			}

			// Insert the pixels in each half of the figure
			insertPoints(im,xf,yf,xm1,ym1,width);
			insertPoints(im,xm2,ym2,xl,yl,width);

		}
	}

}
