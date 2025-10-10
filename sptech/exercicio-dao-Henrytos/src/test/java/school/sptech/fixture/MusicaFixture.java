package school.sptech.fixture;

import school.sptech.Musica;

import java.util.Arrays;
import java.util.List;

public class MusicaFixture {

    public static List<Musica> all() {
        return List.of(
                new Musica(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354),
                new Musica(2, "Love of My Life", "Queen", "A Night at the Opera", 222),
                new Musica(3, "Dont Stop Me Now", "Queen", "Jazz", 209),
                new Musica(4, "We Will Rock You", "Queen", "News of the World", 122),
                new Musica(5, "We Are the Champions", "Queen", "News of the World", 179),
                new Musica(6, "Smells Like Teen Spirit", "Nirvana", "Nevermind", 301),
                new Musica(7, "Come as You Are", "Nirvana", "Nevermind", 219),
                new Musica(8, "Lithium", "Nirvana", "Nevermind", 257),
                new Musica(9, "In Bloom", "Nirvana", "Nevermind", 254),
                new Musica(10, "Heart-Shaped Box", "Nirvana", "In Utero", 268),
                new Musica(11, "Billie Jean", "Michael Jackson", "Thriller", 294),
                new Musica(12, "Thriller", "Michael Jackson", "Thriller", 358),
                new Musica(13, "Beat It", "Michael Jackson", "Thriller", 258),
                new Musica(14, "Smooth Criminal", "Michael Jackson", "Bad", 257),
                new Musica(15, "Man in the Mirror", "Michael Jackson", "Bad", 301),
                new Musica(16, "Hey Jude", "The Beatles", "The Beatles 1967–1970", 431),
                new Musica(17, "Let It Be", "The Beatles", "Let It Be", 243),
                new Musica(18, "Yesterday", "The Beatles", "Help!", 125),
                new Musica(19, "Come Together", "The Beatles", "Abbey Road", 259),
                new Musica(20, "Something", "The Beatles", "Abbey Road", 182),
                new Musica(21, "Hotel California", "Eagles", "Hotel California", 391),
                new Musica(22, "New Kid in Town", "Eagles", "Hotel California", 311),
                new Musica(23, "Take It Easy", "Eagles", "Eagles", 212),
                new Musica(24, "Desperado", "Eagles", "Desperado", 224),
                new Musica(25, "Lyin Eyes", "Eagles", "One of These Nights", 246),
                new Musica(26, "Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 482),
                new Musica(27, "Black Dog", "Led Zeppelin", "Led Zeppelin IV", 296),
                new Musica(28, "Kashmir", "Led Zeppelin", "Physical Graffiti", 515),
                new Musica(29, "Whole Lotta Love", "Led Zeppelin", "Led Zeppelin II", 333),
                new Musica(30, "Immigrant Song", "Led Zeppelin", "Led Zeppelin III", 146),
                new Musica(31, "Like a Rolling Stone", "Bob Dylan", "Highway 61 Revisited", 373),
                new Musica(32, "Knockin on Heavens Door", "Bob Dylan", "Pat Garrett & Billy the Kid", 163),
                new Musica(33, "The Times They Are A-Changin", "Bob Dylan", "The Times They Are A-Changin", 178),
                new Musica(34, "Blowin in the Wind", "Bob Dylan", "The Freewheelin Bob Dylan", 166),
                new Musica(35, "Hurricane", "Bob Dylan", "Desire", 512),
                new Musica(36, "Wonderwall", "Oasis", "(Whats the Story) Morning Glory?", 259),
                new Musica(37, "Dont Look Back in Anger", "Oasis", "(Whats the Story) Morning Glory?", 290),
                new Musica(38, "Champagne Supernova", "Oasis", "(Whats the Story) Morning Glory?", 452),
                new Musica(39, "Supersonic", "Oasis", "Definitely Maybe", 280),
                new Musica(40, "Live Forever", "Oasis", "Definitely Maybe", 274),
                new Musica(41, "Under the Bridge", "Red Hot Chili Peppers", "Blood Sugar Sex Magik", 268),
                new Musica(42, "Californication", "Red Hot Chili Peppers", "Californication", 322),
                new Musica(43, "Scar Tissue", "Red Hot Chili Peppers", "Californication", 217),
                new Musica(44, "Otherside", "Red Hot Chili Peppers", "Californication", 255),
                new Musica(45, "Give It Away", "Red Hot Chili Peppers", "Blood Sugar Sex Magik", 283)
        );
    }

    public static List<Musica> ids(int... args) {
        return all().stream()
                .filter(musica -> Arrays.binarySearch(args, musica.getId()) >= 0)
                .toList();
    }
}
