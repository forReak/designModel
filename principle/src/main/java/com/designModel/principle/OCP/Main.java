package com.designModel.principle.OCP;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author furao
 * @desc
 * @date 2020/11/24
 * @package com.designModel.principle.OCP
 */
public class Main {
    private static ArrayList<String> classPaths = new ArrayList<String>();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //流量是5M
        int flow = 5;
        //计算费用
        BigDecimal sum = null;
        //扫描com.designModel.principle.OCP.userPackage 包下是否有用户自定义的计算方式
        String packagePath = "com.designModel.principle.OCP.userPackage".replace(".",File.separator);
        String path = Main.class.getResource("/").getPath();
        File file = new File(path + packagePath);
        doPath(file);
        if(classPaths.size()>0){
            //如果有用户自定义的计算方式，那么用用户自定义的计算方式
            String userClass = classPaths.get(0);
            String name = userClass.substring(0,userClass.indexOf("."));
            Class<?> aClass = Class.forName("com.designModel.principle.OCP.userPackage."+name);
            Object o = aClass.getConstructors()[0].newInstance(flow);
            Method sum1 = aClass.getMethod("sum");
            Object invokeResult = sum1.invoke(o);
            if(invokeResult instanceof BigDecimal){
                sum = (BigDecimal) invokeResult;
            }
        }else{
            //否则使用系统的计算方式
            Cal cal = new Cal(flow);
            sum = cal.sum();
        }

        System.out.println("money is "+sum.toString());


    }

    private static void doPath(File file) {
        if (file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {//标准文件
            //标准文件我们就判断是否是class文件
            if (file.getName().endsWith(".class")) {
                //如果是class文件我们就放入我们的集合中。
                classPaths.add(file.getName());
            }
        }
    }
}
