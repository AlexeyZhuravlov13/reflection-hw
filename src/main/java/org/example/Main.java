package org.example;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    private static void task1() {
        //1) Получение информации о классе:
        //Напишите программу, которая выводит имя класса, пакет, модификаторы доступа, и другую информацию о классе, используя рефлексию.
        Car car = new Car();
        Class<? extends Car> aClass = car.getClass();

        // 1
        String className = aClass.getSimpleName();
        String packageName = aClass.getPackageName();
        int modifiers = aClass.getModifiers();
        boolean isFinal = Modifier.isFinal(modifiers);
        boolean isAbstract = Modifier.isAbstract(modifiers);
        boolean isInterface = Modifier.isInterface(modifiers);
        boolean isPrivate = Modifier.isPrivate(modifiers);
        boolean isPublic = Modifier.isPublic(modifiers);
        boolean isStatic = Modifier.isStatic(modifiers);

        ClassLoader classLoader = aClass.getClassLoader();
        Class<?> superclass = aClass.getSuperclass();

        Method[] declaredMethods = aClass.getDeclaredMethods();

        Field[] fields = aClass.getDeclaredFields();

        AnnotatedType[] annotatedInterfaces = aClass.getAnnotatedInterfaces();
        Constructor<?>[] constructors = aClass.getConstructors();

        System.out.println("\nTASK 1");
        System.out.println("className: " + className);
        System.out.println("packageName: " + packageName);
        System.out.println("\nmodifiers: ");
        System.out.println("isFinal: " + isFinal);
        System.out.println("isAbstract: " + isAbstract);
        System.out.println("isInterface: " + isInterface);
        System.out.println("isPrivate: " + isPrivate);
        System.out.println("isPublic: " + isPublic);
        System.out.println("isStatic: " + isStatic);
        System.out.println();
        System.out.println("classLoader: " + classLoader);
        System.out.println("superclass: " + superclass);
        System.out.println("declaredMethods: " + Arrays.asList(declaredMethods));
        System.out.println("fields: " + Arrays.asList(fields));
        System.out.println("annotatedInterfaces: " + Arrays.asList(annotatedInterfaces));
        System.out.println("constructors: " + Arrays.asList(constructors));
    }

    private static void task2() {
        //2) Создание объекта с использованием рефлексии:
        //Напишите метод, который принимает имя класса в виде строки и возвращает новый экземпляр этого класса, используя рефлексию.
        System.out.println("\nTASK 2");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter class name with package");
            String classToLoad = scanner.nextLine();
            Class<?> aClass = Class.forName(classToLoad);
            Constructor<?> constructor = aClass.getConstructor();
            Object o = constructor.newInstance();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong class name, aborting");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void task3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //3) Вызов метода по имени:
        //Напишите программу, которая позволяет вызывать метод объекта по его имени, передавая аргументы.
        System.out.println("\nTASK 3");
        Car car = new Car();
        Class<? extends Car> aClass = car.getClass();
        Method drive = aClass.getMethod("drive", String.class);
        drive.invoke(car, "home");
    }

    private static void task4() {
        //4) Анализ аннотаций:
        //Создайте класс с несколькими методами, аннотируйте их различными аннотациями. Затем напишите программу,
        // которая с использованием рефлексии анализирует класс и выводит информацию обо всех аннотациях, присвоенных методам.
        System.out.println("\nTASK 4");
        Class<? extends Car> aClass = Car.class;
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method name: " + method.getName() + " has annotations: " + Arrays.toString(method.getAnnotations()));
        }
    }

    private static void task5() throws NoSuchFieldException, IllegalAccessException {
        //5) Динамическая модификация класса:
        //Реализуйте программу, которая динамически изменяет значение приватного поля объекта с использованием рефлексии.
        System.out.println("\nTASK 5");
        Car car = new Car(1999, "Red");
        System.out.println("Before: " + car);
        Class<? extends Car> aClass = car.getClass();
        Field year = aClass.getDeclaredField("year");
        year.setAccessible(true);
        year.setInt(car, 2005);
        System.out.println("After: " + car);
    }
}
