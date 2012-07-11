package pl.kwisniewski.entities.plain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pl.kwisniewski.database.abstr.AbstractEntity;

@NamedQuery(name="SimpleEntity.getAllSimpleEntity", 
		query="SELECT s FROM SimpleEntity s")
@Entity(name="SimpleEntity")
@Table(name="SIMPLE_TABLE")
public class SimpleEntity extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TestEntity: ");
		sb.append("- id: " + getId());
		sb.append("- name: " + getName());
		return sb.toString();
	}	
	

}
