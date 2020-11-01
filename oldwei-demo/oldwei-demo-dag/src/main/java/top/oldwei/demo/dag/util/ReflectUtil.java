package top.oldwei.demo.dag.util;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.*;

public class ReflectUtil {

















//        private static final String CGLIB_RENAMED_METHOD_PREFIX = "CGLIB$";
//        private static final Method[] NO_METHODS = new Method[0];
//        private static final Field[] NO_FIELDS = new Field[0];
//        private static Map<String, String> primitiveMap = initPrimitiveMap();
//        private static final Map<Class<?>, Method[]> declaredMethodsCache = new ConcurrentReferenceHashMap(256);
//        private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentReferenceHashMap(256);
//        public static ReflectUtil.FieldFilter COPYABLE_FIELDS = new ReflectUtil.FieldFilter() {
//            public boolean matches(Field field) {
//                return !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers());
//            }
//        };
//        public static ReflectUtil.MethodFilter NON_BRIDGED_METHODS = new ReflectUtil.MethodFilter() {
//            public boolean matches(Method method) {
//                return !method.isBridge();
//            }
//        };
//        public static ReflectUtil.MethodFilter USER_DECLARED_METHODS = new ReflectUtil.MethodFilter() {
//            public boolean matches(Method method) {
//                return !method.isBridge() && method.getDeclaringClass() != Object.class;
//            }
//        };
//
//        public ReflectUtil() {
//        }
//
//        private static Map<String, String> initPrimitiveMap() {
//            primitiveMap = new HashMap();
//            primitiveMap.put("int", "java.lang.Integer");
//            primitiveMap.put("byte", "java.lang.Byte");
//            primitiveMap.put("char", "java.lang.Char");
//            primitiveMap.put("short", "java.lang.Short");
//            primitiveMap.put("long", "java.lang.Long");
//            primitiveMap.put("double", "java.lang.Double");
//            primitiveMap.put("boolean", "java.lang.Boolean");
//            primitiveMap.put("float", "java.lang.Float");
//            return primitiveMap;
//        }
//
//        public static Field findField(Class<?> clazz, String name) {
//            return findField(clazz, name, (Class)null);
//        }
//
//        public static Field findField(Class<?> clazz, String name, Class<?> type) {
//            for(Class searchType = clazz; Object.class != searchType && searchType != null; searchType = searchType.getSuperclass()) {
//                Field[] fields = getDeclaredFields(searchType);
//                Field[] var5 = fields;
//                int var6 = fields.length;
//
//                for(int var7 = 0; var7 < var6; ++var7) {
//                    Field field = var5[var7];
//                    if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
//                        return field;
//                    }
//                }
//            }
//
//            return null;
//        }
//
//        public static void setField(Field field, Object target, Object value) {
//            try {
//                field.set(target, value);
//            } catch (IllegalAccessException var4) {
//                handleReflectionException(var4);
//                throw new IllegalStateException("Unexpected reflection exception - " + var4.getClass().getName() + ": " + var4.getMessage());
//            }
//        }
//
//        public static Object getField(Field field, Object target) {
//            try {
//                return field.get(target);
//            } catch (IllegalAccessException var3) {
//                handleReflectionException(var3);
//                throw new IllegalStateException("Unexpected reflection exception - " + var3.getClass().getName() + ": " + var3.getMessage());
//            }
//        }
//
//        public static Object newInstance(String className, Object... args) {
//            try {
//                Class cls = Class.forName(className);
//                if (args.length == 0) {
//                    return cls.newInstance();
//                }
//
//                Class[] paramClasses = new Class[args.length];
//
//                for(int i = 0; i < paramClasses.length; ++i) {
//                    paramClasses[i] = args[i].getClass();
//                }
//
//                Constructor constructor = cls.getDeclaredConstructor(paramClasses);
//                constructor.setAccessible(true);
//                return constructor.newInstance(args);
//            } catch (ClassNotFoundException var5) {
//                var5.printStackTrace();
//            } catch (InstantiationException var6) {
//                var6.printStackTrace();
//            } catch (IllegalAccessException var7) {
//                var7.printStackTrace();
//            } catch (NoSuchMethodException var8) {
//                var8.printStackTrace();
//            } catch (InvocationTargetException var9) {
//                var9.printStackTrace();
//            }
//
//            throw new IllegalStateException("Should never get here");
//        }
//
//        public static Method findMethod(Class<?> clazz, String name) {
//            return findMethod(clazz, name);
//        }
//
//        public static Method findMethod(Class<?> clazz, String name, String[] classTypes) throws ClassNotFoundException {
//            Class[] classes = new Class[classTypes.length];
//
//            for(int i = 0; i < classes.length; ++i) {
//                String classType = classTypes[i];
//                if (primitiveMap.containsKey(classType)) {
//                    classType = (String)primitiveMap.get(classType);
//                }
//
//                Class cls;
//                if (classType.endsWith("String[]")) {
//                    cls = (new String[1]).getClass();
//                } else if (classType.endsWith("long[]")) {
//                    cls = (new long[1]).getClass();
//                } else {
//                    cls = Class.forName(classType);
//                }
//
//                classes[i] = cls;
//            }
//
//            return findMethod(clazz, name, classes);
//        }
//
//        public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
//            for(Class searchType = clazz; searchType != null; searchType = searchType.getSuperclass()) {
//                Method[] methods = searchType.isInterface() ? searchType.getMethods() : getDeclaredMethods(searchType);
//                Method[] var5 = methods;
//                int var6 = methods.length;
//
//                for(int var7 = 0; var7 < var6; ++var7) {
//                    Method method = var5[var7];
//                    if (name.equals(method.getName()) && (paramTypes == null || arrayApprEquals(paramTypes, method.getParameterTypes()))) {
//                        return method;
//                    }
//                }
//            }
//
//            return null;
//        }
//
//        private static boolean arrayEquals(Class[] array1, Class[] array2) {
//            if (array1 == array2) {
//                return true;
//            } else if (array1 != null && array2 != null) {
//                int length = array1.length;
//                if (array2.length != length) {
//                    return false;
//                } else {
//                    int i = 0;
//
//                    while(true) {
//                        if (i >= length) {
//                            return true;
//                        }
//
//                        Class o1 = array1[i];
//                        Class o2 = array2[i];
//                        Iterator var6 = primitiveMap.keySet().iterator();
//
//                        while(var6.hasNext()) {
//                            String key = (String)var6.next();
//                            String value = (String)primitiveMap.get(key);
//                            if (o1.getName().equals(key) && o2.getName().equals(value)) {
//                                return true;
//                            }
//
//                            if (o1.getName().equals(value) && o2.getName().equals(key)) {
//                                return true;
//                            }
//                        }
//
//                        if (o1 == null) {
//                            if (o2 != null) {
//                                break;
//                            }
//                        } else if (!o1.equals(o2)) {
//                            break;
//                        }
//
//                        ++i;
//                    }
//
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//
//        private static boolean arrayApprEquals(Class[] array1, Class[] array2) {
//            if (array1 == array2) {
//                return true;
//            } else if (array1 != null && array2 != null) {
//                int length = array1.length;
//                if (array2.length != length) {
//                    return false;
//                } else {
//                    for(int i = 0; i < length; ++i) {
//                        Class o1 = array1[i];
//                        Class o2 = array2[i];
//                        boolean sameType = false;
//                        Iterator var7 = primitiveMap.keySet().iterator();
//
//                        while(var7.hasNext()) {
//                            String key = (String)var7.next();
//                            String value = (String)primitiveMap.get(key);
//                            if (o1.getName().equals(key) && o2.getName().equals(value)) {
//                                sameType = true;
//                                break;
//                            }
//
//                            if (o1.getName().equals(value) && o2.getName().equals(key)) {
//                                sameType = true;
//                                break;
//                            }
//                        }
//
//                        if (!sameType && !o1.equals(o2) && !o2.isAssignableFrom(o1)) {
//                            return false;
//                        }
//                    }
//
//                    return true;
//                }
//            } else {
//                return false;
//            }
//        }
//
//        public static Object invokeMethod(Method method, Object target) {
//            return invokeMethod(method, target);
//        }
//
//        public static Object invokeMethod(Method method, Object target, Object... args) {
//            try {
//                return method.invoke(target, args);
//            } catch (Exception var4) {
//                handleReflectionException(var4);
//                throw new IllegalStateException("Should never get here");
//            }
//        }
//
//        public static Object invokeMethod(String methodName, Object target, String[] classTypes, Object... args) {
//            String className = target.getClass().getTypeName();
//
//            int i;
//            StackTraceElement[] causes;
//            int var8;
//            int var9;
//            StackTraceElement element;
//            StackTraceElement cause;
//            StackTraceElement[] elements;
//            StackTraceElement[] var15;
//            int var16;
//            try {
//                Method method = findMethod(target.getClass(), methodName, classTypes);
//                return method.invoke(target, args);
//            } catch (NullPointerException var12) {
//                System.out.println("NullPointerException while invoking method：[" + className + "." + methodName + "]");
//                System.out.println("********* argeuments *********");
//
//                for(i = 0; i < args.length; ++i) {
//                    System.out.println("argeument[" + i + "] is null: " + Boolean.toString(args[i] == null));
//                }
//
//                System.out.println("********* stack trace *********");
//                System.out.println(var12.toString());
//                elements = var12.getStackTrace();
//                causes = elements;
//                var8 = elements.length;
//
//                for(var9 = 0; var9 < var8; ++var9) {
//                    element = causes[var9];
//                    System.out.println("\t" + element.toString());
//                }
//
//                if (var12.getCause() != null) {
//                    System.out.println("Caused by: " + var12.getCause().toString());
//                    causes = var12.getCause().getStackTrace();
//                    var15 = causes;
//                    var9 = causes.length;
//
//                    for(var16 = 0; var16 < var9; ++var16) {
//                        cause = var15[var16];
//                        System.out.println("\t" + cause.toString());
//                    }
//                }
//            } catch (Exception var13) {
//                System.out.println("Exception while invoking method：[" + className + "." + methodName + "]");
//                System.out.println("********* argeuments *********");
//
//                for(i = 0; i < classTypes.length; ++i) {
//                    System.out.println("argeument[" + i + "]:    accept:" + classTypes[i] + "    =>    found:" + args[i].getClass().getTypeName());
//                }
//
//                System.out.println("********* stack trace *********");
//                System.out.println(var13.toString());
//                elements = var13.getStackTrace();
//                causes = elements;
//                var8 = elements.length;
//
//                for(var9 = 0; var9 < var8; ++var9) {
//                    element = causes[var9];
//                    System.out.println("\t" + element.toString());
//                }
//
//                System.out.println("Caused by: " + var13.getCause().toString());
//                causes = var13.getCause().getStackTrace();
//                var15 = causes;
//                var9 = causes.length;
//
//                for(var16 = 0; var16 < var9; ++var16) {
//                    cause = var15[var16];
//                    System.out.println("\t" + cause.toString());
//                }
//
//                handleReflectionException(var13);
//            }
//
//            throw new IllegalStateException("Should never get here");
//        }
//
//        public static Object invokeMethod(String methodName, Object target, Object... args) {
//            String[] classTypes = new String[args.length];
//
//            for(int i = 0; i < classTypes.length; ++i) {
//                classTypes[i] = args[i].getClass().getTypeName();
//            }
//
//            return invokeMethod(methodName, target, classTypes, args);
//        }
//
//        public static Object invokeMethod(String className, String methodName, Object... args) {
//            String[] classTypes = new String[args.length];
//
//            for(int i = 0; i < classTypes.length; ++i) {
//                classTypes[i] = args[i].getClass().getTypeName();
//            }
//
//            return invokeMethod(className, methodName, classTypes, args);
//        }
//
//        public static Object invokeMethod(String className, String methodName, String[] classTypes, Object... args) {
//            int i;
//            StackTraceElement[] causes;
//            int var7;
//            int var8;
//            StackTraceElement element;
//            StackTraceElement cause;
//            StackTraceElement[] elements;
//            StackTraceElement[] var16;
//            int var17;
//            try {
//                Class cls = Class.forName(className);
//                Object target = cls.newInstance();
//                Method method = findMethod(cls, methodName, classTypes);
//                return method.invoke(target, args);
//            } catch (NullPointerException var11) {
//                System.out.println("NullPointerException while invoking method：[" + className + "." + methodName + "]");
//                System.out.println("********* argeuments *********");
//
//                for(i = 0; i < args.length; ++i) {
//                    System.out.println("argeument[" + i + "] is null: " + Boolean.toString(args[i] == null));
//                }
//
//                System.out.println("********* stack trace *********");
//                System.out.println(var11.toString());
//                elements = var11.getStackTrace();
//                causes = elements;
//                var7 = elements.length;
//
//                for(var8 = 0; var8 < var7; ++var8) {
//                    element = causes[var8];
//                    System.out.println("\t" + element.toString());
//                }
//
//                if (var11.getCause() != null) {
//                    System.out.println("Caused by: " + var11.getCause().toString());
//                    causes = var11.getCause().getStackTrace();
//                    var16 = causes;
//                    var8 = causes.length;
//
//                    for(var17 = 0; var17 < var8; ++var17) {
//                        cause = var16[var17];
//                        System.out.println("\t" + cause.toString());
//                    }
//                }
//            } catch (Exception var12) {
//                System.out.println("Exception while invoking method：[" + className + "." + methodName + "]");
//                System.out.println("********* argeuments *********");
//
//                for(i = 0; i < classTypes.length; ++i) {
//                    System.out.println("argeument[" + i + "]:    accept:" + classTypes[i] + "    =>    found:" + (args[i] == null ? "null" : args[i].getClass().getTypeName()));
//                }
//
//                System.out.println("********* stack trace *********");
//                System.out.println(var12.toString());
//                elements = var12.getStackTrace();
//                causes = elements;
//                var7 = elements.length;
//
//                for(var8 = 0; var8 < var7; ++var8) {
//                    element = causes[var8];
//                    System.out.println("\t" + element.toString());
//                }
//
//                System.out.println("Caused by: " + var12.getCause().toString());
//                causes = var12.getCause().getStackTrace();
//                var16 = causes;
//                var8 = causes.length;
//
//                for(var17 = 0; var17 < var8; ++var17) {
//                    cause = var16[var17];
//                    System.out.println("\t" + cause.toString());
//                }
//
//                handleReflectionException(var12);
//            }
//
//            throw new IllegalStateException("Should never get here");
//        }
//
//        public static Object invokeStaticMethod(String className, String methodName, String[] classTypes, Object... args) {
//            try {
//                Class cls = Class.forName(className);
//                Method method = findMethod(cls, methodName, classTypes);
//                return invokeMethod((Method)method, (Object)null, args);
//            } catch (Exception var6) {
//                handleReflectionException(var6);
//                throw new IllegalStateException("Should never get here");
//            }
//        }
//
//        public static Object invokeJdbcMethod(Method method, Object target) throws SQLException {
//            return invokeJdbcMethod(method, target);
//        }
//
//        public static Object invokeJdbcMethod(Method method, Object target, Object... args) throws SQLException {
//            try {
//                return method.invoke(target, args);
//            } catch (IllegalAccessException var4) {
//                handleReflectionException(var4);
//            } catch (InvocationTargetException var5) {
//                if (var5.getTargetException() instanceof SQLException) {
//                    throw (SQLException)var5.getTargetException();
//                }
//
//                handleInvocationTargetException(var5);
//            }
//
//            throw new IllegalStateException("Should never get here");
//        }
//
//        public static void handleReflectionException(Exception ex) {
//            if (ex instanceof NoSuchMethodException) {
//                throw new IllegalStateException("Method not found: " + ex.getMessage());
//            } else if (ex instanceof IllegalAccessException) {
//                throw new IllegalStateException("Could not access method: " + ex.getMessage());
//            } else {
//                if (ex instanceof InvocationTargetException) {
//                    handleInvocationTargetException((InvocationTargetException)ex);
//                }
//
//                if (ex instanceof RuntimeException) {
//                    throw (RuntimeException)ex;
//                } else {
//                    throw new UndeclaredThrowableException(ex);
//                }
//            }
//        }
//
//        public static void handleInvocationTargetException(InvocationTargetException ex) {
//            rethrowRuntimeException(ex.getTargetException());
//        }
//
//        public static void rethrowRuntimeException(Throwable ex) {
//            if (ex instanceof RuntimeException) {
//                throw (RuntimeException)ex;
//            } else if (ex instanceof Error) {
//                throw (Error)ex;
//            } else {
//                throw new UndeclaredThrowableException(ex);
//            }
//        }
//
//        public static void rethrowException(Throwable ex) throws Exception {
//            if (ex instanceof Exception) {
//                throw (Exception)ex;
//            } else if (ex instanceof Error) {
//                throw (Error)ex;
//            } else {
//                throw new UndeclaredThrowableException(ex);
//            }
//        }
//
//        public static boolean declaresException(Method method, Class<?> exceptionType) {
//            Class<?>[] declaredExceptions = method.getExceptionTypes();
//            Class[] var3 = declaredExceptions;
//            int var4 = declaredExceptions.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                Class<?> declaredException = var3[var5];
//                if (declaredException.isAssignableFrom(exceptionType)) {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//
//        public static boolean isPublicStaticFinal(Field field) {
//            int modifiers = field.getModifiers();
//            return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
//        }
//
//        public static boolean isEqualsMethod(Method method) {
//            if (method != null && method.getName().equals("equals")) {
//                Class<?>[] paramTypes = method.getParameterTypes();
//                return paramTypes.length == 1 && paramTypes[0] == Object.class;
//            } else {
//                return false;
//            }
//        }
//
//        public static boolean isHashCodeMethod(Method method) {
//            return method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
//        }
//
//        public static boolean isToStringMethod(Method method) {
//            return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
//        }
//
//        public static boolean isObjectMethod(Method method) {
//            if (method == null) {
//                return false;
//            } else {
//                try {
//                    Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
//                    return true;
//                } catch (Exception var2) {
//                    return false;
//                }
//            }
//        }
//
//        public static boolean isCglibRenamedMethod(Method renamedMethod) {
//            String name = renamedMethod.getName();
//            if (!name.startsWith("CGLIB$")) {
//                return false;
//            } else {
//                int i;
//                for(i = name.length() - 1; i >= 0 && Character.isDigit(name.charAt(i)); --i) {
//                }
//
//                return i > "CGLIB$".length() && i < name.length() - 1 && name.charAt(i) == '$';
//            }
//        }
//
//        public static void makeAccessible(Field field) {
//            if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
//                field.setAccessible(true);
//            }
//
//        }
//
//        public static void makeAccessible(Method method) {
//            if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
//                method.setAccessible(true);
//            }
//
//        }
//
//        public static void makeAccessible(Constructor<?> ctor) {
//            if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
//                ctor.setAccessible(true);
//            }
//
//        }
//
//        public static void doWithLocalMethods(Class<?> clazz, ReflectUtil.MethodCallback mc) {
//            Method[] methods = getDeclaredMethods(clazz);
//            Method[] var3 = methods;
//            int var4 = methods.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                Method method = var3[var5];
//
//                try {
//                    mc.doWith(method);
//                } catch (IllegalAccessException var8) {
//                    throw new IllegalStateException("Not allowed to access method '" + method.getName() + "': " + var8);
//                }
//            }
//
//        }
//
//        public static void doWithMethods(Class<?> clazz, ReflectUtil.MethodCallback mc) {
//            doWithMethods(clazz, mc, (ReflectUtil.MethodFilter)null);
//        }
//
//        public static void doWithMethods(Class<?> clazz, ReflectUtil.MethodCallback mc, ReflectUtil.MethodFilter mf) {
//            Method[] methods = getDeclaredMethods(clazz);
//            Method[] var4 = methods;
//            int var5 = methods.length;
//
//            int var6;
//            for(var6 = 0; var6 < var5; ++var6) {
//                Method method = var4[var6];
//                if (mf == null || mf.matches(method)) {
//                    try {
//                        mc.doWith(method);
//                    } catch (IllegalAccessException var9) {
//                        throw new IllegalStateException("Not allowed to access method '" + method.getName() + "': " + var9);
//                    }
//                }
//            }
//
//            if (clazz.getSuperclass() != null) {
//                doWithMethods(clazz.getSuperclass(), mc, mf);
//            } else if (clazz.isInterface()) {
//                Class[] var10 = clazz.getInterfaces();
//                var5 = var10.length;
//
//                for(var6 = 0; var6 < var5; ++var6) {
//                    Class<?> superIfc = var10[var6];
//                    doWithMethods(superIfc, mc, mf);
//                }
//            }
//
//        }
//
//        public static Method[] getAllDeclaredMethods(Class<?> leafClass) {
//            final List<Method> methods = new ArrayList(32);
//            doWithMethods(leafClass, new ReflectUtil.MethodCallback() {
//                public void doWith(Method method) {
//                    methods.add(method);
//                }
//            });
//            return (Method[])methods.toArray(new Method[methods.size()]);
//        }
//
//        public static Method[] getUniqueDeclaredMethods(Class<?> leafClass) {
//            final List<Method> methods = new ArrayList(32);
//            doWithMethods(leafClass, new ReflectUtil.MethodCallback() {
//                public void doWith(Method method) {
//                    boolean knownSignature = false;
//                    Method methodBeingOverriddenWithCovariantReturnType = null;
//                    Iterator var4 = methods.iterator();
//
//                    while(var4.hasNext()) {
//                        Method existingMethod = (Method)var4.next();
//                        if (method.getName().equals(existingMethod.getName()) && Arrays.equals(method.getParameterTypes(), existingMethod.getParameterTypes())) {
//                            if (existingMethod.getReturnType() != method.getReturnType() && existingMethod.getReturnType().isAssignableFrom(method.getReturnType())) {
//                                methodBeingOverriddenWithCovariantReturnType = existingMethod;
//                                break;
//                            }
//
//                            knownSignature = true;
//                            break;
//                        }
//                    }
//
//                    if (methodBeingOverriddenWithCovariantReturnType != null) {
//                        methods.remove(methodBeingOverriddenWithCovariantReturnType);
//                    }
//
//                    if (!knownSignature && !ReflectUtil.isCglibRenamedMethod(method)) {
//                        methods.add(method);
//                    }
//
//                }
//            });
//            return (Method[])methods.toArray(new Method[methods.size()]);
//        }
//
//        private static Method[] getDeclaredMethods(Class<?> clazz) {
//            Method[] result = (Method[])declaredMethodsCache.get(clazz);
//            if (result == null) {
//                Method[] declaredMethods = clazz.getDeclaredMethods();
//                List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
//                if (defaultMethods != null) {
//                    result = new Method[declaredMethods.length + defaultMethods.size()];
//                    System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
//                    int index = declaredMethods.length;
//
//                    for(Iterator var5 = defaultMethods.iterator(); var5.hasNext(); ++index) {
//                        Method defaultMethod = (Method)var5.next();
//                        result[index] = defaultMethod;
//                    }
//                } else {
//                    result = declaredMethods;
//                }
//
//                declaredMethodsCache.put(clazz, result.length == 0 ? NO_METHODS : result);
//            }
//
//            return result;
//        }
//
//        private static List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
//            List<Method> result = null;
//            Class[] var2 = clazz.getInterfaces();
//            int var3 = var2.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Class<?> ifc = var2[var4];
//                Method[] var6 = ifc.getMethods();
//                int var7 = var6.length;
//
//                for(int var8 = 0; var8 < var7; ++var8) {
//                    Method ifcMethod = var6[var8];
//                    if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
//                        if (result == null) {
//                            result = new LinkedList();
//                        }
//
//                        result.add(ifcMethod);
//                    }
//                }
//            }
//
//            return result;
//        }
//
//        public static void doWithLocalFields(Class<?> clazz, ReflectUtil.FieldCallback fc) {
//            Field[] var2 = getDeclaredFields(clazz);
//            int var3 = var2.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Field field = var2[var4];
//
//                try {
//                    fc.doWith(field);
//                } catch (IllegalAccessException var7) {
//                    throw new IllegalStateException("Not allowed to access field '" + field.getName() + "': " + var7);
//                }
//            }
//
//        }
//
//        public static void doWithFields(Class<?> clazz, ReflectUtil.FieldCallback fc) {
//            doWithFields(clazz, fc, (ReflectUtil.FieldFilter)null);
//        }
//
//        public static void doWithFields(Class<?> clazz, ReflectUtil.FieldCallback fc, ReflectUtil.FieldFilter ff) {
//            Class targetClass = clazz;
//
//            do {
//                Field[] fields = getDeclaredFields(targetClass);
//                Field[] var5 = fields;
//                int var6 = fields.length;
//
//                for(int var7 = 0; var7 < var6; ++var7) {
//                    Field field = var5[var7];
//                    if (ff == null || ff.matches(field)) {
//                        try {
//                            fc.doWith(field);
//                        } catch (IllegalAccessException var10) {
//                            throw new IllegalStateException("Not allowed to access field '" + field.getName() + "': " + var10);
//                        }
//                    }
//                }
//
//                targetClass = targetClass.getSuperclass();
//            } while(targetClass != null && targetClass != Object.class);
//
//        }
//
//        private static Field[] getDeclaredFields(Class<?> clazz) {
//            Field[] result = (Field[])declaredFieldsCache.get(clazz);
//            if (result == null) {
//                result = clazz.getDeclaredFields();
//                declaredFieldsCache.put(clazz, result.length == 0 ? NO_FIELDS : result);
//            }
//
//            return result;
//        }
//
//        public static void shallowCopyFieldState(final Object src, final Object dest) {
//            if (src == null) {
//                throw new IllegalArgumentException("Source for field copy cannot be null");
//            } else if (dest == null) {
//                throw new IllegalArgumentException("Destination for field copy cannot be null");
//            } else if (!src.getClass().isAssignableFrom(dest.getClass())) {
//                throw new IllegalArgumentException("Destination class [" + dest.getClass().getName() + "] must be same or subclass as source class [" + src.getClass().getName() + "]");
//            } else {
//                doWithFields(src.getClass(), new ReflectUtil.FieldCallback() {
//                    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
//                        ReflectUtil.makeAccessible(field);
//                        Object srcValue = field.get(src);
//                        field.set(dest, srcValue);
//                    }
//                }, COPYABLE_FIELDS);
//            }
//        }
//
//        public static void clearCache() {
//            declaredMethodsCache.clear();
//            declaredFieldsCache.clear();
//        }
//
//        public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//            Object o = invokeMethod("com.datastax.util.lang.StringUtil", "substringIndent", "20g16", "0", "6");
//            System.out.println(o);
//        }
//
//        public static Object conversion(String value, Class<?> c) {
//            if (String.class.equals(c)) {
//                return value;
//            } else if (Integer.class.equals(c)) {
//                return Integer.parseInt(value);
//            } else if (Double.class.equals(c)) {
//                return Double.parseDouble(value);
//            } else if (Float.class.equals(c)) {
//                return Float.parseFloat(value);
//            } else {
//                return Long.class.equals(c) ? Long.parseLong(value) : value;
//            }
//        }
//
//        public static Object evaluateExpression(String expression, Map<String, Object> instanceMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//            Object object = null;
//            String methodFullName = null;
//            String className = null;
//            if (expression.contains(".") && expression.contains("(")) {
//                methodFullName = expression.substring(0, expression.indexOf("("));
//                if (methodFullName.contains(".")) {
//                    className = methodFullName.substring(0, methodFullName.lastIndexOf("."));
//                }
//            }
//
//            if (className != null && instanceMap.containsKey(className)) {
//                String methodName = methodFullName.substring(methodFullName.lastIndexOf(".") + 1);
//                String argText = StringUtil.substringFirstAndLast(expression, "(", ")");
//                Object instance = instanceMap.get(className);
//                Class clazz = instance.getClass();
//                if (argText == null) {
//                    Method method = clazz.getMethod(methodName);
//                    object = method.invoke(instance);
//                } else {
//                    String[] arguments = argText.split(",");
//                    Object[] args = new Object[arguments.length];
//                    List<Method> methodList = getMethods(clazz, methodName, args.length);
//                    Iterator var12 = methodList.iterator();
//
//                    while(var12.hasNext()) {
//                        Method method = (Method)var12.next();
//                        Class[] paramTypes = method.getParameterTypes();
//
//                        for(int i = 0; i < args.length; ++i) {
//                            Class paramType = paramTypes[i];
//                            String arg = arguments[i];
//                            if (arg.contains(".") && arg.contains("(")) {
//                                args[i] = evaluateExpression(arg, instanceMap);
//                            } else if (String.class.equals(paramType)) {
//                                args[i] = arg.replaceAll("\"", "");
//                            } else if (instanceMap.containsKey(arg)) {
//                                args[i] = instanceMap.get(arg);
//                            } else {
//                                args[i] = conversion(arg, paramType);
//                            }
//                        }
//
//                        try {
//                            object = method.invoke(instance, args);
//                            break;
//                        } catch (Exception var18) {
//                        }
//                    }
//                }
//            } else if (instanceMap.containsKey(expression)) {
//                object = instanceMap.get(expression);
//            } else {
//                object = expression;
//            }
//
//            return object;
//        }
//
//        private static Class[] getClasses(Object[] instances) {
//            Class[] classes = new Class[instances.length];
//
//            for(int i = 0; i < instances.length; ++i) {
//                Object instance = instances[i];
//                classes[i] = instance.getClass();
//            }
//
//            return classes;
//        }
//
//        public static List<Method> getMethods(Class clazz, String methodName, int paramNum) throws NoSuchMethodException {
//            List<Method> methodList = new ArrayList();
//            Method[] methods = clazz.getMethods();
//            Method[] var5 = methods;
//            int var6 = methods.length;
//
//            for(int var7 = 0; var7 < var6; ++var7) {
//                Method method = var5[var7];
//                if (method.getName().equals(methodName)) {
//                    int paramCount = method.getParameterTypes().length;
//                    if (paramCount == paramNum) {
//                        methodList.add(method);
//                    }
//                }
//            }
//
//            return methodList;
//        }
//
//        public static List<String> getObjectMethods() {
//            List<String> methodList = new ArrayList();
//            Method[] methods = Object.class.getMethods();
//            Method[] var2 = methods;
//            int var3 = methods.length;
//
//            for(int var4 = 0; var4 < var3; ++var4) {
//                Method method = var2[var4];
//                methodList.add(method.getName());
//            }
//
//            return methodList;
//        }
//
//        public interface FieldFilter {
//            boolean matches(Field var1);
//        }
//
//        public interface FieldCallback {
//            void doWith(Field var1) throws IllegalArgumentException, IllegalAccessException;
//        }
//
//        public interface MethodFilter {
//            boolean matches(Method var1);
//        }
//
//        public interface MethodCallback {
//            void doWith(Method var1) throws IllegalArgumentException, IllegalAccessException;
//        }
//

}
