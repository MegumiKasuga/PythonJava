package var_type.var_interface;

import var_type.InstanceBoolean;
import var_type.var_roots.ClassObject;

public interface IInstance {
    public boolean is(IInstance instance);
    IInstance convertTo(ClassObject classType);
    public InstanceBoolean isInstance(ClassObject object);
}
