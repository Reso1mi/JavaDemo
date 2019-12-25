package advance_thread_study.chapter11_threadLocal.Shizhan;

/**
 * 获取上下文
 * 单例
 */
public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        //和线程绑定
        return threadLocal.get();
    }

    private ActionContext(){

    }
}