package com.yqj.serviceedu.controller;


import com.yqj.commonutils.R;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.client.VodClient;
import com.yqj.serviceedu.entity.EduVideo;
import com.yqj.serviceedu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/serviceedu/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //增加小节信息
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //根据id查询小节信息
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

    //修改小节信息
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

    //删除小节信息
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        //获取视频id
        EduVideo eduVideo = videoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断不空则远程调用删除视频
        if (!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.deleteVideo(videoSourceId);
            if (result.getCode() == 20001){
                throw new MySystemException(20001,"熔断器执行..调用失败");
            }
        }
        videoService.removeById(videoId);
        return R.ok();
    }
}

