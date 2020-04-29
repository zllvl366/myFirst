package mall.cms.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zll on 2020/4/26.
 * Redis 的测试类
 */
public class RedisTest {

    /**
     *测试单节点
     */
    @Test
    public void testJedis(){
        // 创建Jedis连接、
        Jedis jedis = new Jedis("47.95.219.27",8101);
        // set
        jedis.set("test1","test111");
        // 取值
        String str = jedis.get("test1");
        System.out.println(str);
        //关闭链接
        jedis.close();
    }

    /**
     * 测试链接池
     */
    @Test
    public void testJedisPool(){
        // 创建链接池
        JedisPool pool = new JedisPool("47.95.219.27",8101);
        // 从连接池获取jedis对象
        Jedis jedis = pool.getResource();
        // 获取值
        String str = jedis.get("test1");
        System.out.println(str);
        // 关闭连接池
        jedis.close();
        // 关闭连接池
        pool.close();
    }

    /**
     * 测试redis集群
     */
    @Test
    public void testRedisCluster(){
        // 创建链接
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("47.95.219.27",8101));
        nodes.add(new HostAndPort("47.95.219.27",8102));
        nodes.add(new HostAndPort("47.95.219.27",8103));
        nodes.add(new HostAndPort("47.95.219.27",8104));
        nodes.add(new HostAndPort("47.95.219.27",8105));
        nodes.add(new HostAndPort("47.95.219.27",8106));
        // 创建集群对象
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("test","ffff");
        String str = cluster.get("test");
        System.out.println(str);
        // 关闭集群
        cluster.close();
    }

    /**
     * 整合spring 连接池测试
     */
    @Test
    public  void testJedisPoolS(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();
        jedis.set("test","test111");
        String str = jedis.get("test");
        System.out.println(str);
        jedis.close();
    }

    /**
     * 整合Spring cluster测试
     */
    @Test
    public void testJedisClusterS(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisCluster jedisCluster = (JedisCluster) context.getBean("jedisCluster");
        jedisCluster.hdel("content","117");
        String str = jedisCluster.hget("content", "117");
        System.out.println(str);
    }
}
