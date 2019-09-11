package com.lxk.vertx.elastic.constants;

/**
 * 模块使用的常量
 *
 * @author LiXuekai on 2019/7/4
 */
public interface ConfConstant {
    String MQ_ES_ENTITY = "entity";
    String SCROLL_ID_P = "scroll_id";
    String SCROLL_ID = "_scroll_id";
    String SCROLL = "scroll";
    String MQ_ES_ENDPOINT = "endpoint";
    String GET = "GET";
    String DELETE = "DELETE";
    String PUT = "PUT";
    String HITS = "hits";
    String ID = "_id";
    String SOURCE = "_source";
    String SPLIT = "/";
    String SEARCH = "/_search";
    String MESSAGE = "/message";
    String DELETE_BY_QUERY = "/_delete_by_query";
    String CONNS = "conns";
    String NODES = "nodes";
    String STREAMID = "streamId";
    String ENTRY_NODE = "entryNode";
    String SOURCE_APP_STREAM_IDS = "sourceAppStreamIds";
    String DEST_APP_STREAM_IDS = "destAppStreamIds";
    String NODE_TYPE = "type";
    String REFRESH_OK = "refresh ok";
    String PUBLISHED = "PUBLISHED";

}
