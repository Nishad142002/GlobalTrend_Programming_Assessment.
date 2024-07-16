package com.problem9;

import java.util.ArrayList;
import java.util.List;

class IntervalTree {

    private static class IntervalNode {
        int start;
        int end;
        int max;
        IntervalNode left;
        IntervalNode right;

        IntervalNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.max = end;
        }
    }

    private IntervalNode root;

    public void insertInterval(int start, int end) {
        root = insert(root, start, end);
    }

    private IntervalNode insert(IntervalNode node, int start, int end) {
        if (node == null) {
            return new IntervalNode(start, end);
        }

        if (start < node.start) {
            node.left = insert(node.left, start, end);
        } else {
            node.right = insert(node.right, start, end);
        }

        node.max = Math.max(node.max, end);
        return node;
    }

    public void deleteInterval(int start, int end) {
        root = delete(root, start, end);
    }

    private IntervalNode delete(IntervalNode node, int start, int end) {
        if (node == null) {
            return null;
        }

        if (start < node.start) {
            node.left = delete(node.left, start, end);
        } else if (start > node.start) {
            node.right = delete(node.right, start, end);
        } else {
            if (node.end == end) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    IntervalNode minNode = findMin(node.right);
                    node.start = minNode.start;
                    node.end = minNode.end;
                    node.right = delete(node.right, minNode.start, minNode.end);
                }
            } else {
                node.right = delete(node.right, start, end);
            }
        }

        if (node != null) {
            node.max = Math.max(node.end, Math.max(max(node.left), max(node.right)));
        }

        return node;
    }

    private IntervalNode findMin(IntervalNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int max(IntervalNode node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }

    public List<int[]> findOverlappingIntervals(int start, int end) {
        List<int[]> result = new ArrayList<>();
        findOverlaps(root, start, end, result);
        return result;
    }

    private void findOverlaps(IntervalNode node, int start, int end, List<int[]> result) {
        if (node == null) {
            return;
        }

        if (isOverlapping(node.start, node.end, start, end)) {
            result.add(new int[]{node.start, node.end});
        }

        if (node.left != null && node.left.max >= start) {
            findOverlaps(node.left, start, end, result);
        }

        if (node.right != null && node.right.start <= end) {
            findOverlaps(node.right, start, end, result);
        }
    }

    private boolean isOverlapping(int start1, int end1, int start2, int end2) {
        return start1 <= end2 && start2 <= end1;
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        tree.insertInterval(15, 20);
        tree.insertInterval(10, 30);
        tree.insertInterval(17, 19);
        tree.insertInterval(5, 20);
        tree.insertInterval(12, 15);
        tree.insertInterval(30, 40);

        System.out.println("Overlapping intervals with [14, 16]:");
        List<int[]> overlaps = tree.findOverlappingIntervals(14, 16);
        for (int[] interval : overlaps) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

        tree.deleteInterval(10, 30);
        System.out.println("Overlapping intervals with [14, 16] after deleting [10, 30]:");
        overlaps = tree.findOverlappingIntervals(14, 16);
        for (int[] interval : overlaps) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

