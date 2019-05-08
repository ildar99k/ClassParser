package ru.ildarka.row_workers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowReader {
    private final List<String> list = new ArrayList<>();
    private final List<String> beginningOfProgram = new ArrayList<>();

    public RowReader(String path) {

        try (FileReader fr = new FileReader(path);
             BufferedReader input = new BufferedReader(fr)) {
            String tmp;
            while ((tmp = input.readLine()) != null) {
                list.add(tmp);
            }
        } catch (IOException e) {
            System.out.println("Something wrong in reading");
        }
        Pattern pattern = Pattern.compile("(class(\\s+))");
        for (String tmp : list) {
            Matcher matcher = pattern.matcher(tmp);
            if (matcher.find()) {
                break;
            }
            beginningOfProgram.add(tmp);
        }


    }

    public List<String> getList() {
        return list;
    }

    public List<String> getBeginningOfProgram() {
        return beginningOfProgram;
    }
}
