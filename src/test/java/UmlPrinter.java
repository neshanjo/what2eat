import de.schneider21.what2eat.ServiceFactory;
import de.schneider21.what2eat.framework.HttpServer;
import de.schneider21.what2eat.framework.RestController;
import de.schneider21.what2eat.meal.api.MealController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

public class UmlPrinter {

    public static void main(String[] args) {
//        Class<?>[] classes = {MealService.class, WeatherBitService.class, MensaKlService.class,
//                BasicMeal.class, ExtendedMeal.class};
        Class<?>[] classes = {RestController.IRequestParameters.class, RestController.class, HttpServer.class,
                MealController.class,
                ServiceFactory.class};
        for (Class<?> clazz : classes) {
            printUml(clazz);
        }
    }

    private static void printUml(Class<?> clazz) {
        if (Modifier.isInterface(clazz.getModifiers())) {
            System.out.println("<<Interface>>");
        }
        System.out.println(clazz.getSimpleName());
        System.out.println("----------------------------");
        Arrays.stream(clazz.getDeclaredConstructors())
                .sorted(Comparator.comparing(Constructor::getName))
                .forEach(constructor -> {
                    printModifier(constructor.getModifiers());
                    System.out.print(clazz.getSimpleName());
                    printParameters(constructor.getParameters());
                    System.out.println();
                });

        System.out.println("----------------------------");
        Arrays.stream(clazz.getDeclaredMethods())
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> {
                    printModifier(method.getModifiers());
                    System.out.print(method.getName());
                    printParameters(method.getParameters());
                    System.out.println(" : " + method.getReturnType().getSimpleName());
                });

        System.out.println();
        System.out.println();
    }

    private static void printParameters(Parameter[] parameters) {
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (Parameter type : parameters) {
            String s = type.getName() + ": " + type.getType().getSimpleName();
            joiner.add(s);
        }
        System.out.print(joiner);
    }

    private static void printModifier(int modifiers) {
        if (Modifier.isPublic(modifiers)) {
            System.out.print("+ ");
            return;
        }
        if (Modifier.isPrivate(modifiers)) {
            System.out.print("- ");
            return;
        }
        if (Modifier.isProtected(modifiers)) {
            System.out.print("# ");
            return;
        }
        System.out.print("~ ");
    }
}

