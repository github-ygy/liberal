package com.ygy.liberal.search.elastic;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guoyao
 * @date 2020-08-02
 */
public class TestElastic {
   private static final TransportClient client = createClient();


    public static void main(String[] args) throws Exception {
        testIndex();


    }

    private static void testIndex() throws Exception {
        IndicesAdminClient indices = client.admin().indices();
        //删除索引
        DeleteIndexResponse deleteIndexResponse = indices.prepareDelete("test_index").get();
        System.out.println(deleteIndexResponse.isAcknowledged());
        //判断索引是否存在
        IndicesExistsResponse existsResponse = indices.prepareExists("test_index").get();
        System.out.println(existsResponse.isExists());

        //创建索引
        CreateIndexResponse createIndexResponse = indices.prepareCreate("test_index").get();
        System.out.println(createIndexResponse.isAcknowledged());

        //判断索引是否存在
        IndicesExistsRequest existsRequest = new IndicesExistsRequest("test_index");
        IndicesExistsResponse existsResponse1 = indices.exists(existsRequest).get();
        System.out.println(existsResponse1.isExists());

        //设置mapping
        Map<String, Object> mappingMap = new HashMap<>();
        Map<String, Object> propertiesMap = new HashMap<>();
        Map<String, String> titleFieldMap = new HashMap<>();
        titleFieldMap.put("type", "keyword");
        propertiesMap.put("title", titleFieldMap);
        mappingMap.put("properties", propertiesMap);
        PutMappingRequest mappingRequest = new PutMappingRequest("test_index").type("index").source(mappingMap);
        PutMappingResponse mappingResponse = indices.putMapping(mappingRequest).get();
        System.out.println(mappingResponse.isAcknowledged());

    }


    private static TransportClient createClient()  {
        try {
            return new PreBuiltTransportClient(getSettings())
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (Exception ex) {
        }
        return null;
    }

    private static Settings getSettings() {
        return Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false)
                .build();
    }

}
