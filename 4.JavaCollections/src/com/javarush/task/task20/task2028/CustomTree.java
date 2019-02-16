package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return root.lineNumber;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        return root.add(s);
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof String) {
            if (o.toString().equals(root.elementName))
                return false;
            return root.remove((String) o) > 0;
        }
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
        return s.equals(root.elementName) ? null : root.getParent(s);
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        public boolean add(String s) {
            if (isAvailableToAddChildren()) {
                if (leftChild == null) {
                    leftChild = new Entry<>(s);
                    leftChild.parent = this;
                    leftChild.lineNumber = 1;
                }
                else {
                    rightChild = new Entry<>(s);
                    rightChild.parent = this;
                    rightChild.lineNumber = 1;
                }
                checkChildren();
            }
            else {
                if (Integer.highestOneBit(leftChild.lineNumber) == Integer.highestOneBit(leftChild.lineNumber + 1) || leftChild.lineNumber == rightChild.lineNumber)
                    leftChild.add(s);
                else
                    rightChild.add(s);
            }
            lineNumber++;
            return true;
        }

        public String getParent(String s) {
            String string = null;

            if (leftChild != null)
                if (leftChild.elementName.equals(s))
                    return elementName;
                else
                    string = leftChild.getParent(s);

            if (string != null)
                return string;

            if (rightChild != null)
                if (rightChild.elementName.equals(s))
                    return elementName;
                else
                    string = rightChild.getParent(s);

            return string;
        }

        public int remove(String s) {
            int flag = 0;

            if (leftChild != null)
                if (leftChild.elementName.equals(s)) {
                    flag = leftChild.lineNumber;
                    leftChild = null;
                    availableToAddLeftChildren = true;
                    lineNumber -= flag;
                }
                else {
                    flag = leftChild.remove(s);
                    if (flag > 0)
                        lineNumber -= flag;
                }

            if (flag > 0)
                return flag;

            if (rightChild != null)
                if (rightChild.elementName.equals(s)) {
                    flag = rightChild.lineNumber;
                    rightChild = null;
                    availableToAddRightChildren = true;
                    lineNumber -= flag;
                }
                else {
                    flag = rightChild.remove(s);
                    if (flag > 0)
                        lineNumber -= flag;
                }
            return flag;
        }
    }
}
