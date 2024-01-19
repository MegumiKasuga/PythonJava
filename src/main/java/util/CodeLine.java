package util;

public class CodeLine {

    private String type, code;

    public CodeLine(String type, String code){
        this.type = type;
        this.code = code;
    }

    public String getType(){
        return type;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return type + " -> " + code;
    }
}
