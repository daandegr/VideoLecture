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
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateUtil;


@Entity
@Table(name="VIDEOLECTURE")
public class VideoLecture implements Serializable {
    
    @Id
    @GeneratedValue
    private int LECTUREID;
    private int COURSEID;
    
    @OneToMany(mappedBy="myLecture")
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
    
    public void addMessage(LectureMessage msg){
        messages.add(msg);
    }
    
    public static void createLecture(Course course, User user){
        VideoLecture lecture = new VideoLecture();
        LectureMessage msg = new LectureMessage();

        lecture.setCOURSEID(course.getCOURSEID());
        msg.setMESSAGE("Welcome students! This lecture is about " + course.getCOURSENAME() + ", and your lecturer is: " + user.getFIRSTNAME() + user.getLASTNAME());
        msg.setUSERID(user.getUSERID());
        msg.setMyLecture(lecture);
        Date date = new Date();
        msg.setTS(new Timestamp(date.getTime()));

        lecture.addMessage(msg);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.save(lecture);
        tx.commit();
    }
    
    
}