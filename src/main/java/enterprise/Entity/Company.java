package enterprise.Entity;


import com.nyp.microdelivery.posting.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Named
@Entity
@Table(name="enterprise")
@SessionScoped
public class Company implements Serializable{
    @Id
    private String loginName;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "addr")
    private String addr;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "postalCode")
    private String postalCode;
    public Company(){}



    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Company getCompanyDetail(){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        Company c=null;
        try{
            tx=session.beginTransaction();

            Criteria criteria=session.createCriteria(Company.class).add(Restrictions.eq("loginName",loginName));
            c=(Company)criteria.uniqueResult();
            tx.commit();


        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
        return c;

    }

    @Override
    public String toString() {
        return companyName+"\t"+passwd;
    }
}
