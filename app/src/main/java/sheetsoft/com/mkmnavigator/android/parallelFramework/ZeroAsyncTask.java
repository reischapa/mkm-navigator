package sheetsoft.com.mkmnavigator.android.parallelFramework;

import android.os.AsyncTask;

/**
 * Created by chapa on 14-11-2016.
 */

public class ZeroAsyncTask<I,U,P> extends AsyncTask<I,U, P> {

    public interface AsyncTaskExecutionCallback<A,B,C> { C executeTask(B args, A... taskInputs);}
    public interface AsyncTaskParameterCallback<T> {T giveParameters(); }
    public interface AsyncTaskReturnCallback<T> {void returnResult(T out);}
    public interface AsyncTaskUpdateCallback<T> {void broadcastProgress(T... out);}

    public interface AsyncTaskCancelledCallBack {void imSurpressed();}


    protected AsyncTaskParameterCallback<P> mIn = null;
    protected AsyncTaskExecutionCallback<I,P,P> mExe = null;
    protected AsyncTaskUpdateCallback<U> mUp = null;
    protected AsyncTaskReturnCallback<P> mRet = null;
    protected AsyncTaskCancelledCallBack mCanc = null;

    public ZeroAsyncTask(AsyncTaskParameterCallback<P> mIn, AsyncTaskExecutionCallback<I,P,P> mExe, AsyncTaskReturnCallback<P> mRet){
        this.mIn = mIn;
        this.mExe = mExe;
        this.mRet = mRet;
    }

    public ZeroAsyncTask(AsyncTaskParameterCallback<P> mIn, AsyncTaskExecutionCallback<I,P,P> mExe, AsyncTaskUpdateCallback<U> mUp, AsyncTaskReturnCallback<P> mRet){
        this.mIn = mIn;
        this.mExe = mExe;
        this.mUp = mUp;
        this.mRet = mRet;
    }

    public ZeroAsyncTask(AsyncTaskParameterCallback<P> mIn, AsyncTaskExecutionCallback<I,P,P> mExe, AsyncTaskUpdateCallback<U> mUp, AsyncTaskReturnCallback<P> mRet, AsyncTaskCancelledCallBack mCanc){
        this.mIn = mIn;
        this.mExe = mExe;
        this.mUp = mUp;
        this.mRet = mRet;
        this.mCanc = mCanc;
    }

    protected ZeroAsyncTask() {
    }

    @Override
    protected P doInBackground(I... voids) {
        if (mExe!=null && mIn!=null) return mExe.executeTask(mIn.giveParameters(), voids);
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

    @Override
    protected void onCancelled(){if (mCanc!=null) mCanc.imSurpressed();}




}
