package org.juanma.phonecat.model;

/**
 * Created by Juan Manuel Castillo on 20/12/15.
 * Phone
 */
public abstract class Phone {
  private Long   id;
  private String externalId;
  private String name;
  private String snippet;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSnippet() {
    return snippet;
  }

  public void setSnippet(String snippet) {
    this.snippet = snippet;
  }

  @Override
  public int hashCode() {
    return externalId.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Phone) {
      return externalId.equals(((Phone) obj).externalId);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Phone[" + getExternalId() + "]";
  }
}
