package enterprise.Entity;


import javax.persistence.Column;

public class Type {
    @Column(name = "enterpriseName")
    private String companyName;
    @Column(name = "Type")
    private String typeName;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
