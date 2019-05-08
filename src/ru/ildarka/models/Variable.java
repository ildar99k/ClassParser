package ru.ildarka.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable {// класс переменной
    private String name; //имя переменной
    private String access = " ";//доступ
    private String type;//тип
    private boolean haveSet;//наличие сеттера
    private boolean haveGet;//наличие геттера

    public Variable(String name, String type, String access, List<String> varBody) {//конструктор
        setAccess(access);
        setName(name.trim());
        setType(type.trim());
        checkGetHaves(varBody);
        checkSetHaves(varBody);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAccess() {
        return access;
    }

    private void setAccess(String access) {
        this.access = access;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public boolean isHaveSet() {
        return haveSet;
    }

    private void setHaveSet(boolean haveSet) {
        this.haveSet = haveSet;
    }

    public boolean isHaveGet() {
        return haveGet;
    }

    private void setHaveGet(boolean haveGet) {
        this.haveGet = haveGet;
    }

    private void checkGetHaves(List<String> stringList) {
        String str = String.join(" ", stringList);
        String regexp = "public\\s+void\\s+set" + getName() + "\\s*\\(";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            setHaveGet(true);
        }

    }

    private void checkSetHaves(List<String> stringList) {
        String str = String.join(" ", stringList);
        String regexp = "public\\s+" + getType() + "\\s+get" + getName() + "\\s*\\(";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            setHaveSet(true);
        }
    }

}
