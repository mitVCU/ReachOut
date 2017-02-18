package com.jzheadley.reachout.models.dataobjects;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;

@DynamoDBTable(tableName = "ReachOutPersons")
public class Person {
    protected String name = " ";
    private String personId = " ";
    private double longitude = 0;
    private double lattitude = 0;
    private String profile_picture = " ";
    private String passHash = " ";
    private boolean isLeader = false;
   // private String idOfConfirmingLeader = " ";
    private ArrayList<String> proposals;


    public Person() {
        proposals = new ArrayList<>();
        proposals.add(" ");
    }

    public Person(String personId, boolean isLeader, String name, double longitude, double lattitude,
                  String passHash) {
        this.personId = personId;
        this.isLeader = isLeader;
        this.name = name;
        this.passHash = passHash;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.profile_picture = " ";

        //this.idOfConfirmingLeader = idOfConfirmingLeader;
        this.proposals = new ArrayList<>();

    }

    @DynamoDBHashKey(attributeName = "personId")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setPerson_id(String personId) {
        this.personId = personId;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    @DynamoDBAttribute
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @DynamoDBAttribute
    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    @DynamoDBAttribute
    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    @DynamoDBAttribute
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    //public String getIdOfConfirmingLeader() {
    //    return idOfConfirmingLeader;
    //}

    //public void setIdOfConfirmingLeader(String idOfConfirmingLeader) {
      //  this.idOfConfirmingLeader = idOfConfirmingLeader;
    //}

    public ArrayList<String> getProposals() {
        return proposals;
    }

    public void setProposals(ArrayList<String> proposals) {
        this.proposals = proposals;
    }
}
