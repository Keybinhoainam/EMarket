<template>
    <section class="wrapper">
        <div class="container">
            <div
                class="col-sm-8 offset-sm-2 col-lg-8 offset-lg-2 col-xl-6 offset-xl-3 text-center"
            >
                <div class="logo">
                    <img
                        :src="require('@/assets/images/logo.png')"
                        alt="logo"
                        class="img-fluid"
                    />
                </div>
                <form
                    @submit.prevent="login()"
                    class="rounded bg-white shadow p-5"
                >
                    <h3 class="text-dark fw-bolder fs-4 mb-2">Sign In</h3>
                    <div class="fw-normal text-muted mb-4">
                        New Here?
                        <a
                            href="/register"
                            class="text-primary fw-bold text-decoration-none"
                            >Create an Account</a
                        >
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="floatingInput"
                            placeholder="name@example.com"
                            v-model="user.username"
                            @blur="validateLogin()"
                            v-bind:class="{ 'is-invalid': errors.username }"
                        />
                        <label for="floatingInput">Username</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.username }}
                        </div>
                    </div>
                    <div class="form-floating">
                        <input
                            type="password"
                            class="form-control"
                            id="floatingPassword"
                            placeholder="Password"
                            v-model="user.password"
                            @blur="validateLogin()"
                            v-bind:class="{ 'is-invalid': errors.password }"
                        />
                        <label for="floatingPassword">Password</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.password }}
                        </div>
                    </div>
                    <div class="mt-2 text-end">
                        <a
                            href="#"
                            class="text-primary fw-bold text-decoration-none"
                            >Forget Password?</a
                        >
                    </div>
                    <button
                        type="submit"
                        class="btn btn-primary submit_btn w-100 my-4"
                    >
                        Continue
                    </button>
                    <div class="text-center text-muted text-uppercase mb-3">
                        or
                    </div>
                    <a href="#" class="btn btn-light login_with w-100 mb-3">
                        <img
                            :src="require('@/assets/images/google-icon.svg')"
                            class="img-fluid me-3"
                        />Continue with Google
                    </a>
                    <a href="#" class="btn btn-light login_with w-100 mb-3">
                        <img
                            :src="require('@/assets/images/facebook-icon.svg')"
                            class="img-fluid me-3"
                        />Continue with Facebook
                    </a>
                    <a href="#" class="btn btn-light login_with w-100 mb-3">
                        <img
                            :src="require('@/assets/images/linkedin-icon.svg')"
                            class="img-fluid me-3"
                        />Continue with LinkedIn
                    </a>
                </form>
            </div>
        </div>
    </section>
</template>
<script>
import mixinsAuthen from "@/mixins/mixinsAuthen";
import sweetAlert from "@/mixins/sweetAlert";
import validateAccount from "@/mixins/validateAccount";
import User from "@/models/user";
export default {
    name: "LoginForm",
    data() {
        return {
            user: new User("", ""),
            url: `${this.baseURL}/auth/login`,
            config: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
            },
        };
    },
    methods: {
        async login() {
            if (this.validateLogin()) {
                this.mxLogin();
            }
        },
    },
    mixins: [validateAccount, sweetAlert, mixinsAuthen],
    props: ["baseURL"],
};
</script>

<style>
.wrapper {
    padding: 0 0 100px;
    background-image: url(@/assets/images/bg.png);
    background-position: bottom center;
    background-repeat: no-repeat;
    background-size: contain;
    background-attachment: fixed;
    min-height: 100%;
}
.wrapper .logo img {
    max-width: 40%;
}
.wrapper input,
select {
    background-color: var(--light-white) !important;
    border-color: var(--light-white) !important;
    color: var(--gray) !important;
}
.wrapper input,
select:focus {
    box-shadow: none !important;
}

.wrapper .submit_btn {
    padding: 15px;
    font-weight: 500;
}
.wrapper .login_with {
    padding: 15px;
    font-size: 15px;
    font-weight: 500;
    transition: 0.3s ease-in-out;
}
.wrapper .submit_btn:focus,
.wrapper .login_with:focus {
    box-shadow: none;
}
.wrapper .login_with:hover {
    background-color: var(--gray-1);
    border-color: var(--gray-1);
}
.wrapper .invalid-feedback {
    text-align: left;
}
</style>
