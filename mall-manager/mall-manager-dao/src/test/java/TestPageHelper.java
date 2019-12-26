import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.manager.mapper.TbItemMapper;
import com.mall.manager.pojo.TbItem;
import com.mall.manager.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by zll on 2019/12/24.
 */
public class TestPageHelper {

    /**
     * 测试分页
     */
    @Test
    public void testPageHelper(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper tbItemMapper = (TbItemMapper) ac.getBean("tbItemMapper");
        //设置分页信息
        PageHelper.startPage(1,30);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);
        //获取分页信息
        PageInfo<TbItem>  pageInfo = new PageInfo<TbItem>(list);
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println(list.size());
    }
}
