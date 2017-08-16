package com.jichuangtech.clothshopserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "region", schema = "clothShop", catalog = "")
public class RegionEntity {
	private long id;
	private String name;
	private int level;
	private long parentId;
	@Id
    @Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
    @Column(name = "level")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Basic
    @Column(name = "parent_id")
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	  
	  
}
