package env;

import var_type.*;
import var_type.var_roots.ClassObject;
import var_type.var_roots.permission_control.InstancePermission;

import java.util.HashMap;

public class StaticVars {

    public static HashMap<String, ClassObject> classMapping = new HashMap<>();

    public static ClassObject OBJECT = new ClassObject();
    public static ClassInt INT = new ClassInt();
    public static ClassNoneType NONE_TYPE = new ClassNoneType();
    public static ClassFloat FLOAT = new ClassFloat();
    public static ClassBoolean BOOLEAN = new ClassBoolean();
    public static ClassString STR = new ClassString();
    public static ClassList LIST = new ClassList();
    public static ClassError ERROR = new ClassError();
    public static ClassNamespace NAMESPACE = new ClassNamespace();
    public static InstancePermission DEFAULT_PERMISSION = new InstancePermission(OBJECT);
    public static InstanceBoolean BOOL_FALSE = new InstanceBoolean(DEFAULT_PERMISSION, false);
    public static InstanceBoolean BOOL_TRUE = new InstanceBoolean(DEFAULT_PERMISSION, true);
    public static InstanceNoneType NONE = new InstanceNoneType(DEFAULT_PERMISSION);
    public static InstanceError DEFAULT_ERROR = new InstanceError(DEFAULT_PERMISSION);

    public static void registerClass(ClassObject object){
        classMapping.put(object.getKeyWord(), object);
    }

    public static void setup(){
        registerClass(OBJECT);
        registerClass(INT);
        registerClass(NONE_TYPE);
        registerClass(FLOAT);
        registerClass(BOOLEAN);
        registerClass(STR);
        registerClass(LIST);
        registerClass(ERROR);
        registerClass(NAMESPACE);
    }
}
