package gameMain;

import crew.CrewMember;

import java.util.ArrayList;

public class Container {
    private Test test;
    private ArrayList<CrewMember> crew;

    public Container(Test test, ArrayList<CrewMember> crew) {
        this.test = test;
        this.crew = crew;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public ArrayList<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewMember> crew) {
        this.crew = crew;
    }
}
