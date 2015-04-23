package com.endovectors.uta.checkersai;

import java.util.*;


class ListNode {
  ListNode prev, next;
  Object   value;

  public ListNode (Object elem, ListNode prevNode, ListNode nextNode) {
    value = elem;
    prev = prevNode;
    next = nextNode;
  }
}

public class List implements Cloneable {
  private ListNode head;
  private ListNode tail;
  private int count;

  public List () {
    count = 0;
  }
  
  public void push_front (Object elem) {
    ListNode node = new ListNode (elem, null, head);
    
    if (head != null)
      head.prev = node;
    else
      tail = node;

    head = node;
    count++;
  }

  public void push_back (Object elem) {
    ListNode node = new ListNode (elem, tail, null);

    if (tail != null)
      tail.next = node;
    else
      head = node;

    tail = node;
    count++;
  }

  public Object pop_front () {
    if (head == null)
      return null;

    ListNode node = head;
    head = head.next;

    if (head != null)
      head.prev = null;
    else
      tail = null;

    count--;
    return node.value;
  }

  public Object pop_back () {
    if (tail == null)
      return null;

    ListNode node = tail;
    tail = tail.prev;

    if (tail != null)
      tail.next = null;
    else
      head = null;

    count--;
    return node.value;
  }


  public boolean isEmpty () {
    return head == null;
  }


  public int length () {
    return count;
  }

  public void append (List other) {
    ListNode node = other.head;
    
    while (node != null) {
      push_back (node.value);
      node = node.next;
    }
  }
  

  public void clear () {
    head = tail = null;
  }


  public Object peek_head () {
    if (head != null)
      return head.value;
    else
      return null;
  }

  public Object peek_tail () {
    if (tail != null)
      return tail.value;
    else
      return null;
  }
  
    
  public boolean has (Object elem) {
    ListNode node = head;

    while (node != null && !node.value.equals (elem))
      node = node.next;

    return node != null;
  }

  public Object clone () {
    List temp = new List ();
    ListNode node = head;

    while (node != null) {
      //temp.push_back (node.value.clone ());
      temp.push_back (node.value);
      node = node.next;
    }

    return temp;
  }

  public String toString () {
    String temp = "[";
    ListNode node = head;

    while (node != null) {
      temp += node.value.toString ();
      node = node.next;
      if (node != null)
        temp += ", ";
    }
    temp += "]";

    return temp;
  }

  class Enum implements Enumeration {
    private ListNode node;

    Enum (ListNode start) {
      node = start;
    }

    public boolean hasMoreElements () {
      return node != null;
    }

    public Object nextElement () throws NoSuchElementException {
      Object temp;

      if (node == null)
        throw new NoSuchElementException ();

      temp = node.value;
      node = node.next;

      return temp;
    }
  }

  public Enumeration elements () {
    return new Enum (head);
  }  
}

  
