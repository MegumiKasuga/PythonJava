package var_type;

import env.StaticVars;
import util.CodeLine;
import util.CodeSpace;
import var_type.var_interface.ICodeable;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

public class InstanceNamespace extends InstanceObject implements ICodeable {
    CodeSpace code;
    int pointer;


    public InstanceNamespace(InstancePermission permission) {
        super(StaticVars.NAMESPACE, permission);
    }

    @Override
    public CodeSpace getCode() {
        return code;
    }

    @Override
    public void setCode(CodeSpace code) {
        this.code = code;
    }

    @Override
    public void setPointer(int line) {
        this.pointer = line;
    }

    @Override
    public int getPointer() {
        return pointer;
    }

    @Override
    public CodeLine nextLine() {
        if(hasNextLine()) {
            pointer++;
            return code.getCode(pointer);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNextLine() {
        return code.hasNextLine(pointer);
    }

    @Override
    public void recursiveEncapsulation() {

    }
}
