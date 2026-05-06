package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

    @Entity
    public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private int user_id;
        @Column(name = "user_name")
        private String user_name;
        @Column(name = "email")
        private String email;
        @Column(name = "password")
        private String password;
        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        // ===> Khi sử dụng enum thì nó sẽ luôn ở dạng số nguyên  theo thứ tự ví dụ guest đứng đầu thi lưu số 0,...
        private Role role;          // ===> Nên phải dùng @Enumerated(EnumType.STRING) để đổi lại dạng String
        @Column(name = "is_account_enabled")
        private boolean is_account_enabled;
        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        private Status status;
        @Column(name = "token")
        private String token; // ===> token cho xác nhận đăng kí
        @Column(name = "token_created_at")
        private LocalDateTime token_created_at;
        @Column(name = "token_expired_at")
        private LocalDateTime token_expired_at;
        @Column(name = "phone")
        private String phone;
        @Column(name = "address")
        private String address;
        @Column(name = "otp")
        private String otp;
        @Column(name = "otp_craeted_at")
        private LocalDateTime otp_created_at;
        @Column(name = "otp_expired_at")
        private LocalDateTime otp_expired_at;

        public enum Role {
            GUEST, MEMBER, ADMIN
        }
        public enum Status{
            ACTIVE,BANNED
        }

        public Users(int user_id, String user_name, String email, String password, Role role, boolean is_account_enabled, Status status, String token, LocalDateTime token_created_at, LocalDateTime token_expired_at, String phone, String address, String otp, LocalDateTime otp_created_at, LocalDateTime otp_expired_at) {
            this.user_id = user_id;
            this.user_name = user_name;
            this.email = email;
            this.password = password;
            this.role = role;
            this.is_account_enabled = is_account_enabled;
            this.status = status;
            this.token = token;
            this.token_created_at = token_created_at;
            this.token_expired_at = token_expired_at;
            this.phone = phone;
            this.address = address;
            this.otp = otp;
            this.otp_created_at = otp_created_at;
            this.otp_expired_at = otp_expired_at;
        }

        public Users() {
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public LocalDateTime getOtp_created_at() {
            return otp_created_at;
        }

        public void setOtp_created_at(LocalDateTime otp_created_at) {
            this.otp_created_at = otp_created_at;
        }

        public LocalDateTime getOtp_expired_at() {
            return otp_expired_at;
        }

        public void setOtp_expired_at(LocalDateTime otp_expired_at) {
            this.otp_expired_at = otp_expired_at;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public boolean isIs_account_enabled() {
            return is_account_enabled;
        }

        public void setIs_account_enabled(boolean is_account_enabled) {
            this.is_account_enabled = is_account_enabled;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LocalDateTime getToken_created_at() {
            return token_created_at;
        }

        public void setToken_created_at(LocalDateTime token_created_at) {
            this.token_created_at = token_created_at;
        }

        public LocalDateTime getToken_expired_at() {
            return token_expired_at;
        }

        public void setToken_expired_at(LocalDateTime token_expired_at) {
            this.token_expired_at = token_expired_at;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }