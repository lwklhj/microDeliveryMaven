package com.nyp.microdelivery.user;

/*@Named
@RequestScoped
public class MailBean {
    @Resource(name = "mail/gmail")
    private Session mailSession;

   public void sendEmail(){
        MIMEMessage message = new MIMEMessage(mailSession);
        try{
            message.setSubject("Example subject");
            message.setText("Example Text");
            message.setRecipient(RecipientType.TO,InternetAddress.parse(userAccount.getEmail(),
                            userAccount.toString()));

            Transport.send(message);
        } catch(MessagingException e) {
            e.printStackTrace();
    }
        }

}*/
