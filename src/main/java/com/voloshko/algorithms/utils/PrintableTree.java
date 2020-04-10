package com.voloshko.algorithms.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintableTree {

  final String name;
  final List<PrintableTree> children = new ArrayList<>();

  public PrintableTree(String name) {
    this.name = name;
  }

  public void add(PrintableTree child) {
    children.add(child);
  }

  public String toString() {
    StringBuilder buffer = new StringBuilder(50);
    print(buffer, "", "");
    return buffer.toString();
  }

  private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
    buffer.append(prefix);
    buffer.append(name);
    buffer.append('\n');
    for (Iterator<PrintableTree> it = children.iterator(); it.hasNext();) {
      PrintableTree next = it.next();
      if (it.hasNext()) {
        next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
      } else {
        next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
      }
    }
  }
}
