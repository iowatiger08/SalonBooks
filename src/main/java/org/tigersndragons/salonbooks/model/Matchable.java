package org.tigersndragons.salonbooks.model;

public interface Matchable<T> {
	public boolean matches(T matchee);
}
