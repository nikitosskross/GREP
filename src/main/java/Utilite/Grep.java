package Utilite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private String word;
    private File file;
    private Boolean regex;
    private Boolean inv;
    private Boolean ins;

    public Grep(Boolean inv, Boolean regex, Boolean ins, String word, File file) {
        this.inv = inv;
        this.regex = regex;
        this.ins = ins;
        this.word = word;
        this.file = file;
    }

    private Boolean readStrings(String line) {

        boolean match = false;
        boolean contain = false;
        boolean result = false;

        if (ins) {
            line = line.toUpperCase();
            word = word.toUpperCase();
        }
        if (regex) {
            Pattern p = Pattern.compile(word);
            Matcher m = p.matcher(line);
            match = m.find();
        }
        else
            contain = line.contains(word);

        if (match || contain)
            result = true;
        if (inv) result = !result;
        return result;
    }

    public List<String> readFile() throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                if (readStrings(line))
                    result.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
            return result;
    }
}