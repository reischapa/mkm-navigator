package com.mkmnav.android.parallelFramework;

/**
 * Created by chapa on 14-11-2016.
 */

public interface AsyncTaskExecutionCallback<I,O> {
    public O executeTask(I args);
}
