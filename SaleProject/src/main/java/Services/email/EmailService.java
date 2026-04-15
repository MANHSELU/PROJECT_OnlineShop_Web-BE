package Services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("no-reply@yourdomain.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent =
                "<div style='font-family:Arial,sans-serif;max-width:500px;margin:auto;padding:30px;border:1px solid #e0e0e0;border-radius:10px;'>" +
                        "<h2 style='color:#333;text-align:center;'>Xác thực tài khoản</h2>" +
                        "<p style='color:#555;text-align:center;'>Nhấn vào nút bên dưới để xác thực tài khoản của bạn:</p>" +
                        "<div style='text-align:center;margin:30px 0;'>" +
                        "<a href='" + content + "' " +
                        "target='_blank' " +
                        "rel='noopener noreferrer' " +
                        "style='padding:12px 30px;background:#4CAF50;color:white;text-decoration:none;border-radius:6px;font-size:16px;font-weight:bold;display:inline-block;'>" +
                        "✅ Xác thực tài khoản</a>" +
                        "</div>" +
                        "<p style='color:#999;font-size:13px;text-align:center;'>Hoặc copy link này vào trình duyệt:<br>" +
                        "<a href='" + content + "' style='color:#4CAF50;'>" + content + "</a></p>" +
                        "<p style='color:#999;font-size:12px;text-align:center;'>Link sẽ hết hạn sau 30 phút.</p>" +
                        "<p style='color:#999;font-size:12px;text-align:center;'>Nếu bạn không đăng ký tài khoản, vui lòng bỏ qua email này.</p>" +
                        "</div>";

        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}