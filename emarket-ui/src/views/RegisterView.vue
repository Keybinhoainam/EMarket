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
                    @submit.prevent="save()"
                    class="rounded bg-white shadow p-5"
                >
                    <h3 class="text-dark fw-bolder fs-4 mb-2">
                        Create an Account
                    </h3>
                    <div class="fw-normal text-muted mb-4">
                        Already have an account?
                        <a
                            href="/login"
                            class="text-primary fw-bold text-decoration-none"
                            >Sign in here</a
                        >
                    </div>
                    <div class="text-center text-muted text-uppercase mb-3">
                        or
                    </div>
                    <a href="#" class="btn btn-light login_with w-100 mb-4">
                        <img
                            :src="require('@/assets/images/google-icon.svg')"
                            class="img-fluid me-3"
                        />Sign in with Google
                    </a>

                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            v-model="user.fullname"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.fullname }"
                        />
                        <label>Full Name</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.fullname }}
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <select
                            class="form-select"
                            aria-label="Select an option"
                            v-model="user.gender"
                        >
                            <option value="Male" selected>Male</option>
                            <option value="Female">Female</option>
                            <option value="Orther">Orther</option>
                        </select>
                        <label>Gender</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.gender }}
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="date"
                            class="form-control"
                            v-model="user.birthday"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.birthday }"
                        />
                        <label>Date of Birth</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.birthday }}
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            v-model="user.phone"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.phone }"
                        />
                        <label>Phone</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.phone }}
                        </div>
                    </div>
                    <!-- <div class="form-floating mb-3">
            <input
              type="text"
              class="form-control"
              
              v-model="user.address"
              @blur="validateRegister()"
              v-bind:class="{ 'is-invalid': errors.address }"
            />
            <label >Address</label>
            <div class="invalid-feedback mt-1 mb-4 ms-1">
              {{ errors.address }}
            </div>
          </div> -->
                    <div class="form-floating mb-3">
                        <input
                            type="email"
                            class="form-control"
                            v-model="user.email"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.email }"
                        />
                        <label>Mail</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.email }}
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            v-model="user.username"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.username }"
                        />
                        <label>Username</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.username }}
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input
                            type="password"
                            class="form-control"
                            id="floatingPassword"
                            placeholder="Password"
                            v-model="user.password"
                            @blur="validateRegister()"
                            v-bind:class="{ 'is-invalid': errors.password }"
                        />
                        <label for="floatingPassword">Password</label>
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.password }}
                        </div>
                        <!-- <span class="password-info fst-italic fw-bold">
              Use 8 or more character with a mix of letters, numbers & symbols.
            </span> -->
                        <div class="clearfix"></div>
                    </div>
                    <div class="form-floating">
                        <input
                            type="password"
                            class="form-control"
                            id="floatingConfirmPassword"
                            placeholder="Confirm Password"
                            v-model="user.confirmPassword"
                            @blur="validateRegister()"
                            v-bind:class="{
                                'is-invalid': errors.confirmPassword,
                            }"
                        />
                        <label for="floatingConfirmPassword"
                            >Confirm Password</label
                        >
                        <div class="invalid-feedback mt-1 mb-4 ms-1">
                            {{ errors.confirmPassword }}
                        </div>
                    </div>

                    <button
                        type="submit"
                        class="btn btn-primary submit_btn w-100 my-4"
                    >
                        Continue
                    </button>
                </form>
            </div>
        </div>
    </section>
</template>
<script>
import sweetAlert from "@/mixins/sweetAlert";
import validateAccount from "@/mixins/validateAccount";
import User from "@/models/user";
import authService from "@/services/auth.service";
import mixinsAuthen from '@/mixins/mixinsAuthen';
export default {
    name: "SignupForm",
    data() {
        return {
            user: new User("", "", "", "", "", "Male", "", ""),
            url: `${this.baseURL}/auth/register`,
        };
    },
    methods: {
        async save() {
            if (this.validateRegister()) {
                this.mxRegister();
            }
        },
    },
    mixins: [validateAccount, sweetAlert,mixinsAuthen],
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
