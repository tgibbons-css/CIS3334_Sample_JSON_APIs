package css.sampleAPIs;

// https://statsapi.web.nhl.com/api/v1/people/8476437/
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

import java.util.ArrayList;

class CurrentTeam{
    public int id;
    public String name;
    public String link;
}

class PrimaryPosition{
    public String code;
    public String name;
    public String type;
    public String abbreviation;
}

class Person{
    public int id;
    public String fullName;
    public String link;
    public String firstName;
    public String lastName;
    public String primaryNumber;
    public String birthDate;
    public int currentAge;
    public String birthCity;
    public String birthCountry;
    public String nationality;
    public String height;
    public int weight;
    public boolean active;
    public boolean alternateCaptain;
    public boolean captain;
    public boolean rookie;
    public String shootsCatches;
    public String rosterStatus;
    public CurrentTeam currentTeam;
    public PrimaryPosition primaryPosition;
}

public class NhlPlayer{
    public String copyright;
    public ArrayList<Person> people;
}