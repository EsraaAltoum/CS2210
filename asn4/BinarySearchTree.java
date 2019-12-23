package cs2210.esraa;

public class BinarySearchTree implements BinarySearchTreeADT {
	private BinaryNode root;

	
	public BinarySearchTree() {
			this.root = new BinaryNode();
	}
	public BinaryNode getRoot() {
		return this.root;
	}
	
	//returns the pixel storing the given key if its in the tree, returns false otherwise//
	public Pixel get(BinaryNode r, Location key) {
		if(r.isLeaf()) {
			return null;			
		}
		else {
			if(key.compareTo(r.getData().getLocation())== 0) {
				return r.getData();
			}
			else if(key.compareTo(r.getData().getLocation()) == -1) {
				return get(r.getLeft(), key);
			}
			else {
				return get(r.getRight(), key);
			}
		}
		
	}
	
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		if(this.root.getData() == null) {
			this.root = new BinaryNode(
					data, 
					new BinaryNode(null, null, null, r), 
					new BinaryNode(null, null, null, r), 
					null
				);
			return;
		}	
		
		if(data.getLocation().compareTo(r.getData().getLocation()) == 1) {
			if(r.getRight().isLeaf()) {
				r.getRight().setData(data);
				r.getRight().setLeft(new BinaryNode(null, null, null, r.getRight()));
				r.getRight().setRight(new BinaryNode(null, null, null, r.getRight()));
				return;
			}
			put(r.getRight(), data);
		}
		else if(data.getLocation().compareTo(r.getData().getLocation()) == -1) {
			if(r.getLeft().isLeaf()) {
				r.getLeft().setData(data);
				r.getLeft().setLeft(new BinaryNode(null, null, null, r.getLeft()));
				r.getLeft().setRight(new BinaryNode(null, null, null, r.getLeft()));
				return;
			}
			put(r.getLeft(), data);			
			}
		else throw new DuplicatedKeyException();

	}
		
	
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		Pixel p = get(r, key);
		if(p == null){
			throw new InexistentKeyException();
		}

		if(this.root.isLeaf()) {
			return;
		}
		
		else if(key.compareTo(r.getData().getLocation())==1)
			remove(r.getRight(),key);
		
		else if(key.compareTo(r.getData().getLocation())==-1)
			remove(r.getLeft(),key);
		
		else if(this.root.getRight().isLeaf() && this.root.getLeft().isLeaf())
			this.root.setData(null);

		else {
			if(r.getParent().getLeft() == r) {
				if(r.getRight().isLeaf() && r.getLeft().isLeaf())
					r.getParent().setLeft(new BinaryNode(null, null, null, r.getParent()));	
					
				else if(r.getRight().isLeaf() && !r.getLeft().isLeaf())
					r.getParent().setLeft(r.getLeft());	
				
				else if(!r.getRight().isLeaf() && r.getLeft().isLeaf()) {
					r.getParent().setLeft(r.getRight());	
				}
				
				else {
					p = smallest(r.getRight());
					remove(r.getRight(), p.getLocation());
					r.setData(p);
				}
			}
			
			if(r.getParent().getRight() == r) {
				if(r.getRight().isLeaf() && !r.getLeft().isLeaf()) {
					r.getParent().setRight(r.getLeft());	
				}
				
				else if(!r.getRight().isLeaf() && r.getLeft().isLeaf()) {
					r.getParent().setRight(r.getRight());	
				}
				
				else if(!r.getRight().isLeaf() && r.getLeft().isLeaf()) {
					r.getParent().setRight(new BinaryNode(null, null, null, r.getParent()));	
				}
				
				else {
					p = smallest(r.getRight());
					remove(r.getRight(), p.getLocation());
					r.setData(p);
				}
			}
		}
		}

	
	public Pixel successor(BinaryNode r, Location key) {
		if(this.root.isLeaf()) {
			return null;
		}
	
		if(key.compareTo(r.getData().getLocation()) == -1) {
			return successor(r.getLeft(), key);
		}
		else if(key.compareTo(r.getData().getLocation()) == 1){
			return successor(r.getRight(), key);
		}
		
		else if(key.compareTo(r.getData().getLocation())== 0
				&& !r.getRight().isLeaf()) {
			return smallest(r.getRight());
		}
		else {
			BinaryNode p = r.getParent();
			while(r != this.root && p.getRight() == r) {
				r = p;
				p = p.getParent();
			}
			if(r == this.root) return null;
			else return p.getData();
			
		}
	}

	public Pixel predecessor(BinaryNode r, Location key) {
		if(this.root.isLeaf()) {
			return null;
		}
	
		if(key.compareTo(r.getData().getLocation()) == -1) {
			return get(r.getLeft(), key);
		}
		else if(key.compareTo(r.getData().getLocation()) == 1){
			return get(r.getRight(), key);
		}
		
		else if(key.compareTo(r.getData().getLocation())== 0
				&& !r.getRight().isLeaf()) {
			return largest(r.getLeft());
		}
		else {
			BinaryNode p = r.getParent();
			while(r != this.root && p.getLeft() == r) {
				r = p;
				p = p.getParent();
			}
			if(r == this.root) return null;
			else return p.getData();
			
		}
	}


	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if(this.root.isLeaf()) throw new EmptyTreeException();
		if(r.isLeaf()) return null;
			
		else {
			BinaryNode p = r;
			while(!p.isLeaf()) {
				p= p.getLeft();
			}
			return p.getParent().getData();
		}
		
	}

	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if(this.root.isLeaf()) throw new EmptyTreeException();
		if(r.isLeaf()) return null;
			
		else {
			BinaryNode p = r;
			while(!p.isLeaf()) {
				p= p.getRight();
			}
			return p.getParent().getData();
		}
		
	}
}
