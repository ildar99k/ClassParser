package ru.ildarka.helpers;

import ru.ildarka.models.Clazz;
import ru.ildarka.models.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deleter {
    public List<String> hashDeleter(Clazz aClass) {
        String regexp = "public\\s+int\\s+hashCode\\s*\\(\\)\\s*\\{";
        return methodDeleter(aClass, regexp);
    }

    public List<String> equalsDeleter(Clazz aClass) {
        String regexp = "public\\s+boolean\\s+equals\\s*\\(Object\\s+\\S+\\)\\s*\\{";
        return methodDeleter(aClass, regexp);
    }

    private List<String> methodDeleter(Clazz aClass, String str) {
        List<String> newClassBody = new ArrayList<>();
        Pattern pattern = Pattern.compile(str);
        Pattern pattern2 = Pattern.compile("\\}");
        boolean inEquals = false;
        Matcher matcher;
        for (String tmp : aClass.getStringList()) {
            matcher = pattern.matcher(tmp);
            Matcher matcher2 = pattern2.matcher(tmp);
            if (matcher.find()) {
                newClassBody.add(tmp.substring(0, matcher.start()));
                inEquals = true;
            } else {
                if (inEquals) {
                    if (matcher2.find()) {
                        newClassBody.add(tmp.substring(matcher2.end()));
                        inEquals = false;
                    }
                } else {
                    newClassBody.add(tmp);
                }
            }
        }
        aClass.stringList = newClassBody;
        return aClass.getStringList();
    }

    public List<String> setDeleter(Clazz aClass, Variable var) {
        String regexp = "public\\s+void\\s+set" + var.getName() + "\\s*\\(";
        return methodDeleter(aClass, regexp);
    }

    public List<String> getDeleter(Clazz aClass, Variable var) {
        String regexp = "public\\s+" + var.getType() + "\\s+get" + var.getName() + "\\s*\\(";
        return methodDeleter(aClass, regexp);
    }
}
