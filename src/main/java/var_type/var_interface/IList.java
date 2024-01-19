package var_type.var_interface;

import var_type.InstanceInt;
import var_type.InstanceList;
import var_type.var_roots.InstanceObject;

import java.util.List;

public interface IList extends IInstanceNumber{
    IList cut(InstanceInt index1, InstanceInt index2, InstanceInt index3);
    IList replaceAll(InstanceObject object, InstanceObject object2);
    IList replace(InstanceObject object, InstanceObject object2, InstanceInt index);
    IList append(InstanceObject... object);
    IList append(IList list);
    IList remove(InstanceObject... object);
    List<InstanceObject> getList();
    InstanceInt find(InstanceObject object);
    InstanceInt rfind(InstanceObject object);
}
