
$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        event.preventDefault(); // Prevent page reload

        let user = {
            email: $("#login_email").val().trim(),
            password: $("#login_password").val().trim()
        };

        $.ajax({
            url: "http://localhost:8080/api/auth/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (response) {
                // Example: After successful login
                localStorage.setItem("token", response.token);
                if (response.code === 201) { // Successful login
                    // Store user data before redirecting
                    // localStorage.setItem("studentName", response.data.name);
                    localStorage.setItem("studentEmail", response.data.email);
                    localStorage.setItem("studentNic", response.data.nic);
                    localStorage.setItem("studentReg", response.data.id);
                 localStorage.setItem("userType", response.data.role);

                    // ✅ Clear input fields after successful login
                    $("#login_email").val('');
                    $("#login_password").val('');

                    // Redirect according to userType
                    if (response.data.role === "ADMIN") {
                        window.location.href = "adminDashboard.html";
                    } else if (response.data.role === "USER") {
                        window.location.href = "studentHome.html";
                    //} else if (response.data.role === "TEACHER") {
                        //window.location.href = "teacher_dashboard.html";
                    }
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Login Failed",
                        text: "Invalid Email or Password",
                        confirmButtonText: "Try Again"
                    });
                }
            },
            error: function (xhr) {
                let errorMessage = "Invalid Email or Password";
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }

                Swal.fire({
                    icon: "error",
                    title: "Login Failed",
                    text: errorMessage,
                    confirmButtonText: "Try Again"
                });
            }
        });
    });
});