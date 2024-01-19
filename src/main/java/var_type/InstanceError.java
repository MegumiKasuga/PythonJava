package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceError extends InstanceObject {

    InstanceString error_name, error_message;
    InstanceInt line;
    public InstanceError(InstancePermission permission) {
        super(StaticVars.ERROR, permission);
    }

    public InstanceError(InstancePermission permission, String name, String message, int line) {
        this(permission);
        this.error_name = new InstanceString(permission, name);
        this.error_message = new InstanceString(permission, message);
        this.line = new InstanceInt(permission, line);
    }

    public InstanceError(InstancePermission permission, InstanceString error_name, InstanceString error_message, int line){
        this(permission);
        this.error_name = error_name;
        this.error_message = error_message;
        this.line = new InstanceInt(permission, line);
    }

    public void printStackTrace() {
        System.out.println(this);
    }

    public InstanceInt getLine() {
        return line;
    }

    public InstanceString getMessage() {
        return error_message;
    }

    public InstanceString getName() {
        return error_name;
    }

    @Override
    public String toString() {
        return error_name.data + " at " + line.data + "\n" + error_message.data;
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.STR)) {return new InstanceString(myPermission, toString());}
        return StaticVars.DEFAULT_ERROR;
    }

    @Override
    public InstanceObject runAnyFunction(String name, ClassObject cls, int line, InstanceObject... parameters) {
        switch (name) {
            case "print" -> printStackTrace();
            case "getLine" -> {return getLine();}
            case "getMsg" -> {return getMessage();}
            case "getName" -> {return getName();}
            case "str" -> {return (InstanceObject) convertTo(StaticVars.STR);}
            default -> super.runAnyFunction(name, cls, line, parameters);
        }
        return getDefault(line);
    }

    public static InstanceError getDefault(int line) {
        return new InstanceError(StaticVars.DEFAULT_PERMISSION, "error", "this is an error", line);
    }
}
