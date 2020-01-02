package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.entity.Speaker;
import com.qf.entity.SpeakerQueryVo;
import com.qf.service.SpeakerService;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpeakerServiceimpl implements SpeakerService {
    @Autowired
    private SpeakerMapper speakerMapper;

    public Page<Speaker> findAllSpeakerBySpeakerQueryVo(SpeakerQueryVo queryVo) {

        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

        // 查询数据结果集
        List<Speaker> list = speakerMapper.findAllSpeaker(queryVo);


        // 查询到的数据总条数
        Integer total = speakerMapper.findAllSpeakerCount();

        // 封装返回的page对象
        Page<Speaker> speakerPage = new Page<Speaker>();
        //查询数据
        speakerPage.setRows(list);
        //当前页
        speakerPage.setPage(queryVo.getPage());
        //每页数据
        speakerPage.setSize(queryVo.getRows());
        //总记录数
        speakerPage.setTotal(total);

        return speakerPage;
    }

    public int findAllSpeakerCount() {
        return speakerMapper.findAllSpeakerCount();
    }

    public Speaker findSpeakerById(Integer id) {
        return speakerMapper.findSpeakerById(id);
    }

    public int updateSpeakerById(Speaker speaker) {
        return speakerMapper.updateSpeakerById(speaker);
    }

    public int delSpeakerById(Integer id) {
        return speakerMapper.delSpeakerById(id);
    }

    public int addSpeaker(Speaker speaker) {
        return speakerMapper.addSpeaker(speaker);
    }
}
