package com.voloshko.algorithms.sort;

import javax.xml.soap.Node;

/**
 * TwoTreeTree implementation
 * https://en.wikipedia.org/wiki/2%E2%80%933_tree
 *
 * Space	O(n)		O(n)
 * Search	O(log n)	O(log n)
 * Insert	O(log n)	O(log n)
 * Delete	O(log n)	O(log n)
 * @author avoloshko
 */
public class TwoThreeTree<K extends Comparable<K>, V> {

	private Node root;
	private int size;

	private static class Pair<K, V> {
		Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		K key;
		V value;

        @Override
        public String toString() {
            return key + " - " + value;
        }
    }

	private class Node {

        Node(Pair<K, V> pair) {
            kvPairLeft = pair;
        }


		Pair<K, V> kvPairLeft, kvPairRight;

        Node nodeLeft, nodeMiddle, nodeRight;

        void addLeft(Pair<K, V> pair) {
			kvPairRight = kvPairLeft;
			kvPairLeft = pair;
        }

		void addRight(Pair<K, V> pair) {
			kvPairRight = pair;
		}
	}

	public void remove(K key, V value) {

	}

	public void get(K key, V value) {

		// try to find
		Pair<K, V> pair = find(root, key);
		if (pair != null) {
			pair.value = value;
			return;
		}

		// insert
		if (root == null) {
			root = new Node(new Pair<>(key, value));
		} else {
			Node result = put(root, new Pair<>(key, value));
			if (result != null) {
				// root was changed
				root = result;
			}
		}

		size += 1;
	}

	private Node splitWithLeftNode(Node original, Node leftNode) {
		Node middleNode = new Node(original.kvPairLeft);

		Node rightNode = new Node(original.kvPairRight);
		rightNode.nodeLeft = original.nodeMiddle;
		rightNode.nodeRight = original.nodeRight;

		middleNode.nodeLeft = leftNode;
		middleNode.nodeRight = rightNode;

		return middleNode;
	}

	private Node splitWithMiddleNode(Node original, Node newNode) {
		Node middleNode = new Node(newNode.kvPairLeft);

		Node leftNode = new Node(original.kvPairLeft);
		leftNode.nodeLeft = original.nodeLeft;
		leftNode.nodeRight = newNode.nodeLeft;

		Node rightNode = new Node(original.kvPairRight);
		rightNode.nodeLeft = newNode.nodeRight;
		rightNode.nodeRight = original.nodeRight;

		middleNode.nodeLeft = leftNode;
		middleNode.nodeRight = rightNode;

		return middleNode;
	}

	private Node splitWithRightNode(Node original, Node rightNode) {
		Node middleNode = new Node(original.kvPairRight);

		Node leftNode = new Node(original.kvPairLeft);
		leftNode.nodeLeft = original.nodeLeft;
		leftNode.nodeRight = original.nodeMiddle;

		middleNode.nodeLeft = leftNode;
		middleNode.nodeRight = rightNode;

		return middleNode;
	}

	private void mergeWithRight(Node original, Node newNode) {
		original.kvPairRight = newNode.kvPairLeft;
		original.nodeMiddle = newNode.nodeLeft;
		original.nodeRight = newNode.nodeRight;
	}

	private void mergeWithLeft(Node original, Node newNode) {
		original.kvPairRight = original.kvPairLeft;
		original.kvPairLeft = newNode.kvPairLeft;
		original.nodeLeft = newNode.nodeLeft;
		original.nodeMiddle = newNode.nodeRight;
	}

	private Node put(Node node, Pair<K, V> pair) {
		int leftCompare = pair.key.compareTo(node.kvPairLeft.key);

		if (node.kvPairRight == null) {
			// single node
			if (leftCompare < 0) {
				if (node.nodeLeft != null) {
					Node newNode = put(node.nodeLeft, pair);
					if (newNode != null) {
						// a split occurred lower the tree
						mergeWithLeft(node, newNode);
						return null;
					}
				} else {
					node.addLeft(pair);
					return null;
				}
			} else {
				if (node.nodeRight != null) {
					Node newNode = put(node.nodeRight, pair);
					if (newNode != null) {
						// a split occurred lower the tree
						mergeWithRight(node, newNode);
						return null;
					}
				} else {
					node.addRight(pair);
					return null;
				}
			}
		} else {
			// double node
			if (leftCompare < 0) {
				if (node.nodeLeft != null) {
					Node newNode = put(node.nodeLeft, pair);
					if (newNode != null) {
						// a split occurred lower the tree and here we should also split the node
						return splitWithLeftNode(node, newNode);
					}
				} else {
					return splitWithLeftNode(node, new Node(pair));
				}
			} else {
				int rightCompare = pair.key.compareTo(node.kvPairRight.key);

				if (rightCompare > 0) {
					if (node.nodeRight != null) {
						Node newNode = put(node.nodeRight, pair);
						if (newNode != null) {
							// a split occurred lower the tree and here we should also split the node
							return splitWithRightNode(node, newNode);
						}
					} else {
						return splitWithRightNode(node, new Node(pair));
					}
				} else {
					if (node.nodeMiddle != null) {
						Node newNode = put(node.nodeMiddle, pair);
						if (newNode != null) {
							// a split occurred lower the tree and here we should also split the node
							return splitWithMiddleNode(node, newNode);
						}
					} else {
						return splitWithMiddleNode(node, new Node(pair));
					}
				}
			}
		}
		return null;
	}

	public V find(K key) {
		Pair<K, V> pair = find(root, key);
		if (pair != null) {
			return pair.value;
		}
		return null;
	}

	private Pair<K, V> find(Node node, K key) {
		if (node == null) {
			return null;
		}

		int compare = node.kvPairLeft.key.compareTo(key);
		if (compare == 0) {
			return node.kvPairLeft;
		}

		if (compare > 0) {
            if (node.nodeLeft != null) {
                return find(node.nodeLeft, key);
            }

            return null;
		}

		if (node.kvPairRight != null) {
            compare = node.kvPairRight.key.compareTo(key);
            if (compare == 0) {
                return node.kvPairRight;
            }

            if (compare < 0) {
                if (node.nodeRight != null) {
                    return find(node.nodeRight, key);
                }

                return null;
            }

            if (node.nodeMiddle != null) {
                return find(node.nodeMiddle, key);
            }
		} else {
			if (node.nodeRight != null) {
				return find(node.nodeRight, key);
			}
		}

        return null;
	}

	public int size() {
		return size;
	}

	public int maxHeight() {
		return maxHeight(root);
	}

	private int maxHeight(Node node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(Math.max(maxHeight(node.nodeLeft), maxHeight(node.nodeMiddle)), maxHeight(node.nodeRight));
	}
}
