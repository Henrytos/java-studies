package school.sptech.fixture;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import school.sptech.Musica;
import school.sptech.factory.MusicaFactory;

public class MusicaFixture {

    public static List<Musica> getMusicas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return List.of(
              MusicaFactory.build(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", LocalDate.parse("31/10/1975", formatter)),
              MusicaFactory.build(2, "Imagine", "John Lennon", "Imagine", LocalDate.parse("11/10/1971", formatter)),
              MusicaFactory.build(3, "Billie Jean", "Michael Jackson", "Thriller", LocalDate.parse("02/01/1983", formatter)),
              MusicaFactory.build(4, "Smells Like Teen Spirit", "Nirvana", "Nevermind", LocalDate.parse("10/09/1991", formatter)),
              MusicaFactory.build(5, "Hotel California", "Eagles", "Hotel California", LocalDate.parse("08/12/1976", formatter)),
              MusicaFactory.build(6, "Shape of You", "Ed Sheeran", "÷ (Divide)", LocalDate.parse("06/01/2017", formatter)),
              MusicaFactory.build(7, "Hey Jude", "The Beatles", "Single", LocalDate.parse("26/08/1968", formatter)),
              MusicaFactory.build(8, "Rolling in the Deep", "Adele", "21", LocalDate.parse("29/11/2010", formatter)),
              MusicaFactory.build(9, "Like a Rolling Stone", "Bob Dylan", "Highway 61 Revisited", LocalDate.parse("20/07/1965", formatter)),
              MusicaFactory.build(10, "Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", LocalDate.parse("08/11/1971", formatter)),
              MusicaFactory.build(11, "Someone Like You", "Adele", "21", LocalDate.parse("24/01/2011", formatter)),
              MusicaFactory.build(12, "Wonderwall", "Oasis", "(What's the Story) Morning Glory?", LocalDate.parse("02/10/1995", formatter)),
              MusicaFactory.build(13, "Thriller", "Michael Jackson", "Thriller", LocalDate.parse("30/11/1982", formatter)),
              MusicaFactory.build(14, "Purple Rain", "Prince", "Purple Rain", LocalDate.parse("25/06/1984", formatter)),
              MusicaFactory.build(15, "Back in Black", "AC/DC", "Back in Black", LocalDate.parse("25/07/1980", formatter)),
              MusicaFactory.build(16, "Fix You", "Coldplay", "X&Y", LocalDate.parse("05/09/2005", formatter)),
              MusicaFactory.build(17, "Lose Yourself", "Eminem", "8 Mile (Soundtrack)", LocalDate.parse("28/10/2002", formatter)),
              MusicaFactory.build(18, "Hallelujah", "Jeff Buckley", "Grace", LocalDate.parse("23/08/1994", formatter)),
              MusicaFactory.build(19, "Clocks", "Coldplay", "A Rush of Blood to the Head", LocalDate.parse("26/08/2002", formatter)),
              MusicaFactory.build(20, "Yesterday", "The Beatles", "Help!", LocalDate.parse("13/09/1965", formatter))
        );
    }

    public static List<Musica> getMusicasV2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return List.of(
              MusicaFactory.build(21, "Sweet Child O' Mine", "Guns N' Roses", "Appetite for Destruction", LocalDate.parse("21/07/1987", formatter)),
              MusicaFactory.build(22, "Nothing Else Matters", "Metallica", "Metallica (The Black Album)", LocalDate.parse("12/08/1991", formatter)),
              MusicaFactory.build(23, "Wish You Were Here", "Pink Floyd", "Wish You Were Here", LocalDate.parse("12/09/1975", formatter)),
              MusicaFactory.build(24, "Beat It", "Michael Jackson", "Thriller", LocalDate.parse("30/11/1982", formatter)),
              MusicaFactory.build(25, "Smooth Operator", "Sade", "Diamond Life", LocalDate.parse("15/07/1984", formatter)),
              MusicaFactory.build(26, "All Star", "Smash Mouth", "Astro Lounge", LocalDate.parse("04/05/1999", formatter)),
              MusicaFactory.build(27, "Enter Sandman", "Metallica", "Metallica (The Black Album)", LocalDate.parse("29/07/1991", formatter)),
              MusicaFactory.build(28, "Let It Be", "The Beatles", "Let It Be", LocalDate.parse("06/03/1970", formatter)),
              MusicaFactory.build(29, "Karma Police", "Radiohead", "OK Computer", LocalDate.parse("25/08/1997", formatter)),
              MusicaFactory.build(30, "Numb", "Linkin Park", "Meteora", LocalDate.parse("25/03/2003", formatter)),
              MusicaFactory.build(31, "Crazy", "Aerosmith", "Get a Grip", LocalDate.parse("19/04/1993", formatter)),
              MusicaFactory.build(32, "In the End", "Linkin Park", "Hybrid Theory", LocalDate.parse("24/10/2000", formatter)),
              MusicaFactory.build(33, "No Woman, No Cry", "Bob Marley & The Wailers", "Natty Dread", LocalDate.parse("25/10/1974", formatter)),
              MusicaFactory.build(34, "Creep", "Radiohead", "Pablo Honey", LocalDate.parse("21/09/1992", formatter)),
              MusicaFactory.build(35, "Poker Face", "Lady Gaga", "The Fame", LocalDate.parse("26/09/2008", formatter)),
              MusicaFactory.build(36, "Uptown Funk", "Mark Ronson ft. Bruno Mars", "Uptown Special", LocalDate.parse("10/11/2014", formatter)),
              MusicaFactory.build(37, "Shake It Off", "Taylor Swift", "1989", LocalDate.parse("18/08/2014", formatter)),
              MusicaFactory.build(38, "Boulevard of Broken Dreams", "Green Day", "American Idiot", LocalDate.parse("29/11/2004", formatter)),
              MusicaFactory.build(39, "Seven Nation Army", "The White Stripes", "Elephant", LocalDate.parse("07/04/2003", formatter)),
              MusicaFactory.build(40, "Viva La Vida", "Coldplay", "Viva La Vida or Death and All His Friends", LocalDate.parse("12/06/2008", formatter))
        );
    }


}
