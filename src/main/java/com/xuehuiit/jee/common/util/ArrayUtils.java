package com.xuehuiit.jee.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 *  
 *  @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class ArrayUtils {
    
	/**
     * 动态的增加对象数组的长度，并加入一个新的对象到数组中
     * @param objects 原对象数组
     * @param toAdd   加入到对象
     * @return
     */
    public static Object[] appendObject2Array(Object[] objects,Object toAdd){
        if (null == objects) {
            //as need at least one object in the array to determine the object class
           throw new java.lang.IllegalArgumentException("the array objects should not be null,otherwise the object's class cann't be determined!");
        }
        int length = objects.length;

        Object[] newObjs = (Object[]) java.lang.reflect.Array.newInstance(objects.getClass().getComponentType(),
                length + 1);
        System.arraycopy(objects, 0, newObjs, 0, length);
        newObjs[length] = toAdd;
        return newObjs; 
    }


    /**
     * connect two array by concatenate objects in the second array to the first array
     * @param first
     * @param second
     * @return
     */
    public static Object[] catArray(Object[] first,Object[] second){
        if (null == first||null==second) {
            //as need at least one object in the array to determine the object class
           throw new java.lang.IllegalArgumentException("the two arrays should not be null,otherwise the object's class cann't be determined!");
        }
        if(!first.getClass().getComponentType().equals(second.getClass().getComponentType())){
            throw new java.lang.IllegalArgumentException("objects in the two arrays should be of the same class:\n" +
                    "first is of class "+first.getClass().getComponentType().getName()+
                    "second is of class "+second.getClass().getComponentType().getName());
        }
        int length1 =first.length;
        int length2=second.length;

        Object[] newObjs = (Object[]) java.lang.reflect.Array.newInstance(first.getClass().getComponentType(),
                length1+length2);
        System.arraycopy(first, 0,newObjs, 0, length1);
        System.arraycopy(second, 0,newObjs, length1,length2);
        return newObjs;
    }
    
    /**
     * add elements in two arrays to a list,both first and second can be null
     * @param first
     * @param second
     * @return
     */
    public static List catArray2List(Object[] first,Object[] second){
        /*if (null == first||null==second) {
            //as need at least one object in the array to determine the object class
           throw new java.lang.IllegalArgumentException("the two arrays should not be null,otherwise the object's class cann't be determined!");
        }
        if(!first.getClass().getComponentType().equals(second.getClass().getComponentType())){
            throw new java.lang.IllegalArgumentException("objects in the two arrays should be of the same class:\n" +
                    "first is of class "+first.getClass().getComponentType().getName()+
                    "second is of class "+second.getClass().getComponentType().getName());
        }*/
        int length1=first==null?0:first.length;
        int length2=second==null?0:second.length;
        List list=new ArrayList(length1+length2);
        for(int i=0;i<length1;i++){
            list.add(first[i]);
        }
        for(int i=0;i<length2;i++){
            list.add(second[i]);
        }
        return list;
    }
    
    /**
     * 
     * @param compositeList
     * @return
     */
	public static Object[] compositeArrays(Object[] compositeList){
		List result = new ArrayList();
		for (int i=0;i<compositeList.length;i++){
			CollectionUtils.
				addAll(result,(Object[])compositeList[i]);
		}
		return result.toArray();
	}
}
