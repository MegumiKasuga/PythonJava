package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class ClassInt extends ClassObject {

    public ClassInt() {
        super();
        this.type = this;
    }

    public String getKeyWord() {
        return "int";
    }

    @Override
    public ClassObject[] parent() {
        return new ClassObject[]{StaticVars.OBJECT};
    }

    public IInstance getInstance(InstancePermission permission) {
        return new InstanceInt(permission);
    }
}
