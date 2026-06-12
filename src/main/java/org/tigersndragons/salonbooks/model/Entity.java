package org.tigersndragons.salonbooks.model;

public interface Entity extends Matchable<Entity> {
  Long getId();

  boolean matches(Entity entity);
}
