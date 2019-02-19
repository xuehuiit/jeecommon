package com.xuehuiit.jee.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 
 * @author wingfeng
 *
 */
public class WeatherUtil {

   
	/**
	 * Set the properties of a java bean is null.
	 * @param cleanedForm
	 */
    public static void cleanForm(Object cleanedForm) {
        Object[] fields = ArrayUtils.compositeArrays(new Object[] {
                cleanedForm.getClass().getFields(),
                cleanedForm.getClass().getDeclaredFields() });

        for (int i = 0; i < fields.length; i++) {
            Field field = (Field) fields[i];
            if (!field.getName().equals("errorMsg")) {
                try {
                    Method m = cleanedForm.getClass().getMethod(
                            getSetterName(field.getName()),
                            getTypes(field.getType().getName()));
                    if (!"[Ljava.lang.String;"
                            .equals(field.getType().getName()))
                        m.invoke(cleanedForm,
                                getArgs(field.getType().getName()));

                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 
     * @param type
     * @return
     * @throws Exception
     */
    private static Object[] getArgs(String type) throws Exception {
        if (type.equals("java.lang.String")) {
            return new Object[] { new String("") };
        } else if (type.equals("int")) {
            return new Object[] { new Integer(0) };
        } else if (type.equals("boolean")) {
            return new Object[] { new Boolean(false) };
        } else if (type.equals("long")) {
            return new Object[] { new Long(0) };
        } else if (type.equals("float")) {
            return new Object[] { new Float(0) };
        } else if (type.equals("double")) {
            return new Object[] { new Double(0) };
        } else {
            return new Object[] { Class.forName(type).newInstance() };
        }
    }

    
    /**
     * 
     * @param propName
     * @return
     */
    private static String getSetterName(String propName) {
        return "set" + propName.substring(0, 1).toUpperCase()
                + propName.substring(1, propName.length());
    }

    
    /**
     * 
     * @param type
     * @return
     * @throws ClassNotFoundException
     */
    private static Class[] getTypes(String type) throws ClassNotFoundException {
        if (type.equals("java.lang.String")) {
            return new Class[] { String.class };
        } else if (type.equals("int")) {
            return new Class[] { Integer.TYPE };
        } else if (type.equals("long")) {
            return new Class[] { Long.TYPE };
        } else if (type.equals("boolean")) {
            return new Class[] { Boolean.TYPE };
        } else if (type.equals("float")) {
            return new Class[] { Float.TYPE };
        } else if (type.equals("double")) {
            return new Class[] { Double.TYPE };
        } else {
            return new Class[] { Class.forName(type) };
        }
    }

    
}