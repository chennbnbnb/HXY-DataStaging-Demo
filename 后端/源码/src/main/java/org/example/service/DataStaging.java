package org.example.service;

import java.util.Map;

/**
 * 数据暂存服务需要提供的接口
 */
public interface DataStaging {
    /**
     * 删除给定用户暂存的数据
     * @param id 用户的identify
     * @return 删除成功返回true, 如果id没有暂存的数据, 则返回false
     */
    boolean delete(String id);

    /**
     * 更新给定用户暂存的数据
     * @param id 用户的identify
     * @param maps 要更新的KV对集合, 一个KV对有三种情况:
     *             1. 如果key已经存在, 则会进行更新, 覆盖旧的value
     *             2. 如果key不存在, 则会插入新的KV对
     * @return 返回是否插入成功
     */
    boolean update(String id, Map<String, String> maps);

    /**
     * 查询给定用户暂存的数据
     * @param id 用户的identify
     * @return 返回该用户暂存的数据
     */
    Map<String, String> search(String id);
}
