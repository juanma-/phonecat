package org.juanma.phonecat.model;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by Juan Manuel Castillo on 20/12/15.
 * Phone
 */
@Entity
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String externalId;
  @Column
  private String name;
  @Column
  private String snippet;
  @Column
  private DateTime createdDate;

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

  public DateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(DateTime createdDate) {
    this.createdDate = createdDate;
  }

  public boolean isNew() {
    return null == id;
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
    return "Phone" + (isNew() ? "" : "@" + id) + "[" + externalId + "]";
  }
}
