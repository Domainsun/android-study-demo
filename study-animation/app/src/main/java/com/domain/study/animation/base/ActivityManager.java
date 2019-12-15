package com.domain.study.animation.base;

import android.app.Activity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Activities管理
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;
    private static Stack<Activity> finishActivityStack; //需要一次性关闭的activity的栈列表
    private static ActivityManager instance;

    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            if (activityStack == null) {
                activityStack = new Stack<>();
            }
            activityStack.add(activity);
        }
    }

//    /**
//     * 获取堆栈中倒数第二个个压入的Activity
//     */
//    public Activity lastButOneActivity() {
//        if (activityStack != null && activityStack.size() > 1) {
//            return activityStack.get(activityStack.size() - 2);
//        }
//        return null;
//    }

    /**
     * 获取堆栈中最后一个压入的Activity
     */
    public Activity currentActivity() {
        if (activityStack != null) {
            //activityStack.size() - 1
            return activityStack.lastElement();
        }
        return null;
    }

    public Activity getActivity(Class<?> clazz) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                return activity;
            }
        }
        return null;
    }



    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (activityStack != null) {
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
//    public void finishActivity(Class<?>... cls) {
////        for (Class c : cls) {
////            Iterator<Activity> iterator = activityStack.iterator();
////            while (iterator.hasNext()) {
////                Activity activity = iterator.next();
////                if (activity.getClass().equals(c)) {
////                    //防止ConcurrentModificationException
////                    iterator.remove();
////                    if (!activity.isFinishing()) {
////                        activity.finish();
////                    }
////                }
////            }
////        }
//
//        List<Activity> finishList = new ArrayList<>();
//        for (Class c : cls) {
//            for (Activity activity : activityStack) {
//                if (activity.getClass().equals(c)) {
//                    finishList.add(activity);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < finishList.size(); i++) {
//            finishActivity(finishList.get(i));
//            finishList.set(i, null);
//        }
//    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (activityStack != null) {
                activityStack.remove(activity);
                if (!activity.isFinishing()) {
                    activity.finish();
                    activity=null;
                }
            }
        }
    }
    /**
     * 结束指定类名的activity
     */

    public void finishActivity(Class<?> cls) {

//        for (Activity activity : activityStack) {
//            if (activity.getClass().equals(cls)) {
//                finishActivity(activity);
//            }
//        }
        //改为下面写法，解决java.util.ConcurrentModificationException异常
        Iterator<Activity> iterator = activityStack.iterator();
        while(iterator.hasNext()){
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


    /**
     * 关闭其他Activity
     */
    public synchronized void finishExceptAct(Class<?>... cls) {
        HashSet<Class<?>> map = pack2set(cls);

        Stack<Activity> cache = activityStack;
        activityStack = new Stack<>();
        for (Activity act : cache) {
            if (act != null && !act.isFinishing()) {
                if (map.contains(act.getClass())) {
                    activityStack.add(act);
                } else {
                    act.finish();
                }
            }
        }
        map.clear();
        cache.clear();
    }

    private HashSet<Class<?>> pack2set(Class<?>... cls) {
        HashSet<Class<?>> map = new HashSet<>();
        int len = cls != null ? cls.length : 0;
        for (int i = 0; i < len; i++) {
            map.add(cls[i]);
        }
        return map;
    }


    //region 这里处理 关闭多个activity
    /**
     * 添加Activity到需要一次性关闭的堆栈
     */
    public void addToMoreActivity(Activity activity) {
        if (activity != null) {
            if (finishActivityStack == null) {
                finishActivityStack = new Stack<>();
            }
            finishActivityStack.add(activity);
        }
    }

    /**
     * 结束所有需要一次性关闭的Activity
     */
    public void finishMoreActivity() {
        if (finishActivityStack != null) {
            for (int i = 0, size = finishActivityStack.size(); i < size; i++) {
                if (null != finishActivityStack.get(i)) {
                    finishActivityStack.get(i).finish();
                }
            }
            finishActivityStack.clear();
        }
    }

    //endregion



    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            App.getApp().onTerminate();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 获取未停止的Activity的HashCode
     */
    public HashSet<Integer> activitiesHashCode() {
        HashSet<Integer> cacheKeys = new HashSet<>();
        if (activityStack != null && activityStack.size() > 0) {
            for (Activity act : activityStack) {
                if (act != null) {
                    if (!act.isFinishing()) {
                        cacheKeys.add(act.hashCode());
                    }
                }
            }
        }
        return cacheKeys;
    }
}
