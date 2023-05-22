package com.mashibing.remoteDict;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 远程词典
 */
@RestController
@RequestMapping("remote")
public class RemoteDictController {
    @GetMapping("/dict/{num}")
    @ResponseBody
    public void getDict(@PathVariable("num") Integer i, HttpServletResponse response) {
        String path = i == 1 ? "/Users/zhangzhiwang/Desktop/zhangzhiwang_extends.txt" : "/Users/zhangzhiwang/Desktop/zhangzhiwang_stopwords.txt";
        try (FileInputStream in = new FileInputStream(path);
             ServletOutputStream out = response.getOutputStream();) {
            int length = in.available();
            byte[] bs = new byte[length];
            in.read(bs, 0, length);
            response.setContentType("text/plain;charset=utf-8");
            response.setHeader("Last-Modified", String.valueOf(System.currentTimeMillis()));
            response.setHeader("ETag", String.valueOf(length));
            out.write(bs);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
