package com.ironhack.homeworkRPGSIM;

public class UniqueID {

    static Integer uniqueID = 0;

    public Integer generateID() {
        uniqueID++;
        return uniqueID;
    }

    public UniqueID() {

    }

}
