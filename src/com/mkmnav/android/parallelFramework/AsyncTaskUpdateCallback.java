package com.mkmnav.android.parallelFramework;

/**
 * Created by chapa on 11/15/2016.
 */

public interface AsyncTaskUpdateCallback<T> {
    public void broadcastProgress(T... out);
}
