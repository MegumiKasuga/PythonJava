package var_type.var_roots.permission_control;

import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;

import java.util.ArrayList;
import java.util.List;

public class InstancePermission extends InstanceObject {

    private boolean[] permission = new boolean[]{false, false, true, true};
    // abstract, final, public, static
    public InstancePermission(ClassObject myClass, PermissionTypes... types) {
        super(myClass, null);
        List<PermissionTypes> l = List.of(types);
        permission[0] = l.contains(PermissionTypes.ABSTRACT);
        permission[1] = l.contains(PermissionTypes.FINAL);
        permission[2] = l.contains(PermissionTypes.PUBLIC);
        permission[3] = l.contains(PermissionTypes.STATIC);
    }

    public InstancePermission(ClassObject myClass){
        super(myClass, getDefault(myClass));
    }

    public boolean isAbstract() {
        return permission[0];
    }

    public boolean isFinal() {
        return permission[1];
    }

    public boolean isPublic() {
        return permission[2];
    }

    public boolean isStatic() {
        return permission[3];
    }

    public static InstancePermission getDefault(ClassObject myClass) {
        return new InstancePermission(myClass, PermissionTypes.PUBLIC, PermissionTypes.STATIC);
    }

    public PermissionTypes[] getPermissions() {
        ArrayList<PermissionTypes> result = new ArrayList<>();
        if(permission[0]) result.add(PermissionTypes.ABSTRACT);
        if(permission[1]) result.add(PermissionTypes.FINAL);
        if(permission[2]) result.add(PermissionTypes.PUBLIC);
        if(permission[3]) result.add(PermissionTypes.STATIC);
        return result.toArray(new PermissionTypes[0]);
    }
}
