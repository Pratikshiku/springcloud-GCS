package top.ptcc9.utils;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * @author: HE LONG CAN
 * @description: 一个靓仔写的OSS工具类
 * @date: 2021-01-20 21:17
 */
public class OssUtil {
    /**
     * 访问链接根目录
     */
    private static final String ACCESS_LINK = "http://gcs.ptcc9.top/";

    private static final String END_POINT = "http://oss-cn-beijing.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAI4FzzE41b8eZY7ESUTv13";
    private static final String ACCESS_KEY_SECRET = "AHyKGtPy5eB2jv3kQedd2BDS3LJuqM";

    private static final String BUCKET_NAME = "springboot-gcs";
    private static final String ROOT_PATH = "order-pic/";


    /**
     * 创建链接对象
     * @return
     */
    private static OSS createOssObject() {
        return new OSSClientBuilder().build(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }


    /**
     * 获取随机 UUID
     * @return
     */
    private static String randomUUID() {
        return IdUtil.simpleUUID();
    }


    /**
     * 通过 inputStream 上传文件
     * @param inputStream
     * @param path 路径不包括文件名
     * @return
     */
    public static String uploadFile(InputStream inputStream, String path) {
        OSS ossObject = createOssObject();
        //生成最终路径
        String finalPath = ROOT_PATH + path + randomUUID() + ".png";
        //添加文件
        ossObject.putObject(BUCKET_NAME,finalPath,inputStream);
        ossObject.shutdown();
        return ACCESS_LINK + finalPath;
    }
}
