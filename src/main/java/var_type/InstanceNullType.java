package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceNullType extends InstanceObject implements ILogicable {
    public InstanceNullType(ClassObject myClass, InstancePermission myPermission) {
        super(myClass, myPermission);
    }

    public InstanceNullType(InstancePermission permission) {
        this(StaticVars.NULL_TYPE, permission);
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
        return obj instanceof InstanceNullType;
    }
}
