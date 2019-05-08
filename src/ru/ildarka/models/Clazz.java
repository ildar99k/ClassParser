package ru.ildarka.models;

import ru.ildarka.helpers.Finder;
import ru.ildarka.models.Variable;

import java.util.List;

public class Clazz {// класс для классов
    public List<String> stringList;//все строки класса
    private List<String> varBody;//тело класса, где могут лежать переменные
    private final List<Variable> variablesList;//список перменных
    private final String name;

    public Clazz(List<String> stringList) {
        this.stringList = stringList;
        setVarBody(Finder.varBodyFinder(stringList));
        variablesList = Finder.variablesFinder(getVarBody(), getStringList());
        name = Finder.nameFinder(stringList.get(0));
    }

    private void setVarBody(List<String> varBody) {
        this.varBody = varBody;
    }

    public boolean isHaveHash() {
        return Finder.hashFinder(stringList);
    }


    public boolean isHaveEquals() {
        return Finder.equalsFinder(stringList);
    }


    public List<Variable> getVariablesList() {
        return variablesList;
    }

    public String getName() {
        return name;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    private List<String> getVarBody() {
        return varBody;
    }
}
