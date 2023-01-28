package org.example.entity;

import java.util.Map;

/**
 * 表示前端发送过来的请求
 */
public class StagingRequest {
    public String id;   // 用户id
    public Map<String, String> data;    // KV对集合
}
