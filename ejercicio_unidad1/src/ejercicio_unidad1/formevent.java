package ejercicio_unidad1;

import java.util.EventObject;

public class formevent extends EventObject{

	private String name;
    private String ocu;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String gender;

    public formevent(Object source) {
        super(source);
    }

    public formevent(Object source, String name, String ocu, 
    		int ageCat, String empCat, String taxId, boolean usCitizen, String gender) {
        super(source);
        this.name = name;
        this.ocu = ocu;
        this.ageCategory = ageCat;
        this.empCat=empCat;
        this.taxId= taxId;
        this.usCitizen= usCitizen;
        this.gender=gender;
    }
    

    public String getGender() {
		return gender;
	}

	public String getTaxId() {
		return taxId;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOcu() {
        return ocu;
    }

    public void setOcu(String ocu) {
        this.ocu = ocu;
    }

    public int getAgeCategory() {
        return ageCategory;
    }
    public String getEmploymentCategory() {
    	return empCat;
    }
}
