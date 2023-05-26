package com.mashibing.restClient.util;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * ES客户端工具类
 */
public class ESClientUtil {
    private static RestHighLevelClient HIGH_LEVEL_CLIENT = null;

    private ESClientUtil() {}

    public static RestHighLevelClient getESClientInstance() {
       if(HIGH_LEVEL_CLIENT == null) {
           synchronized (ESClientUtil.class) {
               if(HIGH_LEVEL_CLIENT == null) {
                   HIGH_LEVEL_CLIENT = RestHighLevelClientUtil.getRestHighLevelClient();
               }
           }
       }

       return HIGH_LEVEL_CLIENT;
    }
}
