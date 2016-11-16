package com.mkmnav.android.parallelTaskDefinitions;

import com.mkmnav.android.parallelFramework.AsyncTaskExecutionCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskParameterCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskReturnCallback;
import com.mkmnav.android.parallelFramework.AsyncTaskUpdateCallback;
import com.mkmnav.android.parallelFramework.ZeroAsyncTask;

/**
 * Created by chapa on 11/15/2016.
 */

public class AT_HTMLParser<U,P> extends ZeroAsyncTask<U,P> {



    public static interface HTMLParser_InputCallback<P>{
        public P HTMLParser_InputCallback();
    }

    public static interface HTMLParser_ExecutorCallback<P>{
        public P HTMLParser_ExecutorCallback(P args);
    }

    public static interface HTMLParser_UpdaterCallback<U>{
        public void HTMLParser_UpdaterCallback(U... out);
    }

    public static interface HTMLParser_ReturnerCallback<P>{
        public void HTMLParser_ReturnerCallback(P out);
    }




    private class HTMLParser_InputCallbackWrapper<P> implements AsyncTaskParameterCallback<P> {

        protected HTMLParser_InputCallback<P> mIn;

        public HTMLParser_InputCallbackWrapper(HTMLParser_InputCallback<P> in) {
            this.mIn = in;
        }


        @Override
        public P giveParameters() {
            if (mIn!=null) return mIn.HTMLParser_InputCallback();
            return null;
        }
    }

    private class HTMLParser_UpdaterCallbackWrapper<U> implements AsyncTaskUpdateCallback<U> {
        protected HTMLParser_UpdaterCallback<U> mUp;

        public HTMLParser_UpdaterCallbackWrapper(HTMLParser_UpdaterCallback<U> in){
            this.mUp = in;
        }


        @Override
        public void broadcastProgress(U... out) {
            if (mUp!=null) mUp.HTMLParser_UpdaterCallback(out);
        }
    }


    private class HTMLParser_ExecutorCallbackWrapper<P> implements AsyncTaskExecutionCallback<P,P> {
        protected HTMLParser_ExecutorCallback<P> mExe;

        public HTMLParser_ExecutorCallbackWrapper(HTMLParser_ExecutorCallback<P> in){
            this.mExe = in;
        }


        @Override
        public P executeTask(P args) {
            if (mExe !=null) return mExe.HTMLParser_ExecutorCallback(args);
            return null;
        }
    }


    private class HTMLParser_ReturnerCallbackWrapper<P> implements AsyncTaskReturnCallback<P> {
        protected HTMLParser_ReturnerCallback<P> mRet;

        public HTMLParser_ReturnerCallbackWrapper(HTMLParser_ReturnerCallback<P> in){
            this.mRet = in;
        }


        @Override
        public void returnResult(P out) {
            if (mRet!=null) mRet.HTMLParser_ReturnerCallback(out);
        }
    }



    public AT_HTMLParser(HTMLParser_InputCallback<P> input ,
                         HTMLParser_UpdaterCallback<U> update  ,
                         HTMLParser_ExecutorCallback<P> exe,
                         HTMLParser_ReturnerCallback<P> returner){

        this.mIn = new HTMLParser_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLParser_ExecutorCallbackWrapper<P>(exe);
        this.mUp = new HTMLParser_UpdaterCallbackWrapper<U>(update);
        this.mRet = new HTMLParser_ReturnerCallbackWrapper<P>(returner);
    }

    public AT_HTMLParser(HTMLParser_InputCallback<P> input ,
                         HTMLParser_ExecutorCallback<P> exe,
                         HTMLParser_ReturnerCallback<P> returner){

        this.mIn = new HTMLParser_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLParser_ExecutorCallbackWrapper<P>(exe);
        this.mRet = new HTMLParser_ReturnerCallbackWrapper<P>(returner);
    }





}
