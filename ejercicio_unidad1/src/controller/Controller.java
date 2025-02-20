package controller;

import gui.formevent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
    
    private Database db = new Database();
    
    public void addPerson(formevent e) {
        String name = e.getName();
        String ocu = e.getOcu();
        int ageCatId = e.getAgeCategory();
        String empCat = e.getEmploymentCategory();
        boolean isUs = e.isUsCitizen();
        String taxId = e.getTaxId();
        String gender = e.getGender();
        
        AgeCategory ageCategory = null;
        
        switch (ageCatId) {
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;
            default:
                System.err.println("Invalid AgeCategory ID: " + ageCatId);
        }
        
        EmploymentCategory empCategory;
        
        if (empCat.equals("Employed")) {
            empCategory = EmploymentCategory.employed;
        } else if (empCat.equals("Self-Employed")) {
            empCategory = EmploymentCategory.selfEmployed;
        } else if (empCat.equals("Unemployed")) {
            empCategory = EmploymentCategory.unemployed;
        } else {
            empCategory = EmploymentCategory.other;
            System.err.println("Unknown Employment Category: " + empCat);
        }
        
        Gender genderCat = gender.equals("male") ? Gender.male : Gender.female;
        
        Person person = new Person(ageCatId, name, ocu, ageCategory, empCategory, taxId, isUs, genderCat);
        db.addPerson(person);
    }
}