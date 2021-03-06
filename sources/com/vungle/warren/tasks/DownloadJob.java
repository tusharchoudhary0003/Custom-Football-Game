package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Storage;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Placement;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DownloadJob implements Job {
    private static final long DEFAUT_DELAY = 5000;
    private static final String PLACEMENT_KEY = "placement";
    static final String TAG = DownloadJob.class.getCanonicalName();
    private Placement placement;
    /* access modifiers changed from: private */
    public int result;
    private final Storage storage;

    public DownloadJob(Storage storage2) {
        this.storage = storage2;
    }

    public static JobInfo makeJobInfo(String placementID, boolean retry) {
        Bundle extras = new Bundle();
        extras.putString("placement", placementID);
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(" ");
        sb.append(placementID);
        return new JobInfo(sb.toString()).setUpdateCurrent(true).setExtras(extras).setReschedulePolicy(retry ? DEFAUT_DELAY : -1, 1).setPriority(4);
    }

    public int onRunJob(Bundle params, JobRunner jobRunner) {
        String placementID = params.getString("placement", null);
        Collection<String> validPlacements = this.storage.getValidPlacements();
        if (placementID == null || !validPlacements.contains(placementID)) {
            return 1;
        }
        this.placement = (Placement) this.storage.load(placementID, Placement.class);
        if (this.placement == null) {
            return 1;
        }
        final CountDownLatch latch = new CountDownLatch(1);
        Vungle.loadAd(placementID, new LoadAdCallback() {
            public void onAdLoad(String id) {
                DownloadJob.this.result = 0;
                latch.countDown();
            }

            public void onError(String id, Throwable cause) {
                if (cause instanceof VungleException) {
                    VungleException vungleException = (VungleException) cause;
                    String str = DownloadJob.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("scheduleJob: loadAd onError: ");
                    sb.append(vungleException.getExceptionCode());
                    Log.e(str, sb.toString());
                    if (vungleException.getExceptionCode() == 8 || vungleException.getExceptionCode() == 1 || vungleException.getExceptionCode() == 14) {
                        DownloadJob.this.result = 1;
                    } else {
                        DownloadJob.this.result = 2;
                    }
                } else {
                    DownloadJob.this.result = 2;
                }
                latch.countDown();
            }
        });
        try {
            if (latch.await(1, TimeUnit.MINUTES)) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("scheduleJob: latch await");
                sb.append(this.result);
                Log.d(str, sb.toString());
                return this.result;
            }
            Log.d(TAG, "scheduleJob: latch await else 2");
            return 2;
        } catch (InterruptedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return 1;
        }
    }
}
