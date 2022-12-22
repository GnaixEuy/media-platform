package cn.gnaixeuy.medialike.repository;

import cn.gnaixeuy.medialike.entity.Like;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@SpringBootTest
class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;
    
    @Test
    public void testFindAll() {
        List<Like> all = this.likeRepository.findAll();
        all.forEach(System.out::println);
    }

}