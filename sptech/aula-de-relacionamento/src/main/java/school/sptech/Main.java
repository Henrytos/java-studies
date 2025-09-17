package school.sptech;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("manoel", "11 9999999", "manoel@gmail.com" );

        Musica musica1 = new Musica("Pontes INdestrutiveis", "Charlie", 300);
        Musica musica2 = new Musica("black in black", "ACDC", 450);
        Musica musica3 = new Musica("Time", "ACDPink Floyd", 500);

        Playlist playlist = new Playlist("chola", usuario1);
        playlist.adicionarMusica(musica1);

        System.out.println(musica3);
        System.out.println("playlist 1" + playlist.getMusicas());
        System.out.println(playlist);
    }

}
