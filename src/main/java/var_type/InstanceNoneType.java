package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceNoneType extends InstanceObject implements ILogicable {
    public InstanceNoneType(ClassObject myClass, InstancePermission myPermission) {
        super(myClass, myPermission);
    }

    public InstanceNoneType(InstancePermission permission) {
        this(StaticVars.NONE_TYPE, permission);
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.INT)) {
            return StaticVars.INT.getInstance(StaticVars.DEFAULT_PERMISSION);
        }
        if(classType.equals(StaticVars.FLOAT)) {
            return StaticVars.FLOAT.getInstance(StaticVars.DEFAULT_PERMISSION);
        }
        if(classType.equals(StaticVars.BOOLEAN)) {
            return StaticVars.BOOL_FALSE;
        }
        if(classType.equals(StaticVars.STR)) {
            return new InstanceString(myPermission, "None");
        }
        if(classType.equals(StaticVars.LIST)) {
            return new InstanceString(myPermission);
        }
        return null;
    }

    @Override
    public InstanceBoolean and(ILogicable iLogicable) {
        return StaticVars.BOOL_FALSE;
    }

    @Override
    public InstanceBoolean or(ILogicable iLogicable) {
        return iLogicable.isPositive() ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
    }

    @Override
    public InstanceBoolean not() {
        return StaticVars.BOOL_TRUE;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public String toString() {
        return "none";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InstanceNoneType;
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
            case "list" -> convertTo(StaticVars.LIST);
            default -> super.runAnyFunction(name, cls, line, parameters);
        }
        return InstanceError.getDefault(line);
    }
}
