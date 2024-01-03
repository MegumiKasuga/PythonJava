package var_type.var_interface;

import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public interface IClass {

    public String getKeyWord();

    public ClassObject[] parent();

    public ClassObject[] child();

    public IInstance getInstance(InstancePermission permission);
}
