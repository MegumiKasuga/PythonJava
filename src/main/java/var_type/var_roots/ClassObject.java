package var_type.var_roots;

import env.StaticVars;
import var_type.var_interface.IClass;
import var_type.var_interface.IInstance;
import var_type.var_roots.permission_control.InstancePermission;

import java.util.ArrayList;

public class ClassObject implements IClass {
    public ClassObject type;

    public ClassObject() {
        this.type = this;
    }

    public String getKeyWord() {
        return "object";
    }

    public boolean equals(Object o) {
        return o instanceof ClassObject && ((ClassObject) o).type == this.type;
    }

    public ClassObject[] parent() {return null;}

    public boolean hasParent() {return parent() == null;}

    public ClassObject[] child() {return null;}

    @Override
    public IInstance getInstance(InstancePermission permission) {
        return new InstanceObject(permission);
    }

    public boolean hasChild() {return child() == null;}

    public ClassObject[] getAllParents() {
        if(parent() == null) return null;
        ClassObject[] result = parent();
        ArrayList<ClassObject[]> cache = new ArrayList<>();
        for(ClassObject r : result) {
            if(!r.hasParent()) continue;
            cache.add(r.parent());
        }
        for(ClassObject[] o : cache) {
            result = combineObjectList(result, o);
        }
        return result;
    }

    public ClassObject[] getAllChildren() {
        if(child() == null) return null;
        ClassObject[] result = child();
        ArrayList<ClassObject[]> cache = new ArrayList<>();
        for(ClassObject r : result) {
            if(!r.hasChild()) continue;
            cache.add(r.child());
        }
        for(ClassObject[] o : cache) {
            result = combineObjectList(result, o);
        }
        return result;
    }

    ClassObject[] combineObjectList(ClassObject[] li1, ClassObject[] li2) {
        if(li1 == null) return li2;
        if(li2 == null) return li1;
        ClassObject[] resultList = new ClassObject[li1.length + li2.length];
        int counter = 0;
        for(ClassObject obj : li1) {
            resultList[counter] = obj;
            counter++;
        }
        for(ClassObject obj : li2) {
            resultList[counter] = obj;
            counter++;
        }
        return resultList;
    }

    @Override
    public String toString() {
        return "class_" + getKeyWord();
    }
}
