package var_type.var_roots.permission_control;

public enum PermissionTypes {
    ABSTRACT,
    FINAL,
    PUBLIC,
    STATIC,
    DEFAULT;

    public String toString() {
        switch (this) {
            case ABSTRACT -> {return "abstract";}
            case FINAL -> {return "final";}
            case PUBLIC -> {return "public";}
            case STATIC -> {return "static";}
            default -> {return "default";}
        }
    }

    public PermissionTypes getType(String string) {
        switch (string) {
            case "abstract" -> {return ABSTRACT;}
            case "final" -> {return FINAL;}
            case "public" -> {return PUBLIC;}
            case "static" -> {return STATIC;}
            default -> {return DEFAULT;}
        }
    }
}
