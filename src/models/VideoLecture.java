package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import service.HibernateUtil;


@Entity
@Table(name="VIDEOLECTURE")
public class VideoLecture implements Serializable {
    
    @Id
    @GeneratedValue
    private int LECTUREID;
    private int COURSEID;
    
    @OneToMany(mappedBy="LECTUREID")
    private List<LectureMessage> messages;

    public VideoLecture(int LECTUREID, int COURSEID, List<LectureMessage> messages) {
        this.LECTUREID = LECTUREID;
        this.COURSEID = COURSEID;
        this.messages = messages;
    }

    public VideoLecture() {
    }

    public int getLECTUREID() {
        return LECTUREID;
    }

    public void setLECTUREID(int LECTUREID) {
        this.LECTUREID = LECTUREID;
    }

    public int getCOURSEID() {
        return COURSEID;
    }

    public void setCOURSEID(int COURSEID) {
        this.COURSEID = COURSEID;
    }

    public List<LectureMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LectureMessage> messages) {
        this.messages = messages;
    }
    
    public static VideoLecture getLecture(int courseId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(VideoLecture.class).add(Restrictions.eq("COURSEID", courseId));
        
        List<VideoLecture> lectures = criteria.list();
        
        return lectures.get(lectures.size()-1);        
    }
    
    public static void createLecture(Course course, User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        VideoLecture lecture = new VideoLecture();
        lecture.setCOURSEID(course.getCOURSEID());
        session.save(lecture);
        tx.commit();
        
        
        LectureMessage msg = new LectureMessage();

        msg.setMESSAGE("Welcome students! This lecture is about " + course.getCOURSENAME() + ", and your lecturer is: " + user.getFIRSTNAME() +" "+ user.getLASTNAME());
        msg.setUSERID(user.getUSERID());
        msg.setLECTUREID(getLecture(Course.getActiveCourse().getCOURSEID()).getLECTUREID());
        Date date = new Date();
        msg.setTS(new Timestamp(date.getTime()));

        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx2 = session2.beginTransaction();
        session2.save(msg);
        tx2.commit();
    }
    
    
}