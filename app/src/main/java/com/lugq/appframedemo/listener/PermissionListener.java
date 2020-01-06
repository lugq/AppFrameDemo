package com.lugq.appframedemo.listener;

import java.util.List;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/7/3 14:16
 */
public interface PermissionListener {
    /**
     * 已获取-权限
     */
    void onGranted();

    /**
     * 未获取-权限
     */
    void onDenied(List<String> deniedPermissions);
}
