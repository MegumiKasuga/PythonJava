package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CodeSpace {

    String raw_code = "";
    int line = 0;
    Random randomSource;
    HashMap<String, CodeSpace> executableElementMapping = new HashMap<>();
    ArrayList<CodeLine> codeLines = new ArrayList<>();

    public CodeSpace(String code) {
        raw_code = code;
        randomSource = new Random();
    }

    public void compile(){
        String[] lines = cutLines();
        if(lines.length == 0) return;
        compileFromLines(cutLines());
    }

    public CodeLine getCode(int line) {
        return codeLines.get(line);
    }

    public boolean hasLine(int line) {
        return line > -1 && line < codeLines.size();
    }

    public boolean hasNextLine(int line) {
        return hasLine(line + 1);
    }

    public boolean isEmpty() {
        return codeLines.isEmpty();
    }

    private void compileFromLines(String[] lines){
        if(lines.length == 0) return;

        int basicSpacing = countSpacingBeforeCode(lines[0]);
        codeLines.add(new CodeLine("code", lines[0].substring(basicSpacing)));
        boolean is_sub = false;
        ArrayList<String> sub_lines = new ArrayList<>();
        String sub_name = "";
        HashMap<String, CodeSpace> spaceHashMap = new HashMap<>();
        for(int i = 1; i < lines.length; i++) {
            int spacing = countSpacingBeforeCode(lines[i]);
            if(spacing - basicSpacing >= 4) {
                if(!is_sub) {
                    is_sub = true;
                    sub_name = getRandomName();
                    codeLines.add(new CodeLine("cite", sub_name));
                }
                sub_lines.add(lines[i]);
            } else {
                if(!sub_lines.isEmpty()) {
                    CodeSpace space = new CodeSpace("");
                    space.compileFromLines(sub_lines.toArray(new String[0]));
                    spaceHashMap.put(sub_name, space);
                    sub_lines = new ArrayList<>();
                    is_sub = false;
                    sub_name = "";
                } else {
                    codeLines.add(new CodeLine("code", lines[i].substring(spacing)));
                }
            }
        }

        if(!sub_lines.isEmpty()) {
            CodeSpace space = new CodeSpace("");
            space.compileFromLines(sub_lines.toArray(new String[0]));
            spaceHashMap.put(sub_name, space);
            sub_lines = new ArrayList<>();
            is_sub = false;
            sub_name = "";
        }

        this.executableElementMapping = spaceHashMap;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        int lineCounter = 0;
        for(CodeLine line : codeLines) {
            builder.append(lineCounter).append(": ").append(line.toString()).append("\n");
            lineCounter ++;
        }
        for(String name : executableElementMapping.keySet()) {
            builder.append("\nCodespace: ").append(name).append("\n").append(executableElementMapping.get(name).toString());
        }
        return builder.toString();
    }

    private String getRandomName() {
        String name;
        do {
            name = "CodeSpace" + Math.abs(randomSource.nextInt());
        } while(executableElementMapping.containsKey(name));
        return name;
    }

    private String[] cutLines(){
        boolean in_quote = false;
        ArrayList<String> result = new ArrayList<>();
        int length = raw_code.length();
        int last_cursor = 0;
        for(int i = 0; i < length; i++){
            char c = raw_code.charAt(i);
            if(c == '"' || c == '\'') in_quote = !in_quote;
            if(i < length - 2)
                if(c == '\\' && raw_code.charAt(i + 1) == '\n'){
                    int counter_of_space_nextline = 0;
                    for(int k = i + 1; k < length; k++){
                        if(raw_code.charAt(k) == ' ' || raw_code.charAt(k) == '\t') counter_of_space_nextline ++;
                        else break;
                    }
                    raw_code = raw_code.substring(0, i) + raw_code.substring(i + 2 + counter_of_space_nextline);
                    length = raw_code.length();
                    continue;
                }
            if(in_quote && c == '\n' && (i > 0 && raw_code.charAt(i - 1) != '\\')) {
                raw_code = raw_code.substring(0, i) + "\\n" + raw_code.substring(i + 1);
                length = raw_code.length();
            }
            if(in_quote) continue;
            if(c == '\n') {
                int counter_of_space_line_end = 0;
                for(int k = i - 1; k > -1; k--) {
                    if(raw_code.charAt(k) == ' ' || raw_code.charAt(k) == '\t') counter_of_space_line_end ++;
                    else break;
                }
                result.add(raw_code.substring(last_cursor, i - counter_of_space_line_end));
                last_cursor = i + 1;
            }
        }
        result.add(raw_code.substring(last_cursor, length));
        return result.toArray(new String[0]);
    }

    public int countSpacingBeforeCode(String singleLine) {
        int counter = 0;
        int length = singleLine.length();
        for(int i = 0; i < length; i++) {
            if(singleLine.charAt(i) == ' ') counter++;
            else if (singleLine.charAt(i) == '\t') counter += 4;
            else break;
        }
        return counter;
    }
}
