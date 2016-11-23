import java.io.File;

import sheetsoft.com.mkmnavigator.android.backend.ProgramInput;

/**
 * Created by chapa on 11/23/2016.
 */

public class Main {
    public static void main(String... args){

        String s = ProgramInput.getHTMLFromFile(new File("app/src/main/res/raw/mkmcityofbrassproduct"));

        System.out.println(s);

    }

}
