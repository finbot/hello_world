package pl.kwisniewski.entities.plain;


public class SimpleEntity{
	

    private static final long serialVersionUID = 1L;
	
	private String _id;
    private String _rev;
    private String name;


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }
    public void set_rev(String _rev) {
        this._rev = _rev;
    }

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
		sb.append("- id: " + get_id());
        sb.append("- rev: " + get_rev());
		sb.append("- name: " + getName());
		return sb.toString();
	}	
	

}
