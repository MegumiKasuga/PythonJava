package var_type;

import env.StaticVars;
import var_type.var_roots.ClassObject;

public class ClassNoneType extends ClassObject {

    @Override
    public String getKeyWord() {
        return "none";
    }

    @Override
    public ClassObject[] getAllParents() {
        return new ClassObject[]{StaticVars.OBJECT};
    }

    public InstanceNoneType getNone() {
        return StaticVars.NONE;
    }
}
