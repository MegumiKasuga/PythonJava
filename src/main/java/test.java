import env.StaticVars;
import var_type.InstanceFloat;
import var_type.InstanceInt;

public class test {

    public static void main(String[] args) {
        InstanceFloat num1 = new InstanceFloat(StaticVars.DEFAULT_PERMISSION, 2);
        System.out.println(num1.and(StaticVars.BOOL_TRUE).or(StaticVars.NULL));
    }
}
