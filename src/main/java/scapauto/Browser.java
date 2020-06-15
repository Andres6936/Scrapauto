package scapauto;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public final class Browser extends WebClient
{
    // Final Fields

    /**
     * Site web of where we extract information
     */
    private static final String URL_OBJECTIVE = "https://www.autoevolution.com/";

    // Construct

    public Browser()
    {
        super();

        getOptions().setCssEnabled(false);
        getOptions().setJavaScriptEnabled(false);
    }

    // Methods

    public HtmlPage getPageWithGenerationsOfURL(final String partialUrl) throws IOException
    {
        return getPage(URL_OBJECTIVE + partialUrl);
    }
}
