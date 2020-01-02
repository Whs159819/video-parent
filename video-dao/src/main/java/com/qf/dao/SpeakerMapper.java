package com.qf.dao;

import com.qf.entity.Speaker;
import com.qf.entity.SpeakerQueryVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpeakerMapper {


    public List<Speaker> findAllSpeaker(SpeakerQueryVo speakerQueryVo);

    public int findAllSpeakerCount();

    public Speaker findSpeakerById(Integer id);

    public int updateSpeakerById(Speaker speaker);

    public int delSpeakerById(Integer id);

    public int addSpeaker(Speaker speaker);


}
