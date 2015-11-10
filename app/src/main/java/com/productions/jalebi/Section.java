package com.productions.jalebi;

/**
 * Created by sathvik on 11/10/2015.
 */
public class Section {

    private int sectionId;
    private String sectionName;

    public Section(int sectionId, String sectionName) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    public int getSectionId() {
        return sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
