import com.qf.dao.CourseMapper;
import com.qf.dao.SpeakerMapper;
import com.qf.dao.UserMapper;
import com.qf.dao.VideoMapper;
import com.qf.entity.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    @org.junit.Test
    public void test(){
        SpeakerQueryVo speakerQueryVo = new SpeakerQueryVo();
        speakerQueryVo.setStart(0);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpeakerMapper speakerMapper = (SpeakerMapper)context.getBean("speakerMapper");
        //int allSpeakerCount = speakerMapper.findAllSpeakerCount();
        //System.out.println(allSpeakerCount);
        List<Speaker> allSpeaker = speakerMapper.findAllSpeaker(speakerQueryVo);
        System.out.println(allSpeaker.toString());
//        Speaker speakerById = speakerMapper.findSpeakerById(14);
//        System.out.println(speakerById.toString());
    }

    @org.junit.Test
    public void test1(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpeakerMapper speakerMapper = (SpeakerMapper)context.getBean("speakerMapper");

        Speaker s= new Speaker();
        s.setSpeaker_desc("李思思1");
        s.setSpeaker_name("李思思1");
        s.setSpeaker_job("教授");
        System.out.println(s.toString());
        int i = speakerMapper.addSpeaker(s);
        System.out.println(i);
    }

    @org.junit.Test
    public void test2(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpeakerMapper speakerMapper = (SpeakerMapper)context.getBean("speakerMapper");

        Speaker s= new Speaker();
        s.setId(17);
        s.setSpeaker_desc("李思思2");
        s.setSpeaker_name("李思思2");
        s.setSpeaker_job("教授");
        System.out.println(s.toString());
        int i = speakerMapper.updateSpeakerById(s);
        System.out.println(i);
    }

    @org.junit.Test
    public void test3(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoMapper videoMapper = (VideoMapper)context.getBean("videoMapper");
        Video video = new Video();
        video.setDetail("afjibhreuivabnvis癌变是数据库");
        video.setId(260);

        /*int i = videoMapper.updateVideoById(video);
        System.out.println(i);*/

        int i = videoMapper.delVideoById(260);
        System.out.println(i);


    }


    @org.junit.Test
    public void test4(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CourseMapper courseMapper = (CourseMapper)context.getBean("courseMapper");
        Subject subjectList = courseMapper.selCourseAndVideoBySubjectId(1);

        System.out.println(subjectList.toString());

    }

    @org.junit.Test
    public void test5(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper)context.getBean("userMapper");
        User user = userMapper.getAllUser("222@qq.com", "111");

        System.out.println(user.toString());

    }

    @org.junit.Test
    public void test6(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper)context.getBean("userMapper");
        User user = new User();
        user.setEmail("35688@qq.com");
        user.setPassword("111");
        int i = userMapper.addUser(user);

        System.out.println(i);

    }

    @org.junit.Test
    public void test7(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoMapper videoMapper = (VideoMapper)context.getBean("videoMapper");
        Video video = videoMapper.showVideo(220);
        System.out.println(video.getSpeaker());
        System.out.println(video.toString());


    }


    @org.junit.Test
    public void test8(){


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper)context.getBean("userMapper");
        User user = new User();
        user.setNickName("李思思");
       // user.setPassword("123456");
       //user.setSex("man");
       //user.setBirthday("1996-10-11");
       //user.setAddress("郑州");
        user.setEmail("112233445566@qq.com");
        //user.setEmail("35688@qq.com");
        user.setPassword("111");
        int i = userMapper.updateUserByEmail(user);

        System.out.println(i);

    }


}
