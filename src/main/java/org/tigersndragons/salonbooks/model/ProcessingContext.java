package org.tigersndragons.salonbooks.model;

public interface ProcessingContext {

  void pushNode(Object left, Object right, String fieldName);

  void popNode(Object choosenObject);

  void markObjectReplaced(Object old, Object newObj);

  boolean hasObjectBeenReplaced(Object obj);

  Object getReplacementObject(Object obj);

  void dumpReplacementMap();

  String getNodeStackLog();

  int getNextUniqueKey();
}
