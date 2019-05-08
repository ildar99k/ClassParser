package ru.ildarka.row_workers;

import ru.ildarka.helpers.Adder;
import ru.ildarka.models.Clazz;
import ru.ildarka.models.Variable;
import ru.ildarka.helpers.Deleter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RowWriter {
    private BufferedWriter bw;

    public RowWriter(String path) throws IOException {
        bw = new BufferedWriter(new FileWriter(path, true));
    }

    public void deleteOldFile(String path) throws IOException {
        BufferedWriter bd = new BufferedWriter(new FileWriter(path, false));
        bd.write("");
        bd.close();
    }

    public void allRewrite(List<String> beginning, Clazz clazz) throws IOException {
        Deleter deleter = new Deleter();
        List<String> strings;
        List<String> programBody = new ArrayList<>();
        if (clazz.isHaveEquals()) {
            clazz.setStringList(deleter.equalsDeleter(clazz));
        }
        if (clazz.isHaveHash()) {
            clazz.setStringList(deleter.hashDeleter(clazz));
        }
        strings = Adder.equalsAdd(clazz);
        clazz.stringList.addAll(1, strings);
        strings = Adder.hashAdd(clazz);
        clazz.stringList.addAll(1, strings);
        for (Variable var : clazz.getVariablesList()) {
            if (var.isHaveGet()) {
                clazz.stringList = deleter.getDeleter(clazz, var);
            }
            if (var.isHaveSet()) {
                clazz.stringList = deleter.setDeleter(clazz, var);
            }
            strings = Adder.setAdd(var);
            clazz.stringList.addAll(1, strings);
            strings = Adder.getAdd(var);
            clazz.stringList.addAll(1, strings);

        }

        programBody.addAll(beginning);
        programBody.addAll(clazz.getStringList());
        for (String tmp : programBody) {
            bw.write(tmp);
            bw.newLine();
        }
        bw.close();
    }

    public void partWrite(List<String> begining, Clazz clazz) throws IOException {
        List<String> strings;
        List<String> programBody = new ArrayList<>();
        if (!clazz.isHaveEquals()) {
            strings = Adder.equalsAdd(clazz);
            clazz.stringList.addAll(1, strings);
        }
        if (!clazz.isHaveHash()) {
            strings = Adder.hashAdd(clazz);
            clazz.stringList.addAll(1, strings);
        }
        for (Variable var : clazz.getVariablesList()) {
            if (!var.isHaveGet()) {
                strings = Adder.getAdd(var);
                clazz.stringList.addAll(1, strings);
            }
            if (!var.isHaveSet()) {
                strings = Adder.setAdd(var);
                clazz.stringList.addAll(1, strings);
            }

        }

        programBody.addAll(begining);
        programBody.addAll(clazz.stringList);
        for (String tmp : programBody) {
            bw.write(tmp);
            bw.newLine();
        }
        bw.close();
    }
}

