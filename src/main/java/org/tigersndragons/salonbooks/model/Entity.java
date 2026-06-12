package org.tigersndragons.salonbooks.model;


public interface Entity extends Matchable<Entity> {
	public Long getId();
	public boolean matches(Entity entity);
}
