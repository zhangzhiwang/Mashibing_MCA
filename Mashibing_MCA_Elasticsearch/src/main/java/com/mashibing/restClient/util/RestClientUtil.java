package com.mashibing.restClient.util;

import lombok.Data;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;

@Data
public class RestClientUtil {
//    private static final String ADDRESS = "localhost:9201,localhost:9202";// 可以写到配置文件里，这里只是模拟
    private static final String ADDRESS = "localhost:9200";// 可以写到配置文件里，这里只是模拟
    private static final String SCHEME = "http";
    public static SniffOnFailureListener failureListener = null;

    private RestClientUtil() {
    }

    public static RestClientBuilder getRestClientBuilder() {
        String[] hosts = ADDRESS.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            String[] ipPort = hosts[i].split(":");
            httpHosts[i] = new HttpHost(ipPort[0], Integer.parseInt(ipPort[1]), SCHEME);
        }

        failureListener = new SniffOnFailureListener();
        RestClientBuilder builder = RestClient.builder(httpHosts).setFailureListener(failureListener);

        // 可以根据实际需要，通过low-level client对连接做一些设置，可以参考官方文档：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.12/java-rest-low-usage-initialization.html
        // 设置请求头，避免每个请求都必须指定
//        Header[] defaultHeaders = new Header[]{
//                new BasicHeader("header", "application/json")
//        };
//        builder.setDefaultHeaders(defaultHeaders);

        // 设置每次节点发生故障的时候收到通知的监听器。内部嗅探到故障的时候 被调用
//        builder.setFailureListener(new RestClient.FailureListener() {
//            public void onFailure(Node node) {
//                super.onFailure(node);
//            }
//        });

        // 设置修改默认请求配置的回调 比如：请求超时，认证
//        builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(10000));

        // 设置客户端配置的回调：ssl 加密通讯等
        //        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(
//                    HttpAsyncClientBuilder httpClientBuilder) {
//                return httpClientBuilder.setProxy(
//                        new HttpHost("proxy", 9000, "http"));
//            }
//        });

        // 跳过向 master only 节点发送请求
        // master:true
        // data:false
        // ingest；false
//        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        // 身份认证
        //        final CredentialsProvider credentialsProvider =
//                new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "elaser"));
//
//
//        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
//                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                //线程设置
//                httpAsyncClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(10).build());
//                return httpAsyncClientBuilder;
//            }
//        });

        return builder;
    }
}
