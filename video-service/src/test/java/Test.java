import com.qf.entity.Speaker;
import com.qf.entity.SpeakerQueryVo;
import com.qf.service.SpeakerService;
import com.qf.util.Page;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpeakerService speakerService = (SpeakerService)context.getBean("speakerServiceimpl");
   /*    Speaker speakerById = speakerService.findSpeakerById(14);
        System.out.println(speakerById.toString());*/
        SpeakerQueryVo speakerQueryVo = new SpeakerQueryVo();
        Page<Speaker> allSpeakerBySpeakerQueryVo = speakerService.findAllSpeakerBySpeakerQueryVo(speakerQueryVo);
        System.out.println(allSpeakerBySpeakerQueryVo);


    }
}
