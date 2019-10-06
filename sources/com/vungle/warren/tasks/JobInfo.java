package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JobInfo implements Cloneable {
    private static final String TAG = "JobInfo";
    private long delay = 0;
    private Bundle extras = new Bundle();
    private long nextRescheduleTimeout;
    private int priority = 2;
    private int reschedulePolicy = 1;
    private long rescheduleTimeout;
    private final String tag;
    private boolean updateCurrent;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
        public static final int CRITICAL = 5;
        public static final int HIGH = 3;
        public static final int HIGHEST = 4;
        public static final int LOW = 1;
        public static final int LOWEST = 0;
        public static final int NORMAL = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReschedulePolicy {
        public static final int EXPONENTIAL = 1;
        public static final int LINEAR = 0;
    }

    public JobInfo(String tag2) {
        this.tag = tag2;
    }

    public JobInfo setUpdateCurrent(boolean updateCurrent2) {
        this.updateCurrent = updateCurrent2;
        return this;
    }

    public JobInfo setExtras(Bundle extras2) {
        if (extras2 != null) {
            this.extras = extras2;
        }
        return this;
    }

    public JobInfo setDelay(long delay2) {
        this.delay = delay2;
        return this;
    }

    public JobInfo setReschedulePolicy(long rescheduleTimeout2, int delayCriteria) {
        this.rescheduleTimeout = rescheduleTimeout2;
        this.reschedulePolicy = delayCriteria;
        return this;
    }

    public JobInfo setPriority(int priority2) {
        this.priority = priority2;
        return this;
    }

    public String getJobTag() {
        return this.tag;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public long getDelay() {
        return this.delay;
    }

    public long makeNextRescedule() {
        long j = this.rescheduleTimeout;
        if (j == 0) {
            return 0;
        }
        long j2 = this.nextRescheduleTimeout;
        if (j2 == 0) {
            this.nextRescheduleTimeout = j;
        } else if (this.reschedulePolicy == 1) {
            this.nextRescheduleTimeout = j2 * 2;
        }
        return this.nextRescheduleTimeout;
    }

    public boolean getUpdateCurrent() {
        return this.updateCurrent;
    }

    public int getPriority() {
        return this.priority;
    }

    public JobInfo copy() {
        try {
            return (JobInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }
}
