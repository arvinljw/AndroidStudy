package net.arvin.androidstudy.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by arvinljw on 2018/7/9 19:43
 * Function：
 * Desc：
 */
public class WeakHandler extends Handler {
    private WeakReference<IWeakHandler> handler;

    public WeakHandler(IWeakHandler handler) {
        this.handler = new WeakReference<>(handler);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (handler != null && handler.get() != null) {
            handler.get().handlerMessage(msg);
        }
    }

}
