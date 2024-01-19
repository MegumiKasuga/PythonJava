package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceBoolean extends InstanceObject implements ILogicable {

    boolean data;
    public InstanceBoolean(InstancePermission permission) {
        super(StaticVars.BOOLEAN, permission);
    }

    public InstanceBoolean(InstancePermission permission, boolean data) {
        this(permission);
        this.data = data;
    }

    @Override
    public boolean isPositive() {
        return data;
    }

    @Override
    public String toString() {
        return data ? "True" : "False";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ILogicable)) return false;
        return ((ILogicable) obj).isPositive() == isPositive();
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.STR)) return new InstanceString(myPermission, toString());
        if(classType.equals(StaticVars.INT)) return new InstanceInt(myPermission, data ? 1 : 0);
        if(classType.equals(StaticVars.FLOAT)) return new InstanceFloat(myPermission, data ? 1.0 : 0);
        return this;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public InstanceInt len() {
        return new InstanceInt(myPermission, 0);
    }

    @Override
    public InstanceObject runAnyFunction(String name, ClassObject cls, int line, InstanceObject... parameters) {
        switch (name) {
            case "and" -> and((ILogicable) parameters[0]);
            case "or" -> or((ILogicable) parameters[0]);
            case "not" -> not();
            case "str" -> convertTo(StaticVars.STR);
            case "int" -> convertTo(StaticVars.INT);
            case "float" -> convertTo(StaticVars.FLOAT);
            case "bool" -> convertTo(StaticVars.BOOLEAN);
            case "len" -> len();
            default -> super.runAnyFunction(name, cls, line, parameters);
        }
        return InstanceError.getDefault(line);
    }
}
