package pers.tuershen.nbtedit.configuration;

import pers.tuershen.nbtedit.NBTEditPanel;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.EditFunctionManager;
import pers.tuershen.nbtedit.function.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class DynamicLoadingFunction {

    private File library;

    private List<File> fileList;

    private List<Class<? extends AbstractEditManager>> funClassSet;

    public DynamicLoadingFunction(File library) {
        this.library = library;
        this.fileList = new ArrayList<>();
        this.funClassSet = new ArrayList<>();
    }

    /**
     * 加载function文件夹中的所有jar
     */
    public void loadJarFile() {
        boolean isDirectory = this.library.isDirectory();
        if (!isDirectory) this.library.mkdirs();
        String[] fileList = this.library.list();
        String funMkdirsPath = this.library.getPath();
        for (int i = 0; i < fileList.length; i++) {
            File readFile = new File(funMkdirsPath + "\\" + fileList[i]);
            if (readFile.getName().endsWith(".jar")) {
                this.fileList.add(readFile);
            }
        }
        loadJarInSystemClassLoader();
    }

    /**
     * 功能类写入Set集合，便于初始化
     * @param <F>
     */
    public <F extends AbstractEditManager> void writeClassToHashSet() {
       this.fileList.forEach((file -> {
           try {
               JarFile jarFile = new JarFile(file);
               EditFunctionManager.reloadManagerList();
               Enumeration<JarEntry> enumFiles = jarFile.entries();
               JarEntry entry;
               int count = 0;
               while(enumFiles.hasMoreElements()) {
                   entry = enumFiles.nextElement();
                   String classFullName = entry.getName();
                   if (entry.getName().endsWith(".class")) {
                       String className = classFullName.substring(0, classFullName.length()-6).replace("/", ".");
                       Class<F> loadClazz = (Class<F>) Class.forName(className);
                       if (AbstractEditManager.class.isAssignableFrom(loadClazz) && loadClazz.isAnnotationPresent(Autowired.class)) {
                           Autowired autowired = loadClazz.getAnnotation(Autowired.class);
                           this.funClassSet.add((Class<? extends AbstractEditManager>) autowired.Fun());
                           count++;
                       }
                   }
               }
               NBTEditPanel.plugin.loadJarLogger(file.getName(), count);
           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
       }));
    }

    /**
     *  加载function的所有jar
     *  @auther Tuershen Create by 2020-12-23:15.33
     *  URLClassLoader classLoader = (URLClassLoader) NBTEditPanel.plugin.getClass().getClassLoader(); 这个指当前Bukkit线程的类加载器
     *  URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader()); 这个指当前线程的类加载器
     *  URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader() 获取系统类加载器
     */
    public void loadJarInSystemClassLoader() {
        this.fileList.forEach((f) -> {
            Method method = null;
            boolean accessible = false;
            try {
                method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                accessible = method.isAccessible();
                method.setAccessible(true);
                URLClassLoader classLoader = (URLClassLoader) NBTEditPanel.plugin.getClass().getClassLoader();
                URL url = f.toURI().toURL();
                method.invoke(classLoader, url);
            } catch (NoSuchMethodException | MalformedURLException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                method.setAccessible(accessible);
            }
        });
        writeClassToHashSet();
        loadFunction();
    }

    /**
     * 注册功能
     */
    public void loadFunction() {
        this.funClassSet.forEach(
                (fun) -> {
                    try {
                        AbstractEditManager abstractEditManager = fun.newInstance();
                        EditFunctionManager.registerEditFunction(abstractEditManager);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void reload() {
        this.library = NBTEditPanel.getFunctionFile();
        this.fileList = new ArrayList<>();
        this.funClassSet = new ArrayList<>();
        loadJarFile();
    }











}
