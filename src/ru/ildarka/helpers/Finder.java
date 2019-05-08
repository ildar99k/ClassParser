package ru.ildarka.helpers;

import ru.ildarka.models.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public static boolean hashFinder(List<String> stringList) {
        Pattern pattern = Pattern.compile("public\\s+int\\s+hashCode\\s*\\(\\)\\s*\\{");
        Matcher matcher;
        for (String tmp : stringList) {
            matcher = pattern.matcher(tmp);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsFinder(List<String> stringList) {
        Pattern pattern = Pattern.compile("public\\s+boolean\\s+equals\\s*\\(Object\\s+\\S+\\)\\s*\\{");
        Matcher matcher;
        for (String tmp : stringList) {
            matcher = pattern.matcher(tmp);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public static List<Variable> variablesFinder(List<String> varBody, List<String> stringList) {
        List<Variable> listOfVar = new ArrayList<>();
        String regexp = "(\\w+\\s+)?(\\w+\\s+)?(\\w+\\s+)(\\w+)(\\;|\\=.*)";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher;
        for (String tmp : varBody) {
            matcher = pattern.matcher(tmp);
            if (matcher.find()) {
                listOfVar.add(new Variable(matcher.group(4), matcher.group(3), matcher.group(1), stringList));
            }
        }
        return listOfVar;
    }

    public static String nameFinder(String string) {
        Pattern pattern = Pattern.compile("(class(\\s+))[^\\{\\}\\s]+");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        Pattern pattern1 = Pattern.compile("(\\s)+(\\w)*");
        Matcher matcher1 = pattern1.matcher(matcher.group());
        matcher1.find();
        return matcher1.group().trim();
    }

    public static List<String> varBodyFinder(List<String> bodyClass) {
        List<String> list = new ArrayList<>();
        int levelNumber = 0;
        Pattern pattern1 = Pattern.compile("\\{");
        Pattern pattern2 = Pattern.compile("\\}");
        for (String tmp : bodyClass) {
            Matcher matcher1 = pattern1.matcher(tmp);
            Matcher matcher2 = pattern2.matcher(tmp);
            while (matcher1.find()) {
                levelNumber++;
            }

            while (matcher2.find()) {
                levelNumber--;
            }
            if ((levelNumber == 1) && (!tmp.equals(bodyClass.get(0)))) {
                list.add(tmp);
            }
        }
        return list;
    }
}
