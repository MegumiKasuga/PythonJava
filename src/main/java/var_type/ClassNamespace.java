package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_roots.ClassObject;
import var_type.var_roots.permission_control.InstancePermission;

public class ClassNamespace extends ClassObject {
    @Override
    public ClassObject[] parent() {
        return new ClassObject[]{StaticVars.OBJECT};
    }

    @Override
    public String getKeyWord() {
        return "namespace";
    }

    @Override
    public IInstance getInstance(InstancePermission permission) {
        return new InstanceNamespace(permission);
    }

}
