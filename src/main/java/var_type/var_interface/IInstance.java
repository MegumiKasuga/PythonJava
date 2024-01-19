package var_type.var_interface;

import var_type.InstanceBoolean;
import var_type.InstanceInt;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;

import java.util.function.Function;

public interface IInstance {
    public boolean is(IInstance instance);
    IInstance convertTo(ClassObject classType);
    public InstanceBoolean isInstance(ClassObject object);
    InstanceInt len();
    InstanceObject runAnyFunction(String name, ClassObject cls, int line, InstanceObject... parameters);
}
