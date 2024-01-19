package var_type;

import env.StaticVars;
import var_type.var_interface.IClass;
import var_type.var_interface.IInstance;
import var_type.var_roots.ClassObject;
import var_type.var_roots.permission_control.InstancePermission;

import java.util.ArrayList;
import java.util.List;

public class ClassList extends ClassObject {

    @Override
    public ClassObject[] parent() {
        return new ClassObject[]{StaticVars.OBJECT};
    }

    @Override
    public String getKeyWord() {
        return "list";
    }

    @Override
    public IInstance getInstance(InstancePermission permission) {
        return new InstanceList(permission);
    }
}
