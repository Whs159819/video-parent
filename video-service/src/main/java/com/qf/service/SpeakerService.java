package com.qf.service;

import com.qf.entity.Speaker;
import com.qf.entity.SpeakerQueryVo;
import com.qf.util.Page;

public interface SpeakerService {

    public Page<Speaker> findAllSpeakerBySpeakerQueryVo(SpeakerQueryVo speakerQueryVo);

  /*  public int findAllSpeakerCount();*/

    public Speaker findSpeakerById(Integer id);

    public int updateSpeakerById(Speaker speaker);

    public int delSpeakerById(Integer id);

    public int addSpeaker(Speaker speaker);


}
