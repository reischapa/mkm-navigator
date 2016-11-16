package com.mkmnav.android.parallelFramework;

import android.os.AsyncTask;

/**
 * Created by chapa on 14-11-2016.
 */

public class ZeroAsyncTask<U,P> extends AsyncTask<Void,U, P> {

    protected AsyncTaskParameterCallback<P> mIn = null;
    protected AsyncTaskExecutionCallback<P, P> mExe = null;
    protected AsyncTaskUpdateCallback<U> mUp = null;
    protected AsyncTaskReturnCallback<P> mRet = null;

    public ZeroAsyncTask(AsyncTaskParameterCallback<P> mIn, AsyncTaskExecutionCallback<P,P> mExe, AsyncTaskUpdateCallback<U> mUp, AsyncTaskReturnCallback<P> mRet){
        this.mIn = mIn;
        this.mExe = mExe;
        this.mUp = mUp;
        this.mRet = mRet;
    }

    protected ZeroAsyncTask() {
    }

    @Override
    protected P doInBackground(Void... voids) {
        if (mExe!=null && mIn!=null) return mExe.executeTask(mIn.giveParameters());
        return null;
    }

    @Override
    protected void onProgressUpdate(U... progress){
        if (mUp!=null) mUp.broadcastProgress(progress);
    }

    @Override
    protected void onPostExecute(P result){
        if (mRet!=null) mRet.returnResult(result);
    }

}
