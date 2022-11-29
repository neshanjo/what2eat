package de.schneider21.what2eat.meal.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MensaKlServiceTest {

    private MensaKlService mensaKlService;

    @BeforeEach
    public void setup() {
        mensaKlService = new MensaKlService();
    }

    @Test
    public void parseAndFilterListResponse_emptyArray() throws IOException {
        String input = "[]";

        List<MensaKlService.LunchEntry> lunchEntries = mensaKlService.parseAndFilterListResponse(input);

        assertEquals(0, lunchEntries.size());
    }

    @Test
    public void parseAndFilterListResponse_singletonArrayWantedEntry() throws IOException {
        String input = """
                        [
                            {
                                "m_id": 48541,
                                "date": "2022-11-29",
                                "loc": "1veg",
                                "title": "Pommes frites und Salat",
                                "title_with_additives": "Pommes frites und Salat",
                                "price": "3.50",
                                "rating": 3.4,
                                "rating_amt": 18,
                                "image": "cedfqvairkgwk0p.jpg",
                                "icon": "pork"
                            }
                        ]
                """;

        List<MensaKlService.LunchEntry> lunchEntries = mensaKlService.parseAndFilterListResponse(input);

        assertEquals(1, lunchEntries.size());
        assertEquals("1veg", lunchEntries.get(0).getLoc());
    }

    @Test
    public void parseAndFilterListResponse_singletonArrayUnwantedEntry() throws IOException {
        String input = """
                        [
                            {
                                "m_id": 48581,
                                "date": "2022-11-29",
                                "loc": "Bistro36",
                                "title": "Sp\\u00e4tzle-Sauerkraut-Pfanne mit R\\u00f6stzwiebeln",
                                "title_with_additives": "Sp\\u00e4tzle-Sauerkraut-Pfanne mit R\\u00f6stzwiebeln (1,Gl,So)",
                                "price": "2.75",
                                "rating": 0,
                                "rating_amt": 0,
                                "image": "",
                                "icon": ""
                            }
                        ]
                """;

        List<MensaKlService.LunchEntry> lunchEntries = mensaKlService.parseAndFilterListResponse(input);

        assertEquals(0, lunchEntries.size());
    }

    @Test
    public void parseAndFilterListResponse_withUnwantedLocations() throws IOException {
        String input = """
                        [
                            {
                                "m_id": 48571,
                                "date": "2022-11-29",
                                "loc": "News",
                                "title": "Bei Instagram @studierendenwerk_kl und auf der Studierendenwerk-Website werden schon Bilder des diesj\\u00e4hrigen Weihnachtsmen\\u00fcs [\\ud83c\\udf84 7. Dezember an der TUK] gezeigt.",
                                "title_with_additives": "Bei Instagram @studierendenwerk_kl und auf der Studierendenwerk-Website werden schon Bilder des diesj\\u00e4hrigen Weihnachtsmen\\u00fcs [\\ud83c\\udf84 7. Dezember an der TUK] gezeigt.",
                                "price": "",
                                "rating": 0,
                                "rating_amt": 0,
                                "image": "",
                                "icon": ""
                            },
                            {
                                "m_id": 48541,
                                "date": "2022-11-29",
                                "loc": "1",
                                "title": "Rahmbraten  mit Brasilso\\u00dfe, Pommes frites und Salat",
                                "title_with_additives": "Rahmbraten (S) mit Brasilso\\u00dfe (Gl,La), Pommes frites und Salat",
                                "price": "3.50",
                                "rating": 3.4,
                                "rating_amt": 18,
                                "image": "cedfqvairkgwk0p.jpg",
                                "icon": "pork"
                            },
                            {
                                "m_id": 48542,
                                "date": "2022-11-29",
                                "loc": "2veg",
                                "title": "Vollkornnudeln  mit veganer Champignon-Rahmso\\u00dfe  und Rotkrautsalat",
                                "title_with_additives": "Vollkornnudeln (1,Gl) mit veganer Champignon-Rahmso\\u00dfe (1,Gl,So) und Rotkrautsalat",
                                "price": "2.75",
                                "rating": 3.5,
                                "rating_amt": 4,
                                "image": "nin5erl22f9n59c.jpg",
                                "icon": ""
                            },
                            {
                                "m_id": 48586,
                                "date": "2022-11-29",
                                "loc": "Feelgood",
                                "title": "Reis mit Chili sin Carne und Limetten-Dip",
                                "title_with_additives": "Reis mit Chili sin Carne und Limetten-Dip (La)",
                                "price": "3.95",
                                "rating": 0,
                                "rating_amt": 0,
                                "image": "",
                                "icon": ""
                            },
                            {
                                "m_id": 48581,
                                "date": "2022-11-29",
                                "loc": "Bistro36",
                                "title": "Sp\\u00e4tzle-Sauerkraut-Pfanne mit R\\u00f6stzwiebeln",
                                "title_with_additives": "Sp\\u00e4tzle-Sauerkraut-Pfanne mit R\\u00f6stzwiebeln (1,Gl,So)",
                                "price": "2.75",
                                "rating": 0,
                                "rating_amt": 0,
                                "image": "",
                                "icon": ""
                            }
                        ]
                """;

        List<MensaKlService.LunchEntry> lunchEntries = mensaKlService.parseAndFilterListResponse(input);

        assertEquals(2, lunchEntries.size());
        assertEquals("1", lunchEntries.get(0).getLoc());
        assertEquals("2veg", lunchEntries.get(1).getLoc());
    }
}