package top.oldwei.demo.dag;

import com.google.common.base.Strings;
import top.oldwei.demo.dag.domain.Parameter;

public class Action {



    public static Object getParameterValue(Parameter parameter) {
        Object ret = parameter.getValue();
        String pType = parameter.getType();
        if (ret != null && !Strings.isNullOrEmpty(ret.toString())) {
            if (pType.equals("[D")) {
                String[] texts = ret.toString().split(";");
                double[] values = new double[texts.length];

                for(int i = 0; i < values.length; ++i) {
                    values[i] = Double.parseDouble(texts[i]);
                }

                ret = values;
            } else if (!pType.equals("double") && !pType.equals(Double.class.getTypeName())) {
                if (!pType.equals("long") && !pType.equals(Long.class.getTypeName())) {
                    if (!pType.equals("int") && !pType.equals(Integer.class.getTypeName())) {
                        if (!pType.equals("float") && !pType.equals(Float.class.getTypeName())) {
                            if (pType.equals("boolean") || pType.equals(Boolean.class.getTypeName())) {
                                ret = Boolean.parseBoolean(ret.toString());
                            }
                        } else {
                            ret = Float.parseFloat(ret.toString());
                        }
                    } else {
                        ret = Integer.parseInt(ret.toString());
                    }
                } else {
                    ret = Long.parseLong(ret.toString());
                }
            } else {
                ret = Double.parseDouble(ret.toString());
            }
        } else {
            ret = null;
        }

        return ret;
    }

}
