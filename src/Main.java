import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"
        ));
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("-------");
        //lambda ifadeleri genellikle fonksiyonel arayüzlerde kullanılır
        list.forEach((var myString) -> System.out.println(myString));
        System.out.println("----------");
        String prefix = "nato";//Bu değişkende hiçbir değişiklik veya atamadı yapmadoğım sürüece lambda ifadelerim
        //herhangi birinde kullanabilirim.
        //String myStr="hey";//lambda ifadelerini bildirdiğim bir sonraki satırda hata vveriyor lambda değişkenleriyle
        //aynı ada sahip olamaz
        list.forEach((var myStr) -> {
            char first = myStr.charAt(0);
            System.out.println(prefix + " " + myStr + " means: " + first);
        });
        //System.out.println(myStr);//hata verir çünkü lambda bloğunun dışındadır.
        int result = calculator((var a, var b) -> a * b, 204, 305);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Mary", "Henry"
        );
        // int result4 = calculator((a , b) -> {int c =a * b},20,30);
        // int result5 = calculator((a , b) -> {return a * b;},20,30);bir kod bloğunda return yanlızca işlevsel yöntemin
        // dönüş türü olduğunda kullanın
        // int result3 = calculator((a , b) -> System.out.println(a * b),20,30);
        //burda değer döndürmeyen bir ifadeye sahip
        var coords = Arrays.asList(
                new double[]{47.21321, -95.321312},
                new double[]{321.3212, -32.313},
                new double[]{32.3213, -23.421312}
        );
        coords.forEach((s) -> System.out.println(Arrays.toString(s)));

        BiConsumer<Double, Double> p1 = (lat, lng) ->
                System.out.println("[lat:"+lat+" lon:"+lng+"]");
        var firstPoint = coords.get(0);
        processPoint(firstPoint[0],firstPoint[1],p1);

        System.out.println("------");
        coords.forEach(s -> processPoint(s[0],s[1],p1));
        list.forEach(s -> System.out.println(s));

        System.out.println("-----");
        list.removeIf(s ->s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        System.out.println("------");
        list.addAll(List.of("echo","easy","earnest","bom"));
        list.forEach(s -> System.out.println(s));

        System.out.println("-----");
        list.removeIf(s -> s.startsWith("ea"));//ea ile başlayan kelimeleri siler
        list.forEach(s -> System.out.println(s));

        System.out.println("------");
        list.replaceAll(s -> s.charAt(0) + " -- "+s.toUpperCase());
        list.forEach(s -> System.out.println(s));

        System.out.println("----");
        String [] myString = new String[10];
        Arrays.setAll(myString,i -> "" + (i + 1) + ". "  );
        System.out.println(Arrays.toString(myString));

        System.out.println("----");
        Arrays.setAll(myString,i -> "" + (i + 1) + ". "
        + switch (i){
            case 0 -> "one";
            case 1 -> "two";
            case 2 -> "three";
            default -> "";
        }
        );
        System.out.println(Arrays.toString(myString));

        System.out.println("----");
        String [] names ={"Ann","Bob","Carol","David","Ed","Fred"};
        String [] randomList = randomlySelectedValues(15,names,
                ()-> new Random().nextInt(0,names.length));
        System.out.println(Arrays.toString(randomList));
    }

    //    public static <T> T calculator(Operation<T> function,T value1,T value2){
//        T result = function.operator(value1,value2);
//        System.out.println("Result of operation: "+result);
//        return result;
//    }
    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    public static  <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }

    public static String[] randomlySelectedValues(int count,
                                                  String[] values,
                                                  Supplier<Integer> s){

        String[] selectedValues=new String[count];
        for (int i = 0;i <count;i++){
            selectedValues[i] =values[s.get()];
        }
        return selectedValues;
    }

}

