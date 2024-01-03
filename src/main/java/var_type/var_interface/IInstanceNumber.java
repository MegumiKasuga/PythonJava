package var_type.var_interface;

import var_type.var_roots.ClassObject;

public interface IInstanceNumber extends IInstance, ILogicable {
    public IInstanceNumber addWith(IInstanceNumber number);
    public IInstanceNumber minusWith(IInstanceNumber number);
    public IInstanceNumber multiplyWith(IInstanceNumber number);
    public IInstanceNumber divideWith(IInstanceNumber number);
    public double getNumber();
    public ClassObject getMyClass();
}
