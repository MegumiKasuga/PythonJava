package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.IInstanceNumber;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceInt extends InstanceObject implements IInstanceNumber {

    int data;

    public InstanceInt(InstancePermission myPermission) {
        super(StaticVars.INT, myPermission);
    }

    public InstanceInt(InstancePermission permission, int data) {
        this(permission);
        this.data = data;
    }

    public InstanceInt(ClassObject myClass, InstancePermission myPermission) {
        super(myClass, myPermission);
    }

    public InstanceInt(ClassObject myClass, InstancePermission myPermission, int data) {
        this(myClass, myPermission);
        this.data = data;
    }

    public InstanceInt(ClassObject myClass, InstancePermission myPermission, float data) {
        this(myClass, myPermission, (int) data);
    }

    @Override
    public IInstanceNumber addWith(IInstanceNumber number) {
        return new InstanceInt(myClass, myPermission, data + (int) number.getNumber());
    }

    @Override
    public IInstanceNumber minusWith(IInstanceNumber number) {
        return new InstanceInt(myClass, myPermission, data - (int) number.getNumber());
    }

    @Override
    public IInstanceNumber multiplyWith(IInstanceNumber number) {
        return new InstanceInt(myClass, myPermission, data * (int) number.getNumber());
    }

    @Override
    public IInstanceNumber divideWith(IInstanceNumber number) {
        return new InstanceInt(myClass, myPermission, data / (int) number.getNumber());
    }

    @Override
    public double getNumber() {
        return data;
    }

    public IInstance convertTo(ClassObject type) {
        if(type.equals(StaticVars.OBJECT) || type.equals(StaticVars.INT)) return this;
        if(type.equals(StaticVars.FLOAT)){
            InstanceFloat floatObj = (InstanceFloat) StaticVars.FLOAT.getInstance(myPermission);
            floatObj.data = data;
            return floatObj;
        }
        if(type.equals(StaticVars.BOOLEAN)) {
            InstanceBoolean boolObj = (InstanceBoolean) StaticVars.BOOLEAN.getInstance(myPermission);
            boolObj.data = isPositive();
            return boolObj;
        }
        return null;
    }

    @Override
    public boolean isPositive() {
        return data > 0;
    }

    @Override
    public String toString() {
        return data + "";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof InstanceInt)) return false;
        return ((InstanceInt) obj).data == data;
    }
}
