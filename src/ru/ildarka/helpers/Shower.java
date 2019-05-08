package ru.ildarka.helpers;

import ru.ildarka.models.Clazz;
import ru.ildarka.models.Variable;

public class Shower {//выводит имя класса и его флаги

    public static void show(Clazz clazz) {
        System.out.println(clazz.getName() + " hasHash()- " + clazz.isHaveHash() + " hasEquals()- " + clazz.isHaveEquals());
        clazz.getVariablesList().stream().forEach(var-> System.out.println((var.getAccess() + " " + var.getType() + " \"" + var.getName() + "\" hasSet-" + var.isHaveSet() + " hasGet-" + var.isHaveGet()).trim()));

    }
}
