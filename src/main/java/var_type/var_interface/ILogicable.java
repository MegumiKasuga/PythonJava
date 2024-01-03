package var_type.var_interface;

import env.StaticVars;
import var_type.InstanceBoolean;

public interface ILogicable {
    default InstanceBoolean and(ILogicable iLogicable){
        return iLogicable.isPositive() && this.isPositive() ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
    }
    default InstanceBoolean or(ILogicable iLogicable){
        return iLogicable.isPositive() || this.isPositive() ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
    }
    default InstanceBoolean not(){
        return !this.isPositive() ? StaticVars.BOOL_TRUE : StaticVars.BOOL_FALSE;
    }
    public boolean isPositive();
}
