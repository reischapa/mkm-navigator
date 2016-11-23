package sheetsoft.com.mkmnavigator.android.parallelTaskStorage;

import android.os.AsyncTask;

import sheetsoft.com.mkmnavigator.android.parallelFramework.ZeroAsyncTask;


/**
 * Created by chapa on 11/15/2016.
 */

public class AT_HTMLDownloader<I,U,P> extends ZeroAsyncTask<I,U,P> {



    public interface HTMLDownloader_InputCallback<P>{
        P HTMLDownloader_InputCallback();
    }

    public interface HTMLDownloader_ExecutorCallback<I,P>{
        P HTMLDownloader_ExecutorCallback(P args, AsyncTask ref, I... taskArgs);
    }

    public interface HTMLDownloader_UpdaterCallback<U>{
        void HTMLDownloader_UpdaterCallback(U... out);
    }

    public interface HTMLDownloader_ReturnerCallback<P>{
        void HTMLDownloader_ReturnerCallback(P out);
    }

    public interface HTMLDownloader_CancelledCallback<P>{
        void HTMLDownloader_CancelledCallback(AsyncTask ref);
    }


    public interface HTMLDownloader_IEURC_Callbacks<I,U,P> extends HTMLDownloader_InputCallback<P>,
                                                 HTMLDownloader_ExecutorCallback<I,P>,
                                                 HTMLDownloader_UpdaterCallback<U>,
                                                 HTMLDownloader_ReturnerCallback<P>,
                                                 HTMLDownloader_CancelledCallback<P>{}

    public interface HTMLDownloader_IER_Callbacks<I,P> extends HTMLDownloader_InputCallback<P>,
                                               HTMLDownloader_ExecutorCallback<I,P>,
                                               HTMLDownloader_ReturnerCallback<P>{}

    public interface HTMLDownloader_IERC_Callbacks<I,P> extends HTMLDownloader_InputCallback<P>,
                                                          HTMLDownloader_ExecutorCallback<I,P>,
                                                          HTMLDownloader_ReturnerCallback<P>,
                                                          HTMLDownloader_CancelledCallback<P>{}



    private String stringContent=null;

    public String getContent(){
        return stringContent;
    }

    public void setContent(String input){
        this.stringContent = input;
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


    private class HTMLDownloader_ExecutorCallbackWrapper<I,P> implements AsyncTaskExecutionCallback<I,P,P> {
        private HTMLDownloader_ExecutorCallback<I,P> mExe;
        private AsyncTask<Object,Object,Object> refTask;

        public HTMLDownloader_ExecutorCallbackWrapper(HTMLDownloader_ExecutorCallback<I,P> in, AsyncTask ref){
            this.mExe = in;
            this.refTask = ref;
        }
        @Override
        public P executeTask(P args, I... varargs) {
            if (mExe !=null) return mExe.HTMLDownloader_ExecutorCallback(args,this.refTask, varargs);
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

    private class HTMLDownloader_CancelledCallbackWrapper<P> implements AsyncTaskCancelledCallBack {
        private HTMLDownloader_CancelledCallback mCanc;
        private AsyncTask<Object,Object,Object> refTask;
        public HTMLDownloader_CancelledCallbackWrapper(HTMLDownloader_CancelledCallback<P> in, AsyncTask refTask) {
            this.mCanc = in;
            this.refTask = refTask;
        }
        @Override
        public void imSurpressed() {
            this.mCanc.HTMLDownloader_CancelledCallback(this.refTask);
        }
    }


    public AT_HTMLDownloader(HTMLDownloader_InputCallback<P> input ,
                             HTMLDownloader_ExecutorCallback<I,P> exe,
                             HTMLDownloader_ReturnerCallback<P> returner){

        this.mIn = new HTMLDownloader_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLDownloader_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mRet = new HTMLDownloader_ReturnerCallbackWrapper<P>(returner);
    }


    public AT_HTMLDownloader(HTMLDownloader_InputCallback<P> input ,
                             HTMLDownloader_UpdaterCallback<U> update  ,
                             HTMLDownloader_ExecutorCallback<I,P> exe,
                             HTMLDownloader_ReturnerCallback<P> returner){

        this.mIn = new HTMLDownloader_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLDownloader_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mUp = new HTMLDownloader_UpdaterCallbackWrapper<U>(update);
        this.mRet = new HTMLDownloader_ReturnerCallbackWrapper<P>(returner);
    }


    public AT_HTMLDownloader(HTMLDownloader_InputCallback<P> input ,
                             HTMLDownloader_UpdaterCallback<U> update  ,
                             HTMLDownloader_ExecutorCallback<I,P> exe,
                             HTMLDownloader_ReturnerCallback<P> returner,
                             HTMLDownloader_CancelledCallback<P> canceller){

        this.mIn = new HTMLDownloader_InputCallbackWrapper<P>(input);
        this.mExe = new HTMLDownloader_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mUp = new HTMLDownloader_UpdaterCallbackWrapper<U>(update);
        this.mRet = new HTMLDownloader_ReturnerCallbackWrapper<P>(returner);
        this.mCanc = new HTMLDownloader_CancelledCallbackWrapper<P>(canceller,this);
    }








    public AT_HTMLDownloader(){}






}
