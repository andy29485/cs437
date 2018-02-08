import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeArray.join;

/**
 * Created by HamzaKhanani on 2/8/18.
 */
public class Options {
    public String quotefile; // str with file path to xml file w/ quotes
    public String searchStr; // search string extracted from arguments
    public CliMain.Action action;    // type of action to perform
    public int    param;     // param to pass to action
    public Quote q;

    public Options() {
        this.quotefile = "quotes/quotes.xml";
        this.searchStr = "";
        this.action    = CliMain.Action.RANDOM;
        this.param     = QuoteList.SearchBothVal;
        this.q = q;
    }

    public static Options parseArgs(String[] args) {
        ArrayList<Integer> ignore = new ArrayList<Integer>();
        Options options = new Options();
        String arg;

        // Parse command line options
        for (int i = 0; i < args.length; ++i) {
            arg = args[i];
            if (arg.startsWith("-f") || arg.startsWith("--file")) {
                ignore.add(i); // ignore this and next arg when joining search terms
                ignore.add(i + 1);
                options.quotefile = args[++i]; // set filepath to next arg
            } else if (arg.startsWith("-i") || arg.startsWith("--index")) {
                ignore.add(i);
                ignore.add(i + 1);
                options.action = CliMain.Action.INDEX;
                options.param = Integer.valueOf(args[++i]); // set index to next arg
            } else if (arg.startsWith("-a") || arg.startsWith("--author")) {
                ignore.add(i);
                options.action = CliMain.Action.SEARCH;
                options.param = QuoteList.SearchAuthorVal;
            } else if (arg.startsWith("-q") || arg.startsWith("--quote")) {
                ignore.add(i);
                options.action = CliMain.Action.SEARCH;
                options.param = QuoteList.SearchTextVal;
            } else if (arg.startsWith("-b") || arg.startsWith("--both")) {
                ignore.add(i);
                options.action = CliMain.Action.SEARCH;
                options.param = QuoteList.SearchBothVal;
            } else if (arg.startsWith("-h") || arg.startsWith("--help")) {
                options.action = CliMain.Action.HELP;
            } else if(arg.startsWith("-s") || arg.startsWith("--set")){
                options.action = CliMain.Action.ADDQUOTE;
                Quote temp = null;
                String text = temp.getQuoteText();
                temp.setQuoteText(text);
                String auth = temp.getAuthor();
                temp.setAuthor(auth);
            }
            else { // unmatched argument, must be a search term
                options.action = CliMain.Action.SEARCH;
            }
        }

        // save unused arguments as the search string
        options.searchStr = join(args, ignore);

        return options;
    }

}
