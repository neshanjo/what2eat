package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExampleData {

    public static final List<BasicMeal> EXAMPLE_MEALS;

    static {
        List<BasicMeal> meals = new ArrayList<>();
        meals.add(new BasicMeal(new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                "Tagesspezial: Schnitzel mit Pommes und Salat (SchniPoSa)",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-03-23",
                "Gebratene Hähnchenkeule  mit Mexikana-Salsa, Steakhouse-Fries und Weißkrautsalat \\\"Coleslaw\\\"",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-03-24", "Maultaschen mit Fleischfüllung, Kartoffel-Käse-Soße  und Salat",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-03-25", "Paniertes Schweineschnitzel  mit Rahmsoße, Spätzle  und Salat",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-03-26", "Rindersaftgulasch  mit hausgemachtem Karotten-Kartoffelstampf und Salat",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-03-27", "Indisches Fischcurry mit Seelachs und Gemüse, Duftreis und Salat",
                new BigDecimal("2.65")));

        meals.add(new BasicMeal("2020-03-30", "Falafel mit \"Ras el Hanout\"-Soße, orientalischem Reis und Salat",
                new BigDecimal("2.65")));
        meals.add(
                new BasicMeal("2020-03-31", "Geschmorte Lammkeule \"Provencial\" mit Burgundersoße und Pariser " +
                        "Kartoffeln",
                        new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-04-01",
                "\"Fajita Pueblo\" mit Paprika, Zucchini und Zwiebeln, dazu Sour-Cream-Dip, Tortillas und Salat",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-04-02", "Hähnchenbrustfilet, Ratatouillegemüse, Thymiankartoffeln und Salat",
                new BigDecimal("2.65")));
        meals.add(new BasicMeal("2020-04-03", "'Dibbelabbes': Saarländischer Kartoffelauflauf mit Lauch, dazu Apfelmus",
                new BigDecimal("2.65")));

        EXAMPLE_MEALS = meals;
    }
}
