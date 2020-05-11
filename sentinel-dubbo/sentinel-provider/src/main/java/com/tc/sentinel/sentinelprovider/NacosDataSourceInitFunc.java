package com.tc.sentinel.sentinelprovider;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * 从nacos上去获得动态的限流规则
 */
public class NacosDataSourceInitFunc implements InitFunc {

    //token-server的地址
    private final String CLUSTER_SERVER_HOST="localhost";
    private final int CLUSTER_SERVER_PORT=9999;
    //请求超时时间
    private final int REQUEST_TIME_OUT=200000;

    //namespace
    private final String APP_NAME="App-Tc";

    //nacos的配置()
    private final String remoteAddress="192.168.12.101"; //nacos 配置中心的服务host
    private final String groupId="SENTINEL_GROUP";
    private final String FLOW_POSTFIX="-flow-rules"; //dataid（names+postfix）

    /**意味着当前的token-server会从nacos上获得限流的规则*/
    @Override
    public void init() throws Exception {
         //加载集群-信息
        loadClusterClientConfig();

        registryClusterFlowRuleProperty();
    }

    private void loadClusterClientConfig(){
        ClusterClientAssignConfig assignConfig=new ClusterClientAssignConfig();
        assignConfig.setServerHost(CLUSTER_SERVER_HOST);
        assignConfig.setServerPort(CLUSTER_SERVER_PORT);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        ClusterClientConfig clientConfig=new ClusterClientConfig();
        clientConfig.setRequestTimeout(REQUEST_TIME_OUT);
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    /**注册动态数据源*/
    /**
     * [
     *     {
     *         "resource":"com.gupao.sentinel.sentinelService:sayHello(java.lang.String)",
     *         "grade":1,       限流模式：1-qps
     *         "count":10,      限流阈值
     *         "clusterMode":true,     是否集群模式
     *         "clusterConfig":{
     *             "flowId":111222,        全局唯一id
     *             "thresholdType":1,            阈值模式：1-全局
     *             "fallbackToLocalWhenFail":true        client通信失败  是否使用本地
     *         }
     *     }
     * ]
     */
    private void registryClusterFlowRuleProperty(){
        ReadableDataSource<String, List<FlowRule>> rds=
                new NacosDataSource<>(remoteAddress, groupId, APP_NAME + FLOW_POSTFIX,
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                        }));
        FlowRuleManager.register2Property(rds.getProperty());
    }

}
