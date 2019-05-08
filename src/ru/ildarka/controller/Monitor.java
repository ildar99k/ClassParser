package ru.ildarka.controller;

import ru.ildarka.helpers.Shower;
import ru.ildarka.models.Clazz;
import ru.ildarka.row_workers.RowExtract;
import ru.ildarka.row_workers.RowReader;
import ru.ildarka.row_workers.RowWriter;

import java.io.IOException;
import java.util.Scanner;

public class Monitor {//класс контроллер

    public static void main(String[] args) throws IOException {
        String pathFrom = args[0], pathTo = args[1];//путь
        Scanner in = new Scanner(System.in);
        System.out.println("Приветствую Вас в моей программе\n");
        System.out.println("Если вы хотите увидеть классы и наличие методов в них, нажмите 1,\n если хотите выйти 0");
        int i = in.nextInt();
        if (i == 0) {
            System.exit(0);//выход при вводе 0
        } else { //иначе работаем
            Clazz clazz;
            RowReader rowReader = new RowReader(pathFrom);//создаем объект, который возвращает массив строк из файла
            clazz = RowExtract.extractClass(rowReader); //извлекаем из него коллекцию классов
            RowWriter rw = new RowWriter(pathTo);//объект, занимающийся записью
            rw.deleteOldFile(pathTo);//очистка файла для записи
            Shower.show(clazz);//выводим классы и их элементы
            System.out.println("Если хотите перезаписать все методы во всех классах, нажмите 1");
            System.out.println("Если хотите записать только отсутствующие методы в классах, нажмите 2");
            i = in.nextInt();
            switch (i) {
                case 1: //замена или добавление всего у всех
                    rw.allRewrite(rowReader.getBeginningOfProgram(), clazz);
                    break;
                case 2:
                    rw.partWrite(rowReader.getBeginningOfProgram(), clazz);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Вы все испортили, мы выходим...");

            }
            System.exit(0);

        }
    }

}
