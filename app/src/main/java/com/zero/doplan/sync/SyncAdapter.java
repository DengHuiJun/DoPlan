package com.zero.doplan.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * Created by Allen.D on 17/2/25.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        this(context, autoInitialize, false);

    }

    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        // TODO 处理同步，与服务器交互，下载，上传数据等，此方法异步执行的
    }
}
