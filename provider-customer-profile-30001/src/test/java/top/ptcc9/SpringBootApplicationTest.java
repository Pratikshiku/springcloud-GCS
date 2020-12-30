package top.ptcc9;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.ptcc9.utils.RedisUtil;

import javax.annotation.Resource;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-12-28 16:54
 */
@SpringBootTest
public class SpringBootApplicationTest {
    @Resource
    RedisUtil redisUtil;

    @Test
    void Test01() {
        redisUtil.set("1","1",30);
    }
}
