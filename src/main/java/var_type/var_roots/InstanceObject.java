package var_type.var_roots;

import env.StaticVars;
import var_type.InstanceBoolean;
import var_type.var_interface.IInstance;
import var_type.var_roots.permission_control.InstancePermission;

import java.util.Arrays;
import java.util.List;

public class InstanceObject implements IInstance {
    public final ClassObject myClass;
    public final InstancePermission myPermission;

    public InstanceObject(InstancePermission permission) {
        this.myClass = StaticVars.OBJECT;
        this.myPermission = permission;
    }

    public InstanceObject(ClassObject myClass, InstancePermission myPermission) {
        this.myClass = myClass;
        this.myPermission = myPermission;
    }

    public ClassObject getMyClass() {
        return myClass;
    }

    public InstancePermission getPermission() {
        return myPermission;
    }

    public boolean isAbstract() {
        return myPermission.isAbstract();
    }

    public boolean isFinal() {
        return myPermission.isFinal();
    }

    public boolean isPublic() {
        return myPermission.isPublic();
    }

    public boolean isStatic() {
        return myPermission.isStatic();
    }

    public InstanceBoolean isInstance(ClassObject cls) {
        if(this.myClass.equals(cls)) return StaticVars.BOOL_TRUE;
        return List.of(this.myClass.getAllParents()).contains(cls) ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
    }

    public boolean is(InstanceObject instanceObject) {
        return instanceObject.getMyClass().equals(this.getMyClass());
    }

    @Override
    public boolean is(IInstance instance) {
        return is((InstanceObject) instance);
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        return null;
    }
}
