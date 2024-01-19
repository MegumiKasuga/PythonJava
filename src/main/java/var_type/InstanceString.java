package var_type;

import env.StaticVars;
import var_type.var_interface.IInstance;
import var_type.var_interface.IInstanceNumber;
import var_type.var_interface.IList;
import var_type.var_interface.ILogicable;
import var_type.var_roots.ClassObject;
import var_type.var_roots.InstanceObject;
import var_type.var_roots.permission_control.InstancePermission;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class InstanceString extends InstanceObject implements IList, IInstanceNumber {

    String data;
    public InstanceString(InstancePermission permission) {
        super(StaticVars.STR, permission);
    }

    public InstanceString(InstancePermission permission, String data) {
        this(permission);
        this.data = data;
    }

    @Override
    public IList cut(InstanceInt index1, InstanceInt index2, InstanceInt index3) {
        int first_num = index1.data;
        int last_num = index2.data;
        int step = index3.data;
        if(index1.getNumber() < 0) first_num += data.length();
        if(index2.getNumber() < 0) last_num += data.length();
        String result = "";
        if((first_num < last_num && step > 0) || (first_num > last_num && step < 0)) {
            int counter = Math.min(first_num, last_num);
            while(counter < Math.max(first_num, last_num)) {
                if(counter > data.length() - 1)
                    result += " ";
                else
                    result += data.substring(counter, counter + 1);
                counter += step;
            }
        }
        return new InstanceString(myPermission, result);
    }

    @Override
    public IList replaceAll(InstanceObject object, InstanceObject object2) {
        return new InstanceString(myPermission, data.replaceAll(object.toString(), object2.toString()));
    }

    @Override
    public IList replace(InstanceObject object, InstanceObject object2, InstanceInt index) {
        String regex = object.toString();
        String replace = object2.toString();
        boolean direction = index.data > -1;
        int counter = 0;
        int regex_length = regex.length();
        if(regex_length > data.length()) return this;
        if(regex_length == data.length() && regex.equals(object.toString())) return new InstanceString(myPermission, object2.toString());
        if(regex_length == data.length() && !regex.equals(object.toString())) return this;
        String check_site = String.valueOf(data);

        for(int i = direction ? 0 : check_site.length() - 1; i > -1 && i < check_site.length(); i += direction ? 1 : -1) {
            if((check_site.length() - 1 - i - regex_length < 0 && !direction) || (i + regex_length) > check_site.length() - 1 && direction) break;

            int beginIndex = direction ? i : check_site.length() - 1 - i - regex_length;
            int endIndex = direction ? i + regex_length : check_site.length() - 1 - i;

            String check = check_site.substring(beginIndex, endIndex);
            if(check.equals(regex)){
                if(counter < Math.abs(index.data)) {
                    counter += 1;
                } else {
                    check_site = check_site.substring(0, beginIndex) + replace + check_site.substring(endIndex);
                    break;
                }
            }
        }
        return new InstanceString(myPermission, check_site);
    }

    @Override
    public IList append(InstanceObject... object) {
        StringBuilder result = new StringBuilder(data);
        for(InstanceObject obj : object) {
            result.append(obj.toString());
        }
        return new InstanceString(myPermission, result.toString());
    }

    @Override
    public IList append(IList list) {
        StringBuilder result = new StringBuilder(data);
        for(InstanceObject obj : list.getList()) {
            result.append(obj.toString());
        }
        return new InstanceString(myPermission, result.toString());
    }

    @Override
    public IList remove(InstanceObject... object) {
        ArrayList<String> obj_list = new ArrayList<String>(object.length);
        for(InstanceObject obj : object) {
            obj_list.add(obj.toString());
        }
        ArrayList<String> cache;
        do{
            cache = new ArrayList<>(List.copyOf(obj_list));
            for(int i = 0; i < obj_list.size() - 1;i++) {
                if(obj_list.get(i).length() < obj_list.get(i + 1).length()) {
                    String c = obj_list.get(i);
                    obj_list.set(i, obj_list.get(i + 1));
                    obj_list.set(i + 1, c);
                }
            }
        } while (!cache.equals(obj_list));
        String result = String.valueOf(data);
        for(String s : obj_list) {
            result = result.replaceAll(s, "");
        }
        return new InstanceString(myPermission, result);
    }

    @Override
    public List<InstanceObject> getList() {
        ArrayList<InstanceString> resultList = new ArrayList<>();
        for(int i = 0; i < data.length(); i++) {
            resultList.add(new InstanceString(myPermission, data.substring(i, i+1)));
        }
        return (List) resultList;
    }

    @Override
    public InstanceInt find(InstanceObject object) {
        return new InstanceInt(myPermission, data.indexOf(object.toString()));
    }

    @Override
    public InstanceInt rfind(InstanceObject object) {
        String result = "";
        for(int i = data.length() - 1; i > -1; i--) {
            result += data.substring(i, i+1);
        }
        return new InstanceInt(myPermission, -result.indexOf(object.toString()));
    }

    @Override
    public IInstanceNumber addWith(IInstanceNumber number) {
        if(number instanceof InstanceString str) {
            return append(str);
        }
        if(number instanceof InstanceList list) {
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
        int i = ((InstanceInt)number.convertTo(StaticVars.INT)).data;
        StringBuilder builder = new StringBuilder(data);
        if(i > 1) {
            builder.append(String.valueOf(data).repeat(i - 1));
        }
        return new InstanceString(myPermission, builder.toString());
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
        return !data.equals("");
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public IInstance convertTo(ClassObject classType) {
        if(classType.equals(StaticVars.LIST))
            return new InstanceList(myPermission, getList());
        if(classType.equals(StaticVars.BOOLEAN))
            return new InstanceBoolean(myPermission, isPositive());
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof InstanceString str)
            return str.data.equals(data);
        return false;
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public InstanceObject runAnyFunction(String name, ClassObject cls, int line, InstanceObject... parameters) {
        switch (name) {
            case "print" -> {print(); return null;}
            case "and" -> {return and((ILogicable) parameters[0]);}
            case "or" -> {return or((ILogicable) parameters[0]);}
            case "not" -> {return not();}
            case "cut" -> {return (InstanceObject) cut((InstanceInt) parameters[0], (InstanceInt) parameters[1], (InstanceInt) parameters[2]);}
            case "replaceAll" -> {return (InstanceObject) replaceAll(parameters[0], parameters[1]);}
            case "replace" -> replace(parameters[0], parameters[1], (InstanceInt) parameters[2]);
            case "append" -> {
                if (parameters.length == 1 && parameters[0] instanceof InstanceList)
                    return (InstanceObject) append((IList) parameters[0]);
                else
                    return (InstanceObject) append(parameters);
            }
            case "remove" -> {return (InstanceObject) remove(parameters);}
            case "find" -> {return find(parameters[0]);}
            case "rfind" -> {return rfind(parameters[0]);}
            case "len" -> {return len();}
            case "+" -> {return (InstanceObject) addWith((IInstanceNumber) parameters[0]);}
            case "-" -> {return (InstanceObject) minusWith((IInstanceNumber) parameters[0]);}
            case "*" -> {return (InstanceObject) multiplyWith((IInstanceNumber) parameters[0]);}
            case "/" -> {return (InstanceObject) divideWith((IInstanceNumber) parameters[0]);}
            case "bool" -> {return (InstanceObject) convertTo(StaticVars.BOOLEAN);}
            case "list" -> {return (InstanceObject) convertTo(StaticVars.LIST);}
            case "str" -> {return (InstanceObject) convertTo(StaticVars.STR);}
            default -> {return super.runAnyFunction(name, cls, line, parameters);}
        }
        return InstanceError.getDefault(line);
    }
}
