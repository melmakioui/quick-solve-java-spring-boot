function login(email, password) {
    $.ajax("http://localhost:8080/loginTokenGenerate", {
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            "email":email,
            "password":password
        }),
        success: function(token){
            localStorage.setItem('token', "Bearer " + token);
        },
        error: function(err){
            console.log(err);
        }
    });
}