import env.StaticVars;
import util.CodeSpace;
import var_type.InstanceFloat;
import var_type.InstanceInt;
import var_type.InstanceString;
import var_type.var_roots.InstanceObject;

public class test {

    public static void main(String[] args) {
        StaticVars.setup();
        CodeSpace codeSpace = new CodeSpace("1 + 1 \nprint('hello \n world')   \n    2 + 2");
        codeSpace.compile();
        System.out.println(codeSpace);
    }
}
