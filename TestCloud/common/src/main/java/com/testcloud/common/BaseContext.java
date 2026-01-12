package com.testcloud.common;

public class BaseContext{
    private static  final ScopedValue<Long> USER_ID=ScopedValue.newInstance();

    public static void executeWithUser(Long id,Runnable task){
        ScopedValue.where(USER_ID,id).run(task);
    }

    public static Long getCurrentId(){
        if(!USER_ID.isBound()){
            throw  new IllegalStateException("no user_id in thread");
        }
        return USER_ID.get();
    }

}
