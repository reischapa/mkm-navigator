package com.mkmnav.android.parallelTaskDefinitions;

import com.mkmnav.android.parallelFramework.AsyncTaskExecutionCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskParameterCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskReturnCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskUpdateCallback;
import com.mkmnav.android.parallelFramework.ZeroAsyncTask;

/**
 * Created by chapa on 11/15/2016.
 */

public class AT_HTMLDownloader<U,P> extends ZeroAsyncTask<U,P> {



    public static interface HTMLDownloader_InputCallback<P>{
        public P HTMLDownloader_InputCallback();
    }

    public static interface HTMLDownloader_ExecutorCallback<P>{
        public P HTMLDownloader_ExecutorCallback(P args);
    }

    public static interface HTMLDownloader_UpdaterCallback<U>{
        public void HTMLDownloader_UpdaterCallback(U... out);
    }

    public static interface HTMLDownloader_ReturnerCallback<P>{
        public void HTMLDownloader_ReturnerCallback(P out);
    }




    private class HTMLDownloader_InputCallbackWrapper<P> implements AsyncTaskParameterCallback<P> {

        protected HTMLDownloader_InputCallback<P> mIn;

        public HTMLDownloader_InputCallbackWrapper(HTMLDownloader_InputCallback<P> in) {
            this.mIn = in;
        }


        @Override
        public P giveParameters() {
            if (mIn!=null) return mIn.HTMLDownloader_InputCallback();
            return null;
        }
    }

    private class HTMLDownloader_UpdaterCallbackWrapper<U> implements AsyncTaskUpdateCallback<U> {
        protected HTMLDownloader_UpdaterCallback<U> mUp;

        public HTMLDownloader_UpdaterCallbackWrapper(HTMLDownloader_UpdaterCallback<U> in){
            this.mUp = in;
        }


        @Override
        public void broadcastProgress(U... out) {
            if (mUp!=null) mUp.HTMLDownloader_UpdaterCallback(out);
        }
    }


    private class HTMLDownloader_ExecutorCallbackWrapper<P> implements AsyncTaskExecutionCallback<P,P> {
        protected HTMLDownloader_ExecutorCallback<P> mExe;

        public HTMLDownloader_ExecutorCallbackWrapper(HTMLDownloader_ExecutorCallback<P> in){
            this.mExe = in;
        }


        @Override
        public P executeTask(P args) {
            if (mExe !=null) return mExe.HTMLDownloader_ExecutorCallback(args);
            return null;
        }
    }


    private class HTMLDownloader_ReturnerCallbackWrapper<P> implements AsyncTaskReturnCallback<P> {
        protected HTMLDownloader_ReturnerCallback<P> mRet;

        public HTMLDownloader_ReturnerCallbackWrapper(HTMLDownloader_ReturnerCallback<P> in){
            this.mRet = in;
        }


        @Override
        public void returnResult(P out) {
            if (mRet!=null) mRet.HTMLDownloader_ReturnerCallback(out);
        }
    }



    public AT_HTMLDownloader(HTMLDownloader_InputCallback<P> input ,
                                    HTMLDownloader_UpdaterCallback<U> update  ,
                                        HTMLDownloader_ExecutorCallback<P> exe,
                                            HTMLDownloader_ReturnerCallback<P> returner){

        this.mIn = new HTMLDownloader_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLDownloader_ExecutorCallbackWrapper<P>(exe);
        this.mUp = new HTMLDownloader_UpdaterCallbackWrapper<U>(update);
        this.mRet = new HTMLDownloader_ReturnerCallbackWrapper<P>(returner);
    }

    public AT_HTMLDownloader(HTMLDownloader_InputCallback<P> input ,
                             HTMLDownloader_ExecutorCallback<P> exe,
                             HTMLDownloader_ReturnerCallback<P> returner){

        this.mIn = new HTMLDownloader_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLDownloader_ExecutorCallbackWrapper<P>(exe);
        this.mRet = new HTMLDownloader_ReturnerCallbackWrapper<P>(returner);
    }





}
