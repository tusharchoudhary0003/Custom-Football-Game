package com.unity3d.services.core.request;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancelableThreadPoolExecutor extends ThreadPoolExecutor {
    private final List<Runnable> _activeRunnable = new LinkedList();

    public CancelableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, LinkedBlockingQueue<Runnable> queue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue);
    }

    /* access modifiers changed from: protected */
    public synchronized void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        this._activeRunnable.add(r);
    }

    /* access modifiers changed from: protected */
    public synchronized void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        this._activeRunnable.remove(r);
    }

    public synchronized void cancel() {
        for (Runnable r : this._activeRunnable) {
            if (r instanceof WebRequestRunnable) {
                ((WebRequestRunnable) r).setCancelStatus(true);
            }
        }
    }
}
