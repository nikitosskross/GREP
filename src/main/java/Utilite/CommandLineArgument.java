package Utilite;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommandLineArgument {

    @Option(name="-v",usage="3")
    public Boolean inv;
    @Option(name="-r", usage="1", forbids = "-i")
    public Boolean regex;
    @Option(name="-i",usage="2")
    public Boolean ins;

    @Argument(required = true, usage = "4")
    public String word;
    @Argument(required = true, usage = "5", index = 1)
    public File file;

    public CommandLineArgument(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("Correct input: [-v] [-r] [-i] word filename.txt");
        }
        Grep grep = new Grep(inv, regex, ins, word, file);
        try {
            List<String> result = grep.readFile();
            for (String line : result)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}