package ru.ildarka.helpers;

import ru.ildarka.models.Clazz;
import ru.ildarka.models.Variable;

import java.util.ArrayList;
import java.util.List;

public class Adder {
    public static List<String> hashAdd(Clazz aClass) {
        List<String> addHash = new ArrayList<>();
        addHash.add("public int hashCode() {");
        addHash.add("int result=1;");
        for (Variable var : aClass.getVariablesList()) {
            addHash.add("result=result*17+Objects.hash(" + var.getName() + ");");
        }
        addHash.add("return result;");
        addHash.add("}");
        return addHash;

    }

    public static List<String> equalsAdd(Clazz aClass) {
        List<String> addEq = new ArrayList<>();
        addEq.add("public boolean equals(Object o) {");
        addEq.add("        if (this == o) return true;");
        addEq.add("        if (!(o instanceof " + aClass.getName() + ")) return false;");
        addEq.add(" " + aClass.getName() + " " + aClass.getName() + "1 = (" + aClass.getName() + ") o;");
        addEq.add("        return true ");
        for (Variable var : aClass.getVariablesList()) {
            addEq.add("&& Objects.equals(" + var.getName() + "," + aClass.getName() + "1." + var.getName() + ")");
        }
        addEq.add(";");
        addEq.add("}");
        return addEq;
    }

    public static List<String> setAdd(Variable var) {
        List<String> addSet = new ArrayList<>();
        addSet.add("public void set" + var.getName() + "(" + var.getType() + " " + var.getName() + ") {");
        addSet.add("    this." + var.getName() + " = " + var.getName() + ";");
        addSet.add("}");
        return addSet;
    }

    public static List<String> getAdd(Variable var) {
        List<String> addGet = new ArrayList<>();
        addGet.add("public " + var.getType() + " get" + var.getName() + "()" +
                "{");
        addGet.add("return " + var.getName() + ";");
        addGet.add("}");

        return addGet;
    }

}
