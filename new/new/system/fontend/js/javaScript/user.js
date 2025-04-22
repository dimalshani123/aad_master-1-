
    $("#register_btn").click(function () {
    const name = $("#name").val().trim();
    const email = $("#email").val().trim();
    const nic = $("#nic").val().trim();
    const password = $("#password").val().trim();

    // Regular Expressions
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const nicRegex = /^([0-9]{9}[vV]|[0-9]{12})$/;

    // Validations
    if (name === "") {
    Swal.fire("Validation Error", "Please enter your name.", "warning");
    return;
}

    if (!emailRegex.test(email)) {
    Swal.fire("Validation Error", "Please enter a valid email address.", "warning");
    return;
}

    if (!nicRegex.test(nic)) {
    Swal.fire("Validation Error", "Please enter a valid NIC (e.g., 123456789V or 200012345678).", "warning");
    return;
}

    if (password.length < 6) {
    Swal.fire("Validation Error", "Password must be at least 6 characters long.", "warning");
    return;
}

    // If all validations pass
    saveUser();
});

    function saveUser() {
        let user = {
            email: $("#email").val().trim(),
            name: $("#name").val().trim(),
            nic: $("#nic").val().trim(),
            password: $("#password").val().trim(),
            userType: "USER"
        };

        $.ajax({
            url: "http://localhost:8080/api/auth/register",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function(response) {
                if (response && response.code === 201) {  // Check backend response
                    Swal.fire({
                        icon: "success",
                        title: "User Saved",
                        text: "User registration successful!",
                        confirmButtonColor: "#3085d6"
                    }).then(() => {
                        clearFields();
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Registration Failed",
                        text: "User not saved. Try again.",
                        confirmButtonColor: "#d33"
                    });
                }
            },
            error: function(xhr) {
                console.log("Error Response: ", xhr.responseText); // Log error message
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: xhr.responseText || "Something went wrong!",
                    confirmButtonColor: "#d33"
                });
            }
        });
    }
    function clearFields() {
    $("#name").val("");
    $("#nic").val("");
    $("#email").val("");
    $("#password").val("");
}

