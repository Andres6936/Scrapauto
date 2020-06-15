package scapauto;

import com.alibaba.fastjson.JSON;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public final class Playwright
{
    // Methods

    public static <T> void writeFileJSON(final T _object, final String name)
    {
        String formatJSON = JSON.toJSONString(_object);

        try {
            PrintWriter printWriter = new PrintWriter(name + ".json");
            printWriter.println(formatJSON);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
