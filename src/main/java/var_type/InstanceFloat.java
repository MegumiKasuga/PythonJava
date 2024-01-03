package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.IInstanceNumber;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;
import var_type.var_roots.permission_control.PermissionTypes;

public class InstanceFloat extends InstanceObject implements IInstanceNumber {
    double data = 0;

    public InstanceFloat(InstancePermission permission){
        super(StaticVars.FLOAT, permission);
    }

    public InstanceFloat(InstancePermission permission, double data){
        super(StaticVars.FLOAT, permission);
        this.data = data;
    }

    InstanceFloat(ClassObject myClass, InstancePermission permission) {
        super(myClass, permission);
    }

    InstanceFloat(ClassObject myClass) {
        super(myClass, InstancePermission.getDefault(myClass));
    }

    InstanceFloat(ClassObject myClass, double data) {
        this(myClass);
        this.data = data;
    }

    public InstanceFloat(ClassObject myClass, double data, InstancePermission permission) {
        this(myClass, permission);
        this.data = data;
    }

    @Override
    public IInstanceNumber addWith(IInstanceNumber number) {
        if(number.is(this))
            return new InstanceFloat(this.myClass, this.data + number.getNumber(), this.myPermission);
        return number.addWith(this);
    }

    @Override
    public IInstanceNumber minusWith(IInstanceNumber number) {
        if(number.is(this))
            return new InstanceFloat(this.myClass, this.data - number.getNumber(), this.myPermission);
        return number.minusWith(this);
    }

    @Override
    public IInstanceNumber multiplyWith(IInstanceNumber number) {
        if(number.is(this))
            return new InstanceFloat(this.myClass, this.data * number.getNumber(), this.myPermission);
        return number.multiplyWith(this);
    }

    @Override
    public IInstanceNumber divideWith(IInstanceNumber number) {
        if(number.is(this))
            return new InstanceFloat(this.myClass, this.data / number.getNumber(), this.myPermission);
        return number.divideWith(this);
    }

    @Override
    public double getNumber() {
        return data;
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.OBJECT) || classType.equals(StaticVars.FLOAT)) return this;
        if(classType.equals(StaticVars.INT)) {
            InstanceInt intObj = (InstanceInt) StaticVars.INT.getInstance(myPermission);
            intObj.data = (int) data;
            return intObj;
        }
        if(classType.equals(StaticVars.BOOLEAN)) {
            return isPositive() ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
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
        if(!(obj instanceof InstanceFloat)) return false;
        return ((InstanceFloat) obj).data == data;
    }
}
