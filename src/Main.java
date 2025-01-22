import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public  static void main(String[] args){
        String name = "henry";
        System.out.println("my name is: "+name);

        int age = 18;
        System.out.println("my age is: "+ age);

        byte numByte = 127;
        System.out.println("num byte: "+numByte);

        short numPrimo = 7;
        System.out.println("number primo: "+ numPrimo);

        long bigNumber = 1_000_000_000_000L;
        System.out.println("big int: "+bigNumber);

        float height = 1.70f;
        System.out.println("my height is: "+height);

        char favoriteChar = 'x';
        System.out.println("my favorite character is: "+favoriteChar);

        boolean isDeveloper = true;
        System.out.println("is developer? "+ isDeveloper);

        ArrayList<String> myFavoritesAnimation = new ArrayList<String>();
        System.out.println("----------------------------------------list favorite animations----------------------------------------");

        myFavoritesAnimation.add("souso no frieren");
        myFavoritesAnimation.add("blue lock");
        myFavoritesAnimation.add("jujutsu kaisen");

        for (String animation: myFavoritesAnimation ){
            System.out.println(animation);
        }

        String[] myFavoritesSeries = new String[5];

        System.out.println("----------------------------------------list favorite series----------------------------------------");

        myFavoritesSeries[0]= "the big bang theory";
        myFavoritesSeries[1]= "break ...";
        myFavoritesSeries[2]= "sex education";
        myFavoritesSeries[3]= "blocking 99";

        for(String series: myFavoritesSeries){
            System.out.println(series);
        }

    }
}