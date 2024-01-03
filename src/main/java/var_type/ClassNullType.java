package var_type;

import env.StaticVars;
import var_type.var_roots.ClassObject;

public class ClassNullType extends ClassObject {
    @Override
    public String getKeyWord() {
        return "null";
    }

    @Override
    public ClassObject[] getAllParents() {
        return new ClassObject[]{StaticVars.OBJECT};
    }
}
