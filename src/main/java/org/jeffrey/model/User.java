package org.jeffrey.model;

import org.jeffrey.base.BaseModel;

/**
 * Created by Jeffrey on 2017-11-12.
 */
public class User extends BaseModel{
  private String id;
  private String name;

  public User(String id,String name){
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
