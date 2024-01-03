package var_type;

import env.StaticVars;
import var_type.var_interface.ILogicable;
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
}
