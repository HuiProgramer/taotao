package com.taotao.test.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    //测试单机版
    @Test
    public void TestJedis(){
        //创建Jedis对象 需要指定 链接的地址和端口
        Jedis jedis = new Jedis("192.168.178.98",6379);
        //直接操作redis set
        jedis.set("name","tom");
        System.out.println(jedis.get("name"));
        //关闭Jedis
        jedis.close();
    }

    @Test
    public void TestJedisPool(){
        //创建jedispool对象 需要指定 链接的地址和端口
        JedisPool jedisPool = new JedisPool("192.168.178.98",6379);
        //获取jedis的对象
        Jedis jedis = jedisPool.getResource();
        //直接操作redis
        jedis.set("keypool","keypool");
        System.out.println(jedis.get("keypool"));
        //关闭Jedis(释放资源到连接池)
        jedis.close();
        //关闭JedisPool(应用关闭时才关闭)
        jedisPool.close();
    }

    //测试集群版
    @Test
    public void TestJedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.178.88",7001));
        nodes.add(new HostAndPort("192.168.178.88",7002));
        nodes.add(new HostAndPort("192.168.178.88",7003));
        nodes.add(new HostAndPort("192.168.178.88",7004));
        nodes.add(new HostAndPort("192.168.178.88",7005));
        nodes.add(new HostAndPort("192.168.178.88",7006));
        //创建jediscluster对象
        JedisCluster cluster = new JedisCluster(nodes);
        //直接根据jediscluster对象操作set方法
        cluster.set("keycluster","cluster的value");
        System.out.println(cluster.get("keycluster"));
        //关闭jediscluster对象（是在应用系统关闭的时候关闭）封装了连接池
        cluster.close();
    }
}
