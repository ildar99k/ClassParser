package ru.ildarka.row_workers;

import ru.ildarka.models.Clazz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowExtract {
    public static Clazz extractClass(RowReader rowReader) {
        int beginningOfClass = 0, endOfClass = 0;
        Pattern pattern = Pattern.compile("(class(\\s+))");
        for (int i = 0; i < rowReader.getList().size(); i++) {
            Matcher matcher = pattern.matcher(rowReader.getList().get(i));
            if (matcher.find()) {
                beginningOfClass = i;
                int count = 0;
                Pattern pattern1 = Pattern.compile("(\\{)");
                Pattern pattern2 = Pattern.compile("\\}");
                i--;
                do {
                    i++;
                    Matcher matcher1 = pattern1.matcher(rowReader.getList().get(i));
                    Matcher matcher2 = pattern2.matcher(rowReader.getList().get(i));
                    while (matcher1.find()) {
                        count++;
                    }
                    while (matcher2.find()) {
                        count--;
                    }
                } while (count != 0);
                endOfClass = i;
            }
        }
        return (new Clazz(rowReader.getList().subList(beginningOfClass, endOfClass + 1)));
    }
}
