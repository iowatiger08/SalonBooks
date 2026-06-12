package org.tigersndragons.salonbooks.model;

public interface Matchable<T> {
  boolean matches(T matchee);
}
