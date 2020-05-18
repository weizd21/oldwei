package top.oldwei.netty.server.controller;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.netty.common.domain.FileInfo;
import top.oldwei.netty.server.domain.TransferFileInfo;
import top.oldwei.netty.server.util.CacheUtil;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@RequestMapping("/netty-server")
@RestController
@Slf4j
public class NettyServerController {

    @RequestMapping("/get-cache")
    public Map<String, TransferFileInfo> getCache(){
        return CacheUtil.fileInfoCache;
    }










}
