package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.IInstanceNumber;
import var_type.var_interface.IList;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

import java.util.ArrayList;
import java.util.List;

public class InstanceList extends InstanceObject implements IList, IInstanceNumber {

    ArrayList<InstanceObject> memberList = new ArrayList<>();

    public InstanceList(InstancePermission permission) {
        super(StaticVars.LIST, permission);
    }

    public InstanceList(InstancePermission permission, InstanceObject... members) {
        this(permission, List.of(members));
    }

    public InstanceList(InstancePermission permission, List<InstanceObject> members) {
        super(permission);
        memberList.addAll(members);
    }

    @Override
    public IList cut(InstanceInt index1, InstanceInt index2, InstanceInt index3) {
        int first_num = index1.data;
        int last_num = index2.data;
        int step = index3.data;
        if(index1.getNumber() < 0) first_num += this.memberList.size();
        if(index2.getNumber() < 0) last_num += this.memberList.size();
        ArrayList<InstanceObject> result = new ArrayList<>();
        if((first_num < last_num && step > 0) || (first_num > last_num && step < 0)) {
            int counter = Math.min(first_num, last_num);
            while(counter < Math.max(first_num, last_num)) {
                if(counter > memberList.size() - 1)
                    result.add(StaticVars.NONE);
                else
                    result.add(memberList.get(counter));
                counter += step;
            }
        }
        return new InstanceList(myPermission, result);
    }

    @Override
    public IList replaceAll(InstanceObject object, InstanceObject object2) {
        List<InstanceObject> result = new ArrayList<>(List.copyOf(memberList));
        result.replaceAll(obj -> (obj.equals(object)) ? object2 : obj);
        return new InstanceList(myPermission, result);
    }

    @Override
    public IList replace(InstanceObject object, InstanceObject object2, InstanceInt index) {
        ArrayList<InstanceObject> result = new ArrayList<>(List.copyOf(memberList));
        int counter = 0;
        boolean direction = index.data < 0;
        for(int i = direction ? result.size() - 1 : 0; i < result.size() && i > -1; i += direction ? -1 : 1) {
            if(result.get(i).equals(object)) {
                if(counter < index.data)
                    counter++;
                else {
                    result.set(i, object2);
                    break;
                }
            }
        }
        return new InstanceList(myPermission, result);
    }

    public IList append(IList list) {
        return append(list.getList().toArray(new InstanceObject[0]));
    }

    @Override
    public IList append(InstanceObject... object) {
        ArrayList<InstanceObject> result = new ArrayList<>(List.copyOf(memberList));
        result.addAll(List.of(object));
        return new InstanceList(myPermission, object);
    }

    @Override
    public IList remove(InstanceObject... object) {
        ArrayList<InstanceObject> result = new ArrayList<>(List.copyOf(memberList));
        int length = memberList.size();
        for(int i = 0; i < length; i++) {
            for(InstanceObject obj : object) {
                if (obj instanceof InstanceInt) {
                    if (result.contains(obj)) {
                        result.remove(obj);
                        length --;
                    } else {
                        if(((InstanceInt) obj).data > -1 && ((InstanceInt) obj).data < length){
                            result.remove(((InstanceInt) obj).data);
                            length --;
                        }
                    }
                } else {
                    if (result.contains(obj)){
                        result.remove(obj);
                        length --;
                    }
                }
            }
        }
        return new InstanceList(myPermission, result);
    }

    @Override
    public List<InstanceObject> getList() {
        return memberList;
    }

    @Override
    public InstanceInt find(InstanceObject object) {
        return new InstanceInt(myPermission, memberList.indexOf(object));
    }

    @Override
    public InstanceInt rfind(InstanceObject object) {
        int counter = 0;
        for(int i = memberList.size() - 1; i > -1; i--) {
            counter--;
            if(memberList.get(i).equals(object)){
                break;
            }
        }
        return new InstanceInt(myPermission, counter);
    }

    @Override
    public Object getData() {
        return memberList.size() * memberList.hashCode();
    }

    @Override
    public InstanceInt len() {
        return new InstanceInt(myPermission, memberList.size());
    }

    @Override
    public String toString() {
        return memberList.toString();
    }

    @Override
    public IInstanceNumber addWith(IInstanceNumber number) {
        if(number instanceof IList list) {
            return append(list);
        }
        return this;
    }

    @Override
    public IInstanceNumber minusWith(IInstanceNumber number) {
        return this;
    }

    @Override
    public IInstanceNumber multiplyWith(IInstanceNumber number) {
        if(number instanceof InstanceInt && number.getNumber() > 1) {
            ArrayList<InstanceObject> result = new ArrayList<InstanceObject>(memberList.size() * (int)number.getNumber());
            for(int i = 0; i < number.getNumber(); i++){
                for(InstanceObject obj : memberList){
                    result.add(obj);
                }
            }
            return new InstanceList(myPermission, result);
        }
        return this;
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.BOOLEAN)) return new InstanceBoolean(myPermission, isPositive());
        if(classType.equals(StaticVars.STR)) return new InstanceString(myPermission, toString());
        if(classType.equals(StaticVars.LIST)) return this;
        return super.convertTo(classType);
    }

    @Override
    public IInstanceNumber divideWith(IInstanceNumber number) {
        return this;
    }

    @Override
    public double getNumber() {
        return 0;
    }

    @Override
    public boolean isPositive() {
        return !memberList.isEmpty();
    }

    @Override
    public InstanceObject runAnyFunction(String name, ClassObject cls, int line, InstanceObject... parameters) {
        switch (name) {
            case "and" -> and((ILogicable) parameters[0]);
            case "or" -> or((ILogicable) parameters[0]);
            case "not" -> not();
            case "cut" -> cut((InstanceInt) parameters[0], (InstanceInt) parameters[1], (InstanceInt) parameters[2]);
            case "replaceAll" -> replaceAll(parameters[0], parameters[1]);
            case "replace" -> replace(parameters[0], parameters[1], (InstanceInt) parameters[2]);
            case "append" -> {
                if (parameters.length == 1 && parameters[0] instanceof InstanceList)
                    return (InstanceObject) append((IList) parameters[0]);
                else
                    return (InstanceObject) append(parameters);
            }
            case "remove" -> remove(parameters);
            case "find" -> find(parameters[0]);
            case "rfind" -> rfind(parameters[0]);
            case "len" -> len();
            case "+" -> addWith((IInstanceNumber) parameters[0]);
            case "-" -> minusWith((IInstanceNumber) parameters[0]);
            case "*" -> multiplyWith((IInstanceNumber) parameters[0]);
            case "/" -> divideWith((IInstanceNumber) parameters[0]);
            case "bool" -> convertTo(StaticVars.BOOLEAN);
            case "str" -> convertTo(StaticVars.STR);
            case "list" -> convertTo(StaticVars.LIST);
            default -> super.runAnyFunction(name, cls, line, parameters);
        }
        return InstanceError.getDefault(line);
    }
}
