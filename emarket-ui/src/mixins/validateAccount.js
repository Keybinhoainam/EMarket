import User from "@/models/user";

export default {
    data() {
        return {
            errors: new User("", "", "", "", "", "", "", "", ""),
            isValid: true,
        };
    },
    methods: {
        validateRegister() {
            this.isValid = true;
            this.errors = new User("", "", "", "", "", "", "", "", "");
            this.checkUserName();
            this.checkPassword();
            this.checkConfirmPassword();
            this.checkFullName();
            this.checkGender();
            return this.isValid;
        },
        validateAccountSettingSecurity() {
            this.checkPassword();
            this.checkConfirmPassword();
            return this.isValid;
        },
        validateLogin() {
            this.isValid = true;
            this.errors = new User("", "");
            this.checkUserName();
            this.checkPassword();
            return this.isValid;
        },
        checkUserName() {
            if (!this.user.username) {
                this.errors.username = "Username is required";
                this.isValid = false;
            }
        },
        checkPassword() {
            // const regexPassword =
            //   /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
            // let checkPassword = regexPassword.test(this.user.password);
            // if (!checkPassword) {
            //   this.errors.password = "Password is invalid";
            //   this.isValid = false;
            // }
            if (!this.user.password) {
                this.errors.password = "Password is required";
                this.isValid = false;
            }
        },
        checkConfirmPassword() {
            if (!this.user.confirmPassword) {
                this.errors.confirmPassword = "Confirm Password is required";
                this.isValid = false;
                return;
            }
            if (this.user.password != this.user.confirmPassword) {
                this.errors.confirmPassword = "Incorrect password confirmation";
                this.isValid = false;
            }
        },
        checkEmail() {
            if (!this.user.mail) {
                this.errors.mail = "Mail is required";
                this.isValid = false;
            }
        },
        checkFullName() {
            if (!this.user.fullname) {
                this.errors.fullname = "Full Name is required";
                this.isValid = false;
            }
        },
        checkGender() {
            if (!this.user.gender) {
                this.errors.gender = "Gender is required";
                this.isValid = false;
            }
        },
        checkBirthday() {
            if (!this.user.birthday) {
                this.errors.birthday = "Date of Birth is required";
                this.isValid = false;
            }
        },
        checkAvatar() {
            if (!this.user.avatar) {
                this.errors.avatar = "avatar is required";
                this.isValid = false;
            }
        },
        checkPhone() {
            var regexPhone = /[0-9]{10}/;
            var check = regexPhone.test(this.user.phone);
            if (!check) {
                this.errors.phone = "Fill numbers !";
            }
            if (!this.user.phone) {
                this.errors.phone = "Phone is required";
                this.isValid = false;
            }
        },
    },
};
