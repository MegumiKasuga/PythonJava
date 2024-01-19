package var_type.var_interface;

import util.CodeLine;
import util.CodeSpace;

public interface ICodeable {

    CodeSpace getCode();
    void setCode(CodeSpace code);
    void setPointer(int line);
    int getPointer();
    CodeLine nextLine();
    boolean hasNextLine();
    void recursiveEncapsulation();
}
