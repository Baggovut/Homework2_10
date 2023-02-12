import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Main m1 = new Main();
        /*
            Задание 1
            Напишите реализации функционального интерфейса Predicate, которые проверяют, является ли число положительным.
            Если число положительное, то предикат должен возвращать true, в противном случае — false
         */
        m1.task01Predicate();
        /*
            Задание 2
            Создайте функциональный интерфейс Consumer, который принимает на вход имя человека
             и выводит в консоль приветствие в его адрес.
         */
        m1.task02Consumer();
        /*
            Задание 3
            Реализуйте функциональный интерфейс Function, который принимает на вход вещественное число типа
            Double, а возвращает его округленный вариант типа Long.
         */
        m1.task03Function();
        /*
            Задание 4
            Напишите Supplier, который возвращает случайное число из диапазона от 0 до 100.
         */
        m1.task04Supplier();
        /*
            Задание 5 (опциональное)
         */
        m1.task05();

    }

    public void task01Predicate(){
        List<Integer> integerList1 = new ArrayList<>();

        Predicate<Integer> integerPredicate1 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        };
        Predicate<Integer> integerPredicate2 = integer -> integer > 0;

        for (int i = -10;i <= 10;i++){
            integerList1.add(i);
        }

        System.out.println("\033[4mЗадание 1\033[0m");
        System.out.println("Через анонимный класс:");
        for (int int1 : integerList1){
            if(integerPredicate1.test(int1)) {
                System.out.print(int1+" ");
            }
        }

        System.out.println("\nЧерез лямбду:");
        for (int int1 : integerList1){
            if(integerPredicate2.test(int1)) {
                System.out.print(int1+" ");
            }
        }
        System.out.println();
    }
    public void task02Consumer(){
        List<String> names1 = new ArrayList<>();
        names1.add("Иванов");
        names1.add("Петров");
        names1.add("Сидоров");
        names1.add("Смирнов");
        names1.add("Кузнецов");

        Consumer<String> stringConsumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Приветствую, "+s+"!");
            }
        };
        Consumer<String> stringConsumer2 = string -> System.out.println("Приветствую, "+string+"!");

        System.out.println("\n\033[4mЗадание 2\033[0m");
        System.out.println("Через анонимный класс:");
        for (String st1 : names1){
            stringConsumer1.accept(st1);
        }
        System.out.println("\nЧерез лямбду:");
        for (String st1 : names1){
            stringConsumer2.accept(st1);
        }
    }
    public void task03Function(){
        List<Double> doubleList1 = new ArrayList<>();

        Random random1 = new Random();
        for (int i = 0; i < 10; i++){
            doubleList1.add(random1.nextDouble()*100);
        }

        Function<Double,Long> doubleLongFunction1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return aDouble.longValue();
            }
        };

        Function<Double,Long> doubleLongFunction2 = aDouble -> aDouble.longValue();

        System.out.println("\n\033[4mЗадание 3\033[0m");
        System.out.println("Через анонимный класс:");
        for (double d : doubleList1){
            System.out.println("Double: "+d+" Long: "+doubleLongFunction1.apply(d));
        }
        System.out.println("\nЧерез лямбду:");
        for (double d : doubleList1){
            System.out.println("Double: "+d+" Long: "+doubleLongFunction2.apply(d));
        }
    }
    public void task04Supplier(){
        List<Integer> integerList1 = new ArrayList<>();
        List<Integer> integerList2 = new ArrayList<>();

        Supplier<Integer> integerSupplier1 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                Random random1 = new Random();
                return random1.nextInt(100);
            }
        };

        Supplier<Integer> integerSupplier2 = () -> {
            Random random1 = new Random();
            return random1.nextInt(100);
        };

        System.out.println("\n\033[4mЗадание 4\033[0m");
        System.out.println("Через анонимный класс:");
        for (int i = 0; i < 10; i++){
            integerList1.add(integerSupplier1.get());
        }
        System.out.println(integerList1);
        System.out.println("\nЧерез лямбду:");
        for (int i = 0; i < 10; i++){
            integerList2.add(integerSupplier2.get());
        }
        System.out.println(integerList2);
    }
    public void task05(){
        System.out.println("\n\033[4mЗадание 5 (опциональное)\033[0m");
        Random random = new Random();
        Double randomDouble = random.nextDouble()*100;

        Predicate<Double> doublePredicate1 = aDouble -> aDouble >= 100;
        Function<Double,Float> doubleFloatFunction1 = aDouble -> aDouble.floatValue()/100;
        Function<Double,Float> doubleFloatFunction2 = aDouble -> aDouble.floatValue()*100;

        Function<Double,Float> task05 = ternaryOperator(doublePredicate1,doubleFloatFunction1,doubleFloatFunction2);

        System.out.println(randomDouble);
        System.out.println(task05.apply(randomDouble));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse){
        Random random = new Random();
        Double randomDouble = random.nextDouble()*1000;

        T test = (T) randomDouble;

        if(condition.test(test)){
            return (Function<T, U>) ifTrue;
        }else {
            return (Function<T, U>) ifFalse;
        }
    }
}