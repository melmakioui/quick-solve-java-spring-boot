async function login(email, password) {
    console.log(JSON.stringify({
        "email": email,
        "password": password
    }))
    $.ajax("http://localhost:8080/loginTokenGenerate", {
        method: 'POST',
        body: "pepito",
        success: function(token){
            localStorage.setItem('token', "Bearer " + token);
            return true;
        },
        error: function(err){
            console.log(err)
            return false;
        }
    });
}