package com.integralads.avid.library.inmobi.walking;

import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTask.StateProvider;
import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTaskQueue;
import com.integralads.avid.library.inmobi.walking.async.AvidCleanupAsyncTask;
import com.integralads.avid.library.inmobi.walking.async.AvidEmptyPublishAsyncTask;
import com.integralads.avid.library.inmobi.walking.async.AvidPublishAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidStatePublisher implements StateProvider {
    private final AvidAdSessionRegistry adSessionRegistry;
    private JSONObject previousState;
    private final AvidAsyncTaskQueue taskQueue;

    public AvidStatePublisher(AvidAdSessionRegistry avidAdSessionRegistry, AvidAsyncTaskQueue avidAsyncTaskQueue) {
        this.adSessionRegistry = avidAdSessionRegistry;
        this.taskQueue = avidAsyncTaskQueue;
    }

    public void publishState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        AvidAsyncTaskQueue avidAsyncTaskQueue = this.taskQueue;
        AvidPublishAsyncTask avidPublishAsyncTask = new AvidPublishAsyncTask(this, this.adSessionRegistry, hashSet, jSONObject, d);
        avidAsyncTaskQueue.submitTask(avidPublishAsyncTask);
    }

    public void publishEmptyState(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        AvidAsyncTaskQueue avidAsyncTaskQueue = this.taskQueue;
        AvidEmptyPublishAsyncTask avidEmptyPublishAsyncTask = new AvidEmptyPublishAsyncTask(this, this.adSessionRegistry, hashSet, jSONObject, d);
        avidAsyncTaskQueue.submitTask(avidEmptyPublishAsyncTask);
    }

    public void cleanupCache() {
        this.taskQueue.submitTask(new AvidCleanupAsyncTask(this));
    }

    public JSONObject getPreviousState() {
        return this.previousState;
    }

    public void setPreviousState(JSONObject jSONObject) {
        this.previousState = jSONObject;
    }
}
