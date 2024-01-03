package env;

import var_type.*;
import var_type.var_roots.ClassObject;
import var_type.var_roots.permission_control.InstancePermission;

public class StaticVars {
    public static ClassObject OBJECT = new ClassObject();
    public static ClassInt INT = new ClassInt();
    public static ClassNullType NULL_TYPE = new ClassNullType();
    public static ClassFloat FLOAT = new ClassFloat();
    public static ClassBoolean BOOLEAN = new ClassBoolean();
    public static InstancePermission DEFAULT_PERMISSION = new InstancePermission(OBJECT);
    public static InstanceBoolean BOOL_FALSE = new InstanceBoolean(DEFAULT_PERMISSION, false);
    public static InstanceBoolean BOOL_TRUE = new InstanceBoolean(DEFAULT_PERMISSION, true);
    public static InstanceNullType NULL = new InstanceNullType(DEFAULT_PERMISSION);

}
