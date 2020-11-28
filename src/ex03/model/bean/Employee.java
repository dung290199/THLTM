package ex03.model.bean;

public class Employee {
    private int id;
    private String name;
    private int idpb;
    private String address;

    public Employee() {
    }

    public Employee(int id, String name, int idpb, String address) {
        this.id = id;
        this.name = name;
        this.idpb = idpb;
        this.address = address;
    }

    public Employee(String name, int idpb, String address) {
        this.name = name;
        this.idpb = idpb;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdpb() {
        return idpb;
    }

    public void setIdpb(int idpb) {
        this.idpb = idpb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
