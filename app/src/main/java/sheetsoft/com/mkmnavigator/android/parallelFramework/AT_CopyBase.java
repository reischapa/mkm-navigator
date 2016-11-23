package sheetsoft.com.mkmnavigator.android.parallelFramework;

import android.os.AsyncTask;

/**
 * Created by chapa on 11/15/2016.
 */

public class AT_CopyBase<I,U,P> extends ZeroAsyncTask<I,U,P> {



    public interface CopyBase_InputCallback<P>{
        P CopyBase_InputCallback();
    }

    public interface CopyBase_ExecutorCallback<I,P>{
        P CopyBase_ExecutorCallback(P args, AsyncTask ref, I... taskArgs);
    }

    public interface CopyBase_UpdaterCallback<U>{
        void CopyBase_UpdaterCallback(U... out);
    }

    public interface CopyBase_ReturnerCallback<P>{
        void CopyBase_ReturnerCallback(P out);
    }

    public interface CopyBase_CancelledCallback<P>{
        void CopyBase_CancelledCallback(AsyncTask ref);
    }


    public interface CopyBase_IEURC_Callbacks<I,U,P> extends CopyBase_InputCallback<P>,
                                                 CopyBase_ExecutorCallback<I,P>,
                                                 CopyBase_UpdaterCallback<U>,
                                                 CopyBase_ReturnerCallback<P>,
                                                 CopyBase_CancelledCallback<P>{}

    public interface CopyBase_IER_Callbacks<I,U,P> extends CopyBase_InputCallback<P>,
                                               CopyBase_ExecutorCallback<I,P>,
                                               CopyBase_ReturnerCallback<P>{}

    public interface CopyBase_IERC_Callbacks<I,U,P> extends CopyBase_InputCallback<P>,
                                                          CopyBase_ExecutorCallback<I,P>,
                                                          CopyBase_ReturnerCallback<P>,
                                                          CopyBase_CancelledCallback<P>{}








    private class CopyBase_InputCallbackWrapper<P> implements AsyncTaskParameterCallback<P> {
        protected CopyBase_InputCallback<P> mIn;
        public CopyBase_InputCallbackWrapper(CopyBase_InputCallback<P> in) {
            this.mIn = in;
        }
        @Override
        public P giveParameters() {
            if (mIn!=null) return mIn.CopyBase_InputCallback();
            return null;
        }
    }

    private class CopyBase_UpdaterCallbackWrapper<U> implements AsyncTaskUpdateCallback<U> {
        protected CopyBase_UpdaterCallback<U> mUp;
        public CopyBase_UpdaterCallbackWrapper(CopyBase_UpdaterCallback<U> in){
            this.mUp = in;
        }
        @Override
        public void broadcastProgress(U... out) {
            if (mUp!=null) mUp.CopyBase_UpdaterCallback(out);
        }
    }


    private class CopyBase_ExecutorCallbackWrapper<I,P> implements AsyncTaskExecutionCallback<I,P,P> {
        private CopyBase_ExecutorCallback<I,P> mExe;
        private AsyncTask<Object,Object,Object> refTask;

        public CopyBase_ExecutorCallbackWrapper(CopyBase_ExecutorCallback<I,P> in, AsyncTask ref){
            this.mExe = in;
            this.refTask = ref;
        }
        @Override
        public P executeTask(P args, I... varargs) {
            if (mExe !=null) return mExe.CopyBase_ExecutorCallback(args,this.refTask, varargs);
            return null;
        }
    }


    private class CopyBase_ReturnerCallbackWrapper<P> implements AsyncTaskReturnCallback<P> {
        protected CopyBase_ReturnerCallback<P> mRet;
        public CopyBase_ReturnerCallbackWrapper(CopyBase_ReturnerCallback<P> in){
            this.mRet = in;
        }
        @Override
        public void returnResult(P out) {
            if (mRet!=null) mRet.CopyBase_ReturnerCallback(out);
        }
    }

    private class CopyBase_CancelledCallbackWrapper<P> implements AsyncTaskCancelledCallBack {
        private CopyBase_CancelledCallback mCanc;
        private AsyncTask<Object,Object,Object> refTask;
        public CopyBase_CancelledCallbackWrapper(CopyBase_CancelledCallback<P> in, AsyncTask refTask) {
            this.mCanc = in;
            this.refTask = refTask;
        }
        @Override
        public void imSurpressed() {
            this.mCanc.CopyBase_CancelledCallback(this.refTask);
        }
    }


    public AT_CopyBase(CopyBase_InputCallback<P> input ,
                       CopyBase_ExecutorCallback<I,P> exe,
                       CopyBase_ReturnerCallback<P> returner){

        this.mIn = new CopyBase_InputCallbackWrapper<P>(input);
        this.mExe = new CopyBase_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mRet = new CopyBase_ReturnerCallbackWrapper<P>(returner);
    }


    public AT_CopyBase(CopyBase_InputCallback<P> input ,
                       CopyBase_UpdaterCallback<U> update  ,
                       CopyBase_ExecutorCallback<I,P> exe,
                       CopyBase_ReturnerCallback<P> returner){

        this.mIn = new CopyBase_InputCallbackWrapper<P>(input);
        this.mExe = new CopyBase_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mUp = new CopyBase_UpdaterCallbackWrapper<U>(update);
        this.mRet = new CopyBase_ReturnerCallbackWrapper<P>(returner);
    }


    public AT_CopyBase(CopyBase_InputCallback<P> input ,
                       CopyBase_UpdaterCallback<U> update  ,
                       CopyBase_ExecutorCallback<I,P> exe,
                       CopyBase_ReturnerCallback<P> returner,
                       CopyBase_CancelledCallback<P> canceller){

        this.mIn = new CopyBase_InputCallbackWrapper<P>(input);
        this.mExe = new CopyBase_ExecutorCallbackWrapper<I,P>(exe,this);
        this.mUp = new CopyBase_UpdaterCallbackWrapper<U>(update);
        this.mRet = new CopyBase_ReturnerCallbackWrapper<P>(returner);
        this.mCanc = new CopyBase_CancelledCallbackWrapper<P>(canceller,this);
    }





    public AT_CopyBase(){}









}
