package com.flintcore.tarea_intent.info;

import java.util.Objects;

public class UserInfo {

    private static UserInfo field;

    private String name;
    private String gender;


    private UserInfo() {
    }

    public static UserInfo getInstance() {
        if (Objects.isNull(field)) {
            field = new UserInfo();
        }

        return field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static boolean isNew(){
        return getInstance().isRecentCreated();
    }

    protected boolean isRecentCreated() {
        return Objects.isNull(this.name) || this.name.isEmpty()
                || Objects.isNull(this.gender) || this.gender.isEmpty();
    }
}
