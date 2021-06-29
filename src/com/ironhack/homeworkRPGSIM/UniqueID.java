package com.ironhack.homeworkRPGSIM;

public class UniqueID {

    // **** I HAVE MADE THIS STATIC FOR CONSISTENCY ****
    static Integer uniqueID = 0;

    public Integer generateID() {
        uniqueID++;
        return uniqueID;
    }

    public UniqueID() {

    }

}
