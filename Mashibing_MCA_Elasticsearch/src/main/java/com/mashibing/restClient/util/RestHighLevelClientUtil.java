package com.mashibing.restClient.util;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;

public class RestHighLevelClientUtil {
    private static RestHighLevelClient restHighLevelClient;
    private RestHighLevelClientUtil() {}

    public static RestHighLevelClient getRestHighLevelClient() {// 这里只是样例，可以写成单例
        restHighLevelClient =  new RestHighLevelClient(RestClientUtil.getRestClientBuilder());

        // 加入嗅探机制
        Sniffer sniffer = Sniffer.builder(restHighLevelClient.getLowLevelClient())
//                .setSniffIntervalMillis(1000)
                .setSniffAfterFailureDelayMillis(30000)
                .build();
        SniffOnFailureListener failureListener = RestClientUtil.failureListener;
        if(failureListener != null) {
            failureListener.setSniffer(sniffer);
        }

        return restHighLevelClient;
    }

    public static void closeHighLevelClient() {
        try {
            if(restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
